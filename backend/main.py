# NextGenAI Agency - Hidro AI Core Backend
from flask import Flask, request, jsonify 
import psycopg2 
from datetime import datetime

app = Flask(__name__)

# Configuración de conexión a PostgreSQL
def get_db_connection():
    return psycopg2.connect(
        host="localhost", #colocar comas luego de las comillas sino va a dar error
        database="hidro_ai_db",
        user="postgres", # Cambiá esto por tus credenciales
        password="190439Ab",
        client_encoding='utf8'
    )

@app.route('/webhook/bomba', methods=['POST'])
def webhook_bomba():
    """
    Recibe la señal del Sonoff cuando el Hunter X-Core activa el contactor.
    """
    data = request.json
    estado = data.get('power') # 'on' o 'off'
    origen = data.get('source', 'Hunter_XCore')

    conn = get_db_connection()
    cur = conn.cursor()

    if estado == 'on':
        # Registramos el inicio de la Zeta 4 (150L/min)
        cur.execute(
            "INSERT INTO logs_bomba (trigger_origen, estado_final) VALUES (%s, 'Iniciado') RETURNING id",
            (origen,)
        )
        log_id = cur.fetchone()[0]
        mensaje = f"Hidro AI: Bomba Zeta 4 encendida por {origen}."
    else:
        # 1. Calculamos la duración y los litros basados en la Zeta 4 (150L/min)
        # 150L por 60 seg = 2.5 Litros por segundo
        cur.execute("""
            UPDATE logs_bomba 
            SET fin_carga = CURRENT_TIMESTAMP,
                estado_final = 'OK',
                consumo_litros_calc = EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - inicio_carga)) * (caudal_l_min / 60)
            WHERE estado_final = 'Iniciado'
        """)
        mensaje = "Hidro AI: Carga completada. Consumo calculado y guardado." 

    conn.commit()
    cur.close()
    conn.close()
    
    return jsonify({"status": "success", "message": mensaje}), 200

if __name__ == '__main__':
    app.run(port=5000, debug=True)
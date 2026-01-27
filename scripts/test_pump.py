# NextGenAI Agency - Hidro AI Simulator
import requests # type: ignore
import time

URL = "http://localhost:5000/webhook/bomba"

def simular_carga(duracion_segundos):
    print(f"--- HIDRO AI: Iniciando simulación de carga ---")
    
    # 1. Simulamos que el Sonoff detecta que el Hunter prendió la bomba
    inicio = {"power": "on", "source": "Hunter_XCore"}
    r1 = requests.post(URL, json=inicio)
    print(f"Respuesta Inicio: {r1.json()['message']}")

    print(f"Bombeando agua (Zeta 4 - 150L/min)...")
    time.sleep(duracion_segundos) # Esperamos los segundos de la prueba

    # 2. Simulamos que el Hunter corta o el tanque se llenó
    fin = {"power": "off", "source": "Hunter_XCore"}
    r2 = requests.post(URL, json=fin)
    print(f"Respuesta Fin: {r2.json()['message']}")
    print(f"--- Simulación Finalizada ---")

if __name__ == "__main__":
    # Probamos una carga de 3 segundos para el test
    simular_carga(3)
-- NextGenAI Agency: Esquema de Base de Datos para Hidro AI
-- Proyecto: Automatización de Riego e Inteligencia Hídrica

-- 1. Registro de operaciones de la Bomba Zeta 4
CREATE TABLE logs_bomba (
    id SERIAL PRIMARY KEY,
    inicio_carga TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fin_carga TIMESTAMP,
    caudal_l_min DECIMAL DEFAULT 150.0, -- Capacidad de la Zeta 4
    consumo_litros_calc DECIMAL,
    trigger_origen VARCHAR(50), -- 'Hunter_XCore', 'Manual_App', 'Auto_Freedom'
    estado_final VARCHAR(20) -- 'OK', 'FALLO_FUGA', 'CORTE_MANUAL'
);

-- 2. Estado de los periféricos (Sonoff y Hunter)
CREATE TABLE estado_hardware (
    dispositivo VARCHAR(50) PRIMARY KEY, -- 'Sonoff_Sala', 'Hunter_XCore'
    ip_asignada VARCHAR(15),
    ultima_señal TIMESTAMP,
    firmware_version VARCHAR(20)
);

-- 3. Métricas de eficiencia hídrica
CREATE TABLE metricas_ahorro (
    id SERIAL PRIMARY KEY,
    fecha DATE UNIQUE,
    litros_totales_dia DECIMAL,
    eficiencia_vs_plan_anterior DECIMAL -- Comparativa contra el sistema viejo sin IA
);
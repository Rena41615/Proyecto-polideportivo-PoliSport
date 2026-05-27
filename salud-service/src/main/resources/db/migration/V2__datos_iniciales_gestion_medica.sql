INSERT INTO gestion_medica (atleta_id, tipo_lesion, descripcion, fecha_lesion, fecha_retorno, estado_retorno, medico_id, tratamiento)
VALUES
    (1, 'Esguince',   'Esguince tobillo derecho grado 2', '2026-04-10', '2026-05-10', 'En recuperacion', 1, 'Reposo y fisioterapia'),
    (2, 'Contusion',  'Contusion rodilla izquierda',      '2026-04-20', '2026-05-05', 'Apto',            1, 'Antiinflamatorios'),
    (3, 'Fractura',   'Fractura leve muneca derecha',     '2026-03-15', NULL,         'En tratamiento',  2, 'Inmovilizacion y control semanal'),
    (4, 'Tendinitis', 'Tendinitis hombro izquierdo',      '2026-04-28', '2026-05-20', 'En recuperacion', 2, 'Fisioterapia y ejercicios dirigidos');
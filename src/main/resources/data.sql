INSERT INTO Usuario (email, password, rol, activo, nombre)
SELECT 'test@unlam.edu.ar', 'test', 'ADMIN', true, 'Administrador'
WHERE NOT EXISTS (
        SELECT 1 FROM Usuario WHERE email = 'test@unlam.edu.ar'
    ) LIMIT 1;
-- Crear la base de datos 'critic' si no existe
CREATE DATABASE IF NOT EXISTS critic;

-- Usar la base de datos 'critic'
USE critic;
DELIMITER $$

DELIMITER $$

CREATE TRIGGER after_review_insert
    AFTER INSERT ON review
    FOR EACH ROW
BEGIN
    DECLARE avg_score FLOAT;

    -- Calcular el promedio de los scores de las reviews para la película asociada
    SELECT AVG(score) INTO avg_score
    FROM review
    WHERE movie_id = NEW.movie_id;

    -- Redondear el promedio a 2 decimales
    SET avg_score = ROUND(avg_score, 2);

    -- Actualizar el score de la película con el promedio redondeado
    UPDATE movie
    SET score = avg_score
    WHERE id = NEW.movie_id;
END$$

DELIMITER ;
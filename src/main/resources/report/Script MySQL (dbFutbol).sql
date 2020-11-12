/* 1. Creación de la base de datos */
CREATE DATABASE dbFutbol;
USE dbFutbol;

/* 2. Creación de las tablas
    - Tabla ligas (codLiga, nomLiga) 
    - Tabla equipos (codEquipo, nomEquipo, codLiga, localidad, internacional)
    - Tabla futbolistas (codDnionie, nombre, nacionalidad)
    - Tabla contratos (codDnionie, nombre, nacionalidad) */
CREATE TABLE ligas (
    codLiga VARCHAR (5) NOT NULL PRIMARY KEY,
    nomLiga VARCHAR (50) NOT NULL
);

CREATE TABLE equipos (
    codEquipo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomEquipo VARCHAR (40) NOT NULL,
    codLiga CHAR(5) DEFAULT('PDN'),
    localidad VARCHAR(60) NOT NULL,
    internacional TINYINT(1) DEFAULT 0,
    CONSTRAINT FK_equipo_liga
    FOREIGN KEY (codLiga) REFERENCES ligas(codLiga) ON DELETE CASCADE
);

CREATE TABLE futbolistas (
    codDnionie VARCHAR (9) NOT NULL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(40) NOT NULL
);

CREATE TABLE contratos (
    codContrato INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    codDnionie VARCHAR (9) NOT NULL,
    codEquipo INT NOT NULL,
    fechaInicio DATE DEFAULT NULL,
    fechaFin DATE DEFAULT NULL,
    precioAnual INT NOT NULL,
    precioResicion INT NOT NULL,
    CONSTRAINT FK_contrato_jugador
    FOREIGN KEY (coddnionie) REFERENCES futbolistas(coddnionie) ON DELETE CASCADE,
    CONSTRAINT FK_contrato_equipo
    FOREIGN KEY (codEquipo) REFERENCES equipos(codEquipo) ON DELETE CASCADE
);


/* 3. Inserción de valores
    - 5 Ligas
    - 10 equipos
    - 20 futbolistas
    - 20 contratos */
INSERT INTO ligas VALUES (11111, 'Liga Santander');
INSERT INTO ligas VALUES (22222, 'Premier League');
INSERT INTO ligas VALUES (33333, 'Bundesliga');
INSERT INTO ligas VALUES (44444, 'Ligue 1');
INSERT INTO ligas VALUES (55555, 'Eredivise');

INSERT INTO equipos VALUES (NULL, 'FC Barcelona', 11111, 'Barcelona', 1);
INSERT INTO equipos VALUES (NULL, 'Real Madrid CF', 11111, 'Madrid', 1);
INSERT INTO equipos VALUES (NULL, 'Arsenal', 22222, 'Londres', 1);
INSERT INTO equipos VALUES (NULL, 'Chelsea CF', 22222, 'Londres', 1);
INSERT INTO equipos VALUES (NULL, 'Bayern München', 33333, 'Múnich', 1);
INSERT INTO equipos VALUES (NULL, 'Borussia Dortmund', 33333, 'Dortmund', 1);
INSERT INTO equipos VALUES (NULL, 'PSG', 44444, 'París', 1);
INSERT INTO equipos VALUES (NULL, 'Olympique de Lyon', 44444, 'Lyon', 1);
INSERT INTO equipos VALUES (NULL, 'Ajax de Ámsterdam', 55555, 'Ámsterdam', 1);
INSERT INTO equipos VALUES (NULL, 'Feyenoord', 55555, 'Róterdam', 1);
INSERT INTO equipos VALUES (NULL, 'Schalke 04', 33333, 'Gelsenkirchen', 1);
INSERT INTO equipos VALUES (NULL, 'Borussia Mönchengladbach', 33333, 'Mönchengladbach', 0);
INSERT INTO equipos VALUES (NULL, 'Werder Bremen', 33333, 'Bremen', 0);
INSERT INTO equipos VALUES (NULL, 'Villarreal CF', 11111, 'Castellón', 1);
INSERT INTO equipos VALUES (NULL, 'Liverpool FC', 22222, 'Liverpool', 1);
INSERT INTO equipos VALUES (NULL, 'Stade de Reims', 44444, 'Reims', 0);
INSERT INTO equipos VALUES (NULL, 'West Bromwich Albion', 22222, 'West Bromwich', 0);
INSERT INTO equipos VALUES (NULL, 'AS Mónaco', 44444, 'Monaco', 1);
INSERT INTO equipos VALUES (NULL, 'FC Twente', 33333, 'Enschede', 0);

INSERT INTO futbolistas VALUES ('11111111A', 'Lionel Messi', 'Argentina');
INSERT INTO futbolistas VALUES ('11111111B', 'Ansu Fati', 'Española');
INSERT INTO futbolistas VALUES ('22222222C', 'Thomas Müller', 'Alemana');
INSERT INTO futbolistas VALUES ('22222222D', 'Corentin Tolisso', 'Francesa');
INSERT INTO futbolistas VALUES ('33333333E', 'Sergiño Dest', 'Estadounidense');
INSERT INTO futbolistas VALUES ('33333333F', 'Daley Blind', 'Holandés');
INSERT INTO futbolistas VALUES ('44444444G', 'Steven Berghuis', 'Holandés');
INSERT INTO futbolistas VALUES ('44444444H', 'Orkun Kökçü', 'Turco');
INSERT INTO futbolistas VALUES ('55555555I', 'Neymar Jr.', 'Brasileño');
INSERT INTO futbolistas VALUES ('55555555J', 'Ander Herrera', 'Española');
INSERT INTO futbolistas VALUES ('66666666K', 'David Luiz', 'Brasileño');
INSERT INTO futbolistas VALUES ('66666666L', 'Alexandre Lacazette', 'Francesa');
INSERT INTO futbolistas VALUES ('77777777M', 'Karim Benzema', 'Francesa');
INSERT INTO futbolistas VALUES ('77777777N', 'Isco', 'Española');
INSERT INTO futbolistas VALUES ('88888888O', 'Timo Werner', 'Alemana');
INSERT INTO futbolistas VALUES ('88888888P', 'Jorginho', 'Italiano');
INSERT INTO futbolistas VALUES ('99999999Q', 'Memphis Depay', 'Holandés');
INSERT INTO futbolistas VALUES ('99999999R', 'Karl Toko Ekambi', 'Camerunés');
INSERT INTO futbolistas VALUES ('00000000S', 'Lukasz Piszczek', 'Polaco');
INSERT INTO futbolistas VALUES ('00000000T', 'Axel Witsel', 'Belga');

INSERT INTO contratos VALUES (NULL, '11111111A', 1, '2005-07-01', '2021-06-30', 11200, 18000);
INSERT INTO contratos VALUES (NULL, '11111111B', 1, '2019-08-01', '2022-06-30', 5000, 10000);
INSERT INTO contratos VALUES (NULL, '22222222C', 2, '2009-07-01', '2023-06-30', 3500, 7500);
INSERT INTO contratos VALUES (NULL, '22222222D', 2, '2017-07-01', '2022-06-30', 2550, 4500);
INSERT INTO contratos VALUES (NULL, '33333333E', 3, '2019-09-12', '2022-06-30', 1800, 2000);
INSERT INTO contratos VALUES (NULL, '33333333F', 3, '2018-07-17', '2022-06-30', 2000, 2500);
INSERT INTO contratos VALUES (NULL, '44444444G', 4, '2017-07-31', '2022-06-30', 1100, 1400);
INSERT INTO contratos VALUES (NULL, '44444444H', 4, '2018-07-01', '2025-06-30', 1750, 1850);
INSERT INTO contratos VALUES (NULL, '55555555I', 5, '2017-08-03', '2022-06-30', 12800, 18000);
INSERT INTO contratos VALUES (NULL, '55555555J', 5, '2019-07-04', '2024-06-30', 1600, 3000);
INSERT INTO contratos VALUES (NULL, '66666666K', 6, '2019-08-08', '2021-06-30', 800, 3000);
INSERT INTO contratos VALUES (NULL, '66666666L', 6, '2017-07-05', '2022-06-30', 4800, 7000);
INSERT INTO contratos VALUES (NULL, '77777777M', 7, '2009-07-09', '2022-06-30', 3200, 6000);
INSERT INTO contratos VALUES (NULL, '77777777N', 7, '2013-07-01', '2022-06-30', 4000, 9000);
INSERT INTO contratos VALUES (NULL, '88888888O', 8, '2020-07-01', '2025-06-30', 6400, 8000);
INSERT INTO contratos VALUES (NULL, '88888888P', 8, '2018-07-14', '2023-06-30', 5200, 6500);
INSERT INTO contratos VALUES (NULL, '99999999Q', 9, '2017-01-20', '2021-06-30', 4400, 5500);
INSERT INTO contratos VALUES (NULL, '99999999R', 9, '2020-07-01', '2024-06-30', 1400, 1800);
INSERT INTO contratos VALUES (NULL, '00000000S', 10, '2010-10-01', '2021-06-30', 200, 1600);
INSERT INTO contratos VALUES (NULL, '00000000T', 10, '2018-08-06', '2022-06-30', 1700, 4000);
INSERT INTO contratos VALUES (NULL, '77777777M', 9, '2005-07-01', '2009-07-09', 2900, 3500);


INSERT INTO equipos VALUES (10, 'Feyenoord', 55555, 'Róterdam', 1);
INSERT INTO contratos VALUES (10, '00000000S', 10, '2010-10-01', '2021-06-30', 200, 1600);
INSERT INTO contratos VALUES (10, '00000000T', 10, '2018-08-06', '2022-06-30', 1700, 4000);


/* ---------------------- PROCEDIMIENTOS ALMACENADOS Y FUNCIONES EN MYSQL ---------------------- */

/*
1.- Crear un procedimiento almacenado que liste todos los contratos de cierto futbolista pasando por
parámetro de entrada el dni o nie del futbolista, ordenados por fecha de inicio.
Los datos a visualizar serán: Código de contrato, nombre de equipo, nombre de liga, fecha de inicio,
fecha de fin, precio anual y precio de recisión del contrato. 
*/
DELIMITER $$
CREATE PROCEDURE ejerc_1 (dni VARCHAR(9))
    BEGIN
        SELECT codContrato, nomEquipo, nomLiga, fechaInicio, fechaFin, precioAnual, precioResicion
        FROM contratos
        INNER JOIN equipos on equipos.codEquipo = contratos.codEquipo
        INNER JOIN ligas on equipos.codLiga = ligas.codLiga
        WHERE codDnionie LIKE dni
        ORDER BY fechaInicio DESC;
    END $$
DELIMITER;
-- DROP PROCEDURE IF EXISTS ejerc_1;

-- EJECUCIÓN
-- CALL ejerc_1("77777777M");

/*
2.- Crear un procedimiento almacenado que inserte un equipo, de modo que se le pase como parámetros
todos los datos. Comprobar que el código de liga pasado exista en la tabla ligas. En caso de que 
no exista la liga que no se inserte.
    - Devolver en un parámetro de salida: 0 si la liga no existe y 1 si la liga existe.
    - Devolver en otro parámetro de salida: 0 si el equipo no se insertó y 1 si la inserción 
    fue correcta. 
*/
DELIMITER $$
CREATE PROCEDURE ejerc_2 (IN nomEquipo VARCHAR (40), IN codLiga CHAR (5), IN localidad VARCHAR (60), IN internacional TINYINT, OUT ligaExiste TINYINT, OUT insercion TINYINT)
    BEGIN
        SET ligaExiste = 0;
        SET insercion = 0;

        SET ligaExiste = (SELECT COUNT(*) FROM ligas WHERE ligas.codLiga = codLiga);
        IF ligaExiste = 1 THEN
            SET @EquiposAntes = (SELECT COUNT(*) FROM equipos);
            INSERT INTO equipos VALUES (null, nomEquipo, codLiga, localidad, internacional);
        SET @EquiposDespues = (SELECT COUNT(*) FROM equipos);
	        IF @EquiposAntes < @EquiposDespues THEN
    	        SET insercion = 1;
            ELSE
    	        SET insercion = 0;
            END IF;
        ELSE
	        SET insercion = 0;
        END IF;
    END $$
DELIMITER;
-- DROP PROCEDURE IF EXISTS ejerc_2;

-- La Liga existe
-- CALL ejerc_2 ('Atlético de Madrid', 11111, 'Madrid', 1, @ligaExiste, @insercion);
-- SELECT @ligaExiste;
-- SELECT @insercion;

-- La Liga no existe
-- CALL ejerc_2 ('Manchester United', 48752, 'Manchester', 1, @ligaExiste, @insercion);
-- SELECT @ligaExiste;
-- SELECT @insercion;

/*
3.- Crear un procedimiento almacenado que indicándole un equipo, precio anual y un precio recisión,
devuelva dos parámetros. En un parámetro de salida la cantidad de futbolistas en activo (con contrato
vigente) que hay en dicho equipo. En otro parámetro de salida la cantidad de futbolistas en activo de
dicho equipo con precio anual y de recisión menor de los indicados.
*/
DELIMITER $$
CREATE PROCEDURE ejerc_3 (IN codEquipo INT, IN precioAnual INT, IN precioRecision INT, OUT futbolistasPreciso INT, OUT futbolistasActivo INT)
    BEGIN 
        SET futbolistasPreciso = (SELECT COUNT(contratos.codDnionie)
                                    FROM contratos
                                    WHERE contratos.codEquipo = codEquipo AND contratos.fechaFin >= CURDATE() AND contratos.precioAnual < precioAnual 
                                    AND contratos.precioResicion < precioRecision);

        SET futbolistasActivo = (SELECT COUNT(contratos.codDnionie)
                                    FROM contratos
                                    WHERE contratos.codEquipo = codEquipo AND contratos.fechaFin >= CURDATE());
    
    END $$
DELIMITER;
-- DROP PROCEDURE IF EXISTS ejerc_3;

-- EJECUCIÓN
-- CALL ejerc_3 (9, 18000, 18000, @futbolistasPreciso, @futbolistasActivo);
-- SELECT @futbolistasPreciso, @futbolistasActivo;


/*
4.- Crear una función que dándole un dni o nie de un futbolista nos devuelva en número de meses total
que ha estado en equipos. 
*/
DELIMITER $$
CREATE FUNCTION ejerc_4(codDnionie VARCHAR(9)) RETURNS INT
    BEGIN
    RETURN (SELECT (TIMESTAMPDIFF(MONTH, fechaInicio, fechaFin)) 
            FROM contratos
            INNER JOIN futbolistas ON contratos.codDnionie = futbolistas.codDnionie
            WHERE codDnionie = futbolistas.codDnionie);
    END $$
DELIMITER;
-- DROP FUNCTION IF EXISTS ejerc_4;

-- EJECUCIÓN
-- SELECT ejerc_4 ('11111111A') as mesesTotales;


/* ---------------------------------------- TRIGGERS EN MYSQL --------------------------------------- */

/*
1.- Hacer un Trigger que en la tabla contratos al insertar o modificar el precio de recisión no permita
que sea menor que el precio anual.
*/
DELIMITER $$
CREATE TRIGGER trigger1_INSERT BEFORE UPDATE
    ON contratos FOR EACH ROW
    BEGIN
        IF NEW.precioAnual>NEW.precioResicion THEN
            signal SQLSTATE '45000' set message_text = 'El precio anual no puede ser mayor que el precio de recisión'; 
        END IF;
    END $$
DELIMITER;
-- DROP TRIGGER IF EXISTS trigger1_INSERT;

DELIMITER $$
CREATE TRIGGER trigger1_UPDATE BEFORE INSERT
    ON contratos FOR EACH ROW
    BEGIN
        IF NEW.precioAnual>NEW.precioResicion THEN
            signal SQLSTATE '45000' set message_text = 'El precio anual no puede ser mayor que el precio de recisión'; 
        END IF;
    END $$
DELIMITER;
-- DROP TRIGGER IF EXISTS trigger1_UPDATE;

/*
2.- Hacer un Trigger que si en la tabla contratos que al insertar o modificar ponemos la fecha inicio
posterior a la fecha fin que las intercambie.
*/
DELIMITER $$
CREATE TRIGGER trigger2_INSERT BEFORE UPDATE
    ON contratos FOR EACH ROW
    BEGIN 
        IF NEW.fechaInicio>NEW.fechaFin THEN
            SET @Fecha1 = NEW.fechaInicio;
            SET @Fecha2 = NEW.fechaFin;
            SET NEW.fechaInicio = @Fecha2;
            SET NEW.fechaFin = @Fecha1;
        END IF;
    END $$
DELIMITER;
-- DROP TRIGGER IF EXISTS trigger2_INSERT;

DELIMITER $$
CREATE TRIGGER trigger2_UPDATE BEFORE INSERT
    ON contratos FOR EACH ROW
    BEGIN 
        IF NEW.fechaInicio>NEW.fechaFin THEN
            SET @Fecha1 = NEW.fechaInicio;
            SET @Fecha2 = NEW.fechaFin;
            SET NEW.fechaInicio = @Fecha2;
            SET NEW.fechaFin = @Fecha1;
        END IF;
    END $$
DELIMITER;
-- DROP TRIGGER IF EXISTS trigger2_UPDATE;

/*
3.- Hacer un Trigger que no permita eliminar ninguna liga.
*/
DELIMITER $$
CREATE TRIGGER trigger3_NOBORRAR BEFORE DELETE ON ligas 
    FOR EACH ROW signal SQLSTATE '45000' set message_text = 'No puede borrarse ninguna liga';
    $$
DELIMITER ;
-- DROP TRIGGER IF EXISTS trigger3_NOBORRAR;
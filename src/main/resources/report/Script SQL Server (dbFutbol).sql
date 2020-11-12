/* 1. Creación de la base de datos */
USE master;
GO
CREATE DATABASE dbFutbol;
GO
USE dbFutbol;
GO

/* 2. Creación de las tablas
    - Tabla ligas (codLiga, nomLiga) 
    - Tabla equipos (codEquipo, nomEquipo, codLiga, localidad, internacional)
    - Tabla futbolistas (codDnionie, nombre, nacionalidad)
    - Tabla contratos (codDnionie, nombre, nacionalidad) */
CREATE TABLE ligas (
	codLiga CHAR(5) PRIMARY KEY, 
	nomLiga VARCHAR(50)
);

CREATE TABLE equipos (
	codEquipo INTEGER IDENTITY PRIMARY KEY, 
	nomEquipo VARCHAR(40), 
	codLiga CHAR(5) DEFAULT 'PDN' 
	FOREIGN KEY (codLiga) REFERENCES ligas(codLiga),
	localidad VARCHAR(60), 
	internacional BIT DEFAULT 0
);

CREATE table futbolistas (
	codDnionie CHAR(9) PRIMARY KEY, 
	nomFutbolista VARCHAR(50), 
	nacionalidad VARCHAR(40)
);

CREATE table contratos (
	codContrato INTEGER IDENTITY PRIMARY KEY, 
	codDnionie CHAR(9) 
	FOREIGN KEY (codDnionie) REFERENCES futbolistas(codDnionie), 
	codEquipo INTEGER
	FOREIGN KEY (codEquipo) REFERENCES equipos(codEquipo),
	fechaInicio DATE, 
	fechaFin DATE, 
	precioAnual INT, 
	precioResicion INT
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

select * from ligas;

INSERT INTO equipos VALUES ('FC Barcelona', 11111, 'Barcelona', 1);
INSERT INTO equipos VALUES ('Real Madrid CF', 11111, 'Madrid', 1);
INSERT INTO equipos VALUES ('Arsenal', 22222, 'Londres', 1);
INSERT INTO equipos VALUES ('Chelsea CF', 22222, 'Londres', 1);
INSERT INTO equipos VALUES ('Bayern München', 33333, 'Múnich', 1);
INSERT INTO equipos VALUES ('Borussia Dortmund', 33333, 'Dortmund', 1);
INSERT INTO equipos VALUES ('PSG', 44444, 'París', 1);
INSERT INTO equipos VALUES ('Olympique de Lyon', 44444, 'Lyon', 1);
INSERT INTO equipos VALUES ('Ajax de Ámsterdam', 55555, 'Ámsterdam', 1);
INSERT INTO equipos VALUES ('Feyenoord', 55555, 'Róterdam', 1);
INSERT INTO equipos VALUES ('Schalke 04', 33333, 'Gelsenkirchen', 1);
INSERT INTO equipos VALUES ('Borussia Mönchengladbach', 33333, 'Mönchengladbach', 0);
INSERT INTO equipos VALUES ('Werder Bremen', 33333, 'Bremen', 0);
INSERT INTO equipos VALUES ('Villarreal CF', 11111, 'Castellón', 1);
INSERT INTO equipos VALUES ('Liverpool FC', 22222, 'Liverpool', 1);
INSERT INTO equipos VALUES ('Stade de Reims', 44444, 'Reims', 0);
INSERT INTO equipos VALUES ('West Bromwich Albion', 22222, 'West Bromwich', 0);
INSERT INTO equipos VALUES ('AS Mónaco', 44444, 'Monaco', 1);
INSERT INTO equipos VALUES ('FC Twente', 33333, 'Enschede', 0);

select * from equipos;

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

select * from futbolistas;

INSERT INTO contratos VALUES ('11111111A', 1, '01/07/2005', '30/06/2021', 11200, 18000);
INSERT INTO contratos VALUES ('11111111B', 1, '2019-08-01', '2022-06-30', 5000, 10000);
INSERT INTO contratos VALUES ('22222222C', 2, '2009-07-01', '2023-06-30', 3500, 7500);
INSERT INTO contratos VALUES ('22222222D', 2, '2017-07-01', '2022-06-30', 2550, 4500);
INSERT INTO contratos VALUES ('33333333E', 3, '2019-09-12', '2022-06-30', 1800, 2000);
INSERT INTO contratos VALUES ('33333333F', 3, '2018-07-17', '2022-06-30', 2000, 2500);
INSERT INTO contratos VALUES ('44444444G', 4, '2017-07-31', '2022-06-30', 1100, 1400);
INSERT INTO contratos VALUES ('44444444H', 4, '2018-07-01', '2025-06-30', 1750, 1850);
INSERT INTO contratos VALUES ('55555555I', 5, '2017-08-03', '2022-06-30', 12800, 18000);
INSERT INTO contratos VALUES ('55555555J', 5, '2019-07-04', '2024-06-30', 1600, 3000);
INSERT INTO contratos VALUES ('66666666K', 6, '2019-08-08', '2021-06-30', 800, 3000);
INSERT INTO contratos VALUES ('66666666L', 6, '2017-07-05', '2022-06-30', 4800, 7000);
INSERT INTO contratos VALUES ('77777777M', 7, '2009-07-09', '2022-06-30', 3200, 6000);
INSERT INTO contratos VALUES ('77777777N', 7, '2013-07-01', '2022-06-30', 4000, 9000);
INSERT INTO contratos VALUES ('88888888O', 8, '2020-07-01', '2025-06-30', 6400, 8000);
INSERT INTO contratos VALUES ('88888888P', 8, '2018-07-14', '2023-06-30', 5200, 6500);
INSERT INTO contratos VALUES ('99999999Q', 9, '2017-01-20', '2021-06-30', 4400, 5500);
INSERT INTO contratos VALUES ('99999999R', 9, '2020-07-01', '2024-06-30', 1400, 1800);
INSERT INTO contratos VALUES ('00000000S', 10, '2010-10-01', '2021-06-30', 200, 1600);
INSERT INTO contratos VALUES ('00000000T', 10, '2018-08-06', '2022-06-30', 1700, 4000);
INSERT INTO contratos VALUES ('77777777M', 9, '2005-07-01', '2009-07-09', 2900, 3500);

select * from contratos;


/* ---------------------- PROCEDIMIENTOS ALMACENADOS Y FUNCIONES EN MYSQL ---------------------- */

/*
1.- Crear un procedimiento almacenado que liste todos los contratos de cierto futbolista pasando por
parámetro de entrada el dni o nie del futbolista, ordenados por fecha de inicio.
Los datos a visualizar serán: Código de contrato, nombre de equipo, nombre de liga, fecha de inicio,
fecha de fin, precio anual y precio de recisión del contrato. 
*/
GO
CREATE PROCEDURE ejerc_1 @codDnionie CHAR(9)
	AS
		SELECT codContrato, nomEquipo, nomLiga, fechaInicio, fechaFin, precioAnual, precioResicion FROM ligas
		INNER JOIN equipos ON equipos.codLiga = ligas.codLiga
		INNER JOIN contratos ON contratos.codEquipo = equipos.codEquipo
		WHERE contratos.codDnionie = @codDnionie
		order by fechaInicio;
	GO

-- EJECUCIÓN
-- EXEC  dbo.ejerc_1 '11111111F';

/*
2.- Crear un procedimiento almacenado que inserte un equipo, de modo que se le pase como parámetros
todos los datos. Comprobar que el código de liga pasado exista en la tabla ligas. En caso de que 
no exista la liga que no se inserte.
    - Devolver en un parámetro de salida: 0 si la liga no existe y 1 si la liga existe.
    - Devolver en otro parámetro de salida: 0 si el equipo no se insertó y 1 si la inserción 
    fue correcta. 
*/
GO
CREATE PROCEDURE ejerc_2 @nomEquipo VARCHAR(40), @codLiga CHAR(5), @localidad VARCHAR(60), @internacional BIT, @Salida1 INT OUTPUT, @Salida2 INT OUTPUT
 	AS
		IF (@codLiga != ALL (SELECT codLiga FROM equipos))
			BEGIN 
				SET @Salida1 = 0;
			END
		ELSE
			BEGIN 
				SET @Salida1 = 1;
			END

			BEGIN TRY 
				INSERT INTO equipos VALUES (@nomEquipo, @codLiga, @localidad, @internacional)
				SET @Salida2 = 1;
			END TRY
			BEGIN CATCH
				SET @Salida2 = 0;
			END CATCH
GO

-- EJECUCIÓN
-- Existe Liga
-- DECLARE @Salida1 INT, @Salida2 INT;
-- EXEC dbo.ejerc_2 'EquipoExtra', 11111, 'VistaBella', 0, @Salida1 OUTPUT, @Salida2 OUTPUT;
-- SELECT @Salida1 AS 'No existe Liga';
-- SELECT @Salida2 AS 'Insercion';

-- No Existe Liga
-- DECLARE @Salida1 INT, @Salida2 INT;
-- EXEC dbo.ejerc_2 'EquipoExtra', 45687, 'VistaBella', 0, @Salida1 OUTPUT, @Salida2 OUTPUT;
-- SELECT @Salida1 AS 'No existe Liga';
-- SELECT @Salida2 AS 'Insercion';

/*
3.- Crear un procedimiento almacenado que indicándole un equipo, precio anual y un precio recisión,
devuelva dos parámetros. En un parámetro de salida la cantidad de futbolistas en activo (con contrato
vigente) que hay en dicho equipo. En otro parámetro de salida la cantidad de futbolistas en activo de
dicho equipo con precio anual y de recisión menor de los indicados.
*/
GO
CREATE PROCEDURE ejerc_3 @codEquipo INT, @precioAnual INT, @precioResicion INT, @Salida1 INT OUTPUT, @Salida2 INT OUTPUT
AS
	BEGIN
	SET @Salida1 = (SELECT count(codDnionie) FROM contratos WHERE @codEquipo = contratos.codEquipo
					AND fechaFin > getdate() AND fechaInicio < GETDATE());
	SET @Salida2 = (SELECT count(codDnionie) FROM contratos WHERE @codEquipo = contratos.codEquipo
					AND @precioAnual > contratos.precioAnual AND @precioResicion > contratos.precioResicion
					AND fechaFin > getdate() AND fechaInicio < GETDATE());
	END
GO

-- EJECUCIÓN
-- DECLARE @Salida1 INT, @Salida2 INT;
-- EXEC dbo.ejerc_3 6, 12369, 1235, @Salida1 OUTPUT, @Salida2 OUTPUT;
-- SELECT @Salida1 AS 'Conteo por fecha';
-- SELECT @Salida2 AS 'Conteo por precio';

-- SELECT * FROM contratos

/*
4.- Crear una función que dándole un dni o nie de un futbolista nos devuelva en número de meses total
que ha estado en equipos. 
*/
GO
CREATE FUNCTION ejerc_4 (@dni CHAR(9))
RETURNS INT
AS
	BEGIN
	RETURN (SELECT sum(DATEDIFF(MONTH, fechaInicio, fechaFin)) FROM contratos
			INNER JOIN futbolistas ON contratos.codDnionie = futbolistas.codDnionie
			WHERE @dni = futbolistas.codDnionie);
	END
GO

-- EJECUCIÓN
-- SELECT dbo.ejerc_4 ('11111111F') AS Meses;
-- SELECT dbo.ejerc_4 ('22222222R') AS Meses;


/* ---------------------------------------- TRIGGERS EN MYSQL --------------------------------------- */

/*
1.- Hacer un Trigger que en la tabla contratos al insertar o modificar el precio de recisión no permita
que sea menor que el precio anual.
*/


/*
2.- Hacer un Trigger que si en la tabla contratos que al insertar o modificar ponemos la fecha inicio
posterior a la fecha fin que las intercambie.
*/
GO
CREATE TRIGGER trigger2_UPDATE on contratos AFTER UPDATE, INSERT
AS
	BEGIN
		DECLARE @Fecha1 DATE, @Fecha2 DATE;
		SET @Fecha1=(SELECT fechaInicio FROM inserted)
		SET @Fecha2=(SELECT fechaFin FROM inserted)

IF @Fecha1>@Fecha2
	BEGIN
		DECLARE @codDnionie INTEGER, @codEquipo INTEGER, @precioAnual INTEGER, @precioResicion INTEGER, @codContrato INTEGER;
		SET @codDnionie=(SELECT codDnionie FROM inserted)
		SET @codEquipo=(SELECT codEquipo FROM inserted)
		SET @precioAnual=(SELECT precioAnual FROM inserted)
		SET @precioResicion=(SELECT precioResicion FROM inserted)
		SET @codContrato=(SELECT codContrato FROM inserted)

		UPDATE contratos SET @codDnionie=@codDnionie, @codEquipo=@codEquipo, fechaInicio=@Fecha2,
		fechaFin=@Fecha1, @precioAnual=@precioAnual, @precioResicion=@precioResicion
		WHERE codContrato=@codContrato;

		END
END
GO

-- EJECUCIÓN
-- INSERT INTO contratos VALUES ('44444444S', 6, '8/11/2019', '6/4/2013', 123, 123556);
-- SELECT * FROM contratos;

/*
3.- Hacer un Trigger que no permita eliminar ninguna liga.
*/
GO
CREATE TRIGGER trigger3_NOBORRAR
	ON ligas FOR DELETE
AS
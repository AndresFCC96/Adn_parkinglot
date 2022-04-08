CREATE TABLE lugar (
    id_lugar        NUMERIC NOT NULL,
    estado          VARCHAR(1) NOT NULL,
    CONSTRAINT PK_1 PRIMARY KEY ( id_lugar )
);

CREATE TABLE vehiculo (
    id_vehiculo NUMERIC NOT NULL NOT NULL,
    placa       VARCHAR(7) NOT NULL,
    tipo        VARCHAR(1) NOT NULL,
	lugar_vehiculo        NUMERIC NOT NULL,
    fecha_entrada        DATE NOT NULL,
    fecha_salida         DATE NOT NULL,
    valor_parqueo        NUMERIC NOT NULL,
    valor_base      NUMERIC NOT NULL,
    CONSTRAINT PK_2 PRIMARY KEY ( id_vehiculo ),
    CONSTRAINT FK_1 FOREIGN KEY ( lugar_vehiculo ) REFERENCES lugar ( id_lugar )
);

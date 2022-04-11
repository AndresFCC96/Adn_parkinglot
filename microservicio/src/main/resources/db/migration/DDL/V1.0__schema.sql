CREATE TABLE lugar (
    id_lugar        SERIAL NOT NULL,
    estado          VARCHAR(1) NOT NULL,
    CONSTRAINT PK_1 PRIMARY KEY ( id_lugar )
);

CREATE TABLE vehiculo (
    id_vehiculo SERIAL NOT NULL,
    placa       VARCHAR(7) NOT NULL,
    tipo        VARCHAR(1) NOT NULL,
	lugar_vehiculo        INTEGER NOT NULL,
    fecha_entrada        timestamp with time zone NOT NULL,
    fecha_salida         timestamp with time zone NOT  NULL,
    valor_parqueo        NUMERIC NOT NULL,
    CONSTRAINT PK_2 PRIMARY KEY ( id_vehiculo ),
    CONSTRAINT FK_14 FOREIGN KEY ( lugar_vehiculo ) REFERENCES lugar ( id_lugar )
);

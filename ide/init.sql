-- Usar la base de datos
\c chapatupoint;

-- Tabla Categoria
CREATE TABLE Categoria (
    idCategoria SERIAL PRIMARY KEY,
    NombreCategoria VARCHAR(100) NOT NULL
);

INSERT INTO Categoria (NombreCategoria) VALUES ('RESTAURANTES');
INSERT INTO Categoria (NombreCategoria) VALUES ('DISCOTECAS');
INSERT INTO Categoria (NombreCategoria) VALUES ('HOTELES');

-- Tabla Locales
CREATE TABLE Locales (
    IdLocal SERIAL PRIMARY KEY,
    NombreLocal VARCHAR(100) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    valoracion VARCHAR(100),
    horaCierre VARCHAR(100) NOT NULL,
    idCategoria INT NOT NULL,
    horaApertura VARCHAR(100) NOT NULL,
    estado VARCHAR(10) NOT NULL,
    descripcion VARCHAR(1000) NOT NULL,
    Link_Instagram VARCHAR(500),
    Link_Facebook VARCHAR(500),
    Link_Maps VARCHAR(500),
    ubicacion VARCHAR(100),
    ruc VARCHAR(15),
    diassemana VARCHAR(100),
    rutapdf VARCHAR(100),
    CONSTRAINT Locales_Categoria_idCategoria_fk FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
);

-- Tabla Publicaciones
CREATE TABLE Publicaciones (
    IdPublicacion SERIAL PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    FechaPublicacion VARCHAR(100) NOT NULL,
    idLocal INT NOT NULL,
    estado VARCHAR(10),
    CONSTRAINT Publicaciones_Locales_IdLocal_fk FOREIGN KEY (idLocal) REFERENCES Locales(IdLocal)
);

-- Tabla TipoUsuario
CREATE TABLE TipoUsuario (
    idTipoUsuario INT PRIMARY KEY,
    Descripcion VARCHAR(100)
);

INSERT INTO TipoUsuario (idTipoUsuario, Descripcion) VALUES (0, 'CLIENTE');
INSERT INTO TipoUsuario (idTipoUsuario, Descripcion) VALUES (1, 'ADMINISTRADOR');

-- Tabla Usuario
CREATE TABLE Usuario (
    IdUsuario SERIAL PRIMARY KEY,
    NombreUsuario VARCHAR(20) NOT NULL,
    ApellidosUsuario VARCHAR(25) NOT NULL,
    dniusuario VARCHAR(8) NOT NULL,
    Telefono VARCHAR(12) NOT NULL,
    ClaveUsuario VARCHAR(60),
    correo VARCHAR(100) NOT NULL,
    idTipoUsuario INT,
    CONSTRAINT Usuario_TipoUsuario_idTipoUsuario_fk FOREIGN KEY (idTipoUsuario) REFERENCES TipoUsuario(idTipoUsuario)
);

-- Tabla Comentarios
CREATE TABLE Comentarios (
    idcomentario SERIAL PRIMARY KEY,
    idpublicacion INT NOT NULL,
    idusuario INT NOT NULL,
    contenido VARCHAR(400) NOT NULL,
    fechacomentario VARCHAR(10) NOT NULL,
    CONSTRAINT Comentarios_Publicaciones_IdPublicacion_fk FOREIGN KEY (idpublicacion) REFERENCES Publicaciones(IdPublicacion),
    CONSTRAINT Comentarios_Usuario_IdUsuario_fk FOREIGN KEY (idusuario) REFERENCES Usuario(IdUsuario)
);

-- Tabla Solicitudes
CREATE TABLE Solicitudes (
    idSolicitud SERIAL PRIMARY KEY,
    IdUsuario INT NOT NULL,
    estado VARCHAR(100) NOT NULL,
    comentario_admin VARCHAR(100),
    idLocal INT NOT NULL,
    fechaRegistro VARCHAR(100),
    CONSTRAINT Solicitudes_Usuario_IdUsuario_fk FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario),
    CONSTRAINT Solicitudes_Locales_IdLocal_fk FOREIGN KEY (idLocal) REFERENCES Locales(IdLocal)
);

-- Tabla Imagenes
CREATE TABLE Imagenes (
    idImagenes SERIAL PRIMARY KEY,
    idSolicitud INT NOT NULL,
    imagen BYTEA NOT NULL,
    descripcion VARCHAR(300),
    CONSTRAINT Imagenes_Solicitudes_idSolicitud_fk FOREIGN KEY (idSolicitud) REFERENCES Solicitudes(idSolicitud)
);

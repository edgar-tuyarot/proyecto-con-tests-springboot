-- Tabla para Categorías de Productos
CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla para Clientes
CREATE TABLE clientes (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    telefono VARCHAR(11) NOT NULL,
    celular VARCHAR(13),
    email VARCHAR(50),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla para Estados de Pedido
CREATE TABLE estados_pedido (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla para Productos
CREATE TABLE productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    precio NUMERIC(19, 2) NOT NULL, -- Usar NUMERIC para precisión monetaria
    stock INTEGER NOT NULL DEFAULT 0,
    sku VARCHAR(255) UNIQUE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion TIMESTAMP WITHOUT TIME ZONE,
    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_categoria
        FOREIGN KEY(categoria_id)
        REFERENCES categorias(id)
);

-- Tabla para Pedidos
CREATE TABLE pedidos (
    id BIGSERIAL PRIMARY KEY,
    total DOUBLE PRECISION DEFAULT 0.0,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    estado_pedido_id BIGINT,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_estado_pedido
        FOREIGN KEY(estado_pedido_id)
        REFERENCES estados_pedido(id),
    CONSTRAINT fk_cliente
        FOREIGN KEY(cliente_id)
        REFERENCES clientes(id)
);

-- Tabla de unión para la relación Muchos a Muchos entre Productos y Pedidos
CREATE TABLE productos_pedidos (
    id BIGSERIAL PRIMARY KEY,
    cantidad INTEGER NOT NULL,
    precio_unitario NUMERIC(19, 2),
    producto_id BIGINT,
    pedido_id BIGINT,
    CONSTRAINT fk_producto
        FOREIGN KEY(producto_id)
        REFERENCES productos(id),
    CONSTRAINT fk_pedido
        FOREIGN KEY(pedido_id)
        REFERENCES pedidos(id)
);
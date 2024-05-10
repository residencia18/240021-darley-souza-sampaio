CREATE TABLE IF NOT EXISTS public.usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(32) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS public.postagem (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(11) NOT NULL UNIQUE,
    texto VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.amigos (
    usuario_id INT,
    amigo_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (amigo_id) REFERENCES usuario(id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_id, amigo_id)
);
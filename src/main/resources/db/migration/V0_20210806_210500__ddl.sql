CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL,
    username VARCHAR(128),
    password VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT NOT NULL,
    system_role VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT  fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);


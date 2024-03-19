CREATE TYPE gender_type AS ENUM('male', 'female');

CREATE TABLE users (
  id     UUID        NOT NULL PRIMARY KEY,
  name_  VARCHAR     NOT NULL,
  cpf    VARCHAR(11) NOT NULL UNIQUE,
  gender gender_type,
  street VARCHAR,
  state_ VARCHAR
);

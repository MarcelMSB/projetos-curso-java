﻿CREATE TABLE PUBLIC.PESSOAS
(
  ID INTEGER NOT NULL,
  NOME CHARACTER VARYING(255) NOT NULL,
  SEXO CHARACTER VARYING(1) NOT NULL,
  CPF CHARACTER VARYING(14),
  RG CHARACTER VARYING(14),
  DATA_NASCIMENTO DATE NOT NULL,
  TELEFONE_FIXO CHARACTER VARYING(20),
  TELEFONE_CELULAR CHARACTER VARYING(120),
  EMAIL CHARACTER VARYING(120),
CONSTRAINT PK_PESSOAS PRIMARY KEY (ID)
) WITH (OIDS = FALSE);
ALTER TABLE PUBLIC.PESSOAS OWNER TO POSTGRES;

CREATE SEQUENCE PUBLIC.SEQ_PESSOAS
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE PUBLIC.SEQ_PESSOAS OWNER TO POSTGRES;
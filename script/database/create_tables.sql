-- Script para criacao das tabelas

CREATE TABLE public.teste1
(
  codigo integer NOT NULL,
  nome character varying(255),
  CONSTRAINT teste1_pkey PRIMARY KEY (codigo)
);
CREATE TABLE public.teste
(
  codigo integer NOT NULL,
  nome character varying(255),
  teste1 integer,
  CONSTRAINT teste_pkey PRIMARY KEY (codigo),
  CONSTRAINT testefg FOREIGN KEY (teste1)
      REFERENCES public.teste1 (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

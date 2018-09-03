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

CREATE TABLE public.produto
(
  codigo serial,
  descricao character varying(40),
  CONSTRAINT produto_pkey PRIMARY KEY (codigo)
);

CREATE TABLE public.compra
(
  codigo serial,
  data date,
  CONSTRAINT comprateste_pkey PRIMARY KEY (codigo)
);

CREATE TABLE public.itemcompra
(
  compra integer NOT NULL,
  quantidade integer,
  produto integer NOT NULL,
  CONSTRAINT itemcompra_pkey PRIMARY KEY (compra, produto),
  CONSTRAINT itemcompra_compra_fkey FOREIGN KEY (compra)
      REFERENCES public.compra (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT itemcompra_produto_fkey FOREIGN KEY (produto)
      REFERENCES public.produto (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.venda
(
  codigo serial,
  data date,
  CONSTRAINT pk_venda PRIMARY KEY (codigo)
);

CREATE TABLE public.itemvenda
(
  codigo serial,
  venda integer NOT NULL,
  quantidade integer,
  produto integer NOT NULL,
  CONSTRAINT itemvenda_pkey PRIMARY KEY (codigo),
  CONSTRAINT itemcompra_produto_fkey FOREIGN KEY (produto)
      REFERENCES public.produto (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT itemvenda_fg FOREIGN KEY (venda)
      REFERENCES public.venda (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
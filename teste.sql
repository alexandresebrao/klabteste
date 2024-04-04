CREATE TABLE public.produtos (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	nome varchar NOT NULL,
	quantidades int4 DEFAULT 0 NOT NULL,
	defeitos int4 NULL,
	preco numeric NULL,
	CONSTRAINT produtos_pkey PRIMARY KEY (id)
);


CREATE TABLE public.vendas (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	comprador varchar NOT NULL,
	produto_id int4 NOT NULL,
	quantidades int4 NOT NULL,
	total_venda numeric NOT NULL,
	CONSTRAINT vendas_pk PRIMARY KEY (id)
);

ALTER TABLE public.vendas ADD CONSTRAINT vendas_produtos_fk FOREIGN KEY (produto_id) REFERENCES public.produtos(id);


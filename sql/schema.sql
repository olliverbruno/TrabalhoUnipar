
CREATE TABLE cliente (
    cpf character(11) NOT NULL,
    nome character varying(150) NOT NULL,
    idade integer NOT NULL,
    dividas_abertas boolean NOT NULL DEFAULT false,
    CONSTRAINT cliente_pkey PRIMARY KEY (cpf)
);

CREATE TABLE funcionario (
    cpf character(11) NOT NULL,
    nome character varying(150) NOT NULL,
    idade integer NOT NULL,
    salario numeric(12,2) NOT NULL,
    turno character varying(30) NOT NULL,
    habilidade character varying(30) NOT NULL,
    CONSTRAINT funcionario_pkey PRIMARY KEY (cpf)
);


CREATE TABLE caixa_da_agua (
    id serial PRIMARY KEY,
    marca character varying(100) NOT NULL,
    modelo character varying(100) NOT NULL,
    dimensao double precision[] NOT NULL,
    cor character varying(30) NOT NULL,
    material character varying(30) NOT NULL,
    formato character varying(100) NOT NULL,
    preco numeric(12,2) NOT NULL,
    quantidade integer NOT NULL DEFAULT 1
);


CREATE TABLE servico (
    id serial PRIMARY KEY,
    cliente_cpf character(11) NOT NULL,
    instalador_cpf character(11) NOT NULL,
    preco numeric(12,2) NOT NULL,
    data_instalacao date NOT NULL,
    tipo character varying(20) NOT NULL,
    CONSTRAINT servico_cliente_cpf_fkey FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf),
    CONSTRAINT servico_instalador_cpf_fkey FOREIGN KEY (instalador_cpf) REFERENCES funcionario(cpf)
);


CREATE TABLE movimentacao (
    id serial PRIMARY KEY,
    valor numeric(12,2) NOT NULL,
    data_movimentacao timestamp NOT NULL,
    descricao character varying(255) NOT NULL,
    pagador character varying(150) NOT NULL,
    recebedor character varying(150) NOT NULL,
    responsavel character varying(150) NOT NULL
);

CREATE TABLE fornecedor (
    cpf character(14) NOT NULL,
    nome character varying(150) NOT NULL,
    idade integer NOT NULL,
    produto_fornecido character varying(150) NOT NULL,
    CONSTRAINT fornecedor_pkey PRIMARY KEY (cpf)
);

CREATE TABLE auditor (
    cpf character(11) NOT NULL,
    nome character varying(150) NOT NULL,
    idade integer NOT NULL,
    registro_profissional character varying(50) NOT NULL,
    CONSTRAINT auditor_pkey PRIMARY KEY (cpf)
);

CREATE TABLE compra (
    id serial PRIMARY KEY,
    fornecedor_cpf character(14) NOT NULL,
    caixa_da_agua_id integer NOT NULL,
    quantidade integer NOT NULL,
    preco numeric(12,2) NOT NULL,
    data_compra date NOT NULL,
    CONSTRAINT compra_fornecedor_cpf_fkey FOREIGN KEY (fornecedor_cpf) REFERENCES fornecedor(cpf),
    CONSTRAINT compra_caixa_da_agua_id_fkey FOREIGN KEY (caixa_da_agua_id) REFERENCES caixa_da_agua(id)
);

CREATE TABLE venda (
    id serial PRIMARY KEY,
    cliente_cpf character(11) NOT NULL,
    caixa_da_agua_id integer NOT NULL,
    quantidade integer NOT NULL,
    preco numeric(12,2) NOT NULL,
    data_venda date NOT NULL,
    CONSTRAINT venda_cliente_cpf_fkey FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf),
    CONSTRAINT venda_caixa_da_agua_id_fkey FOREIGN KEY (caixa_da_agua_id) REFERENCES caixa_da_agua(id)
);

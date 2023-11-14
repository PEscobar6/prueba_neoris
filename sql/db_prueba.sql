--
-- PostgreSQL database cluster dump
--

-- Started on 2023-11-14 18:28:50

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE test;
ALTER ROLE test WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3

-- Started on 2023-11-14 18:28:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2023-11-14 18:28:52

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3

-- Started on 2023-11-14 18:28:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2023-11-14 18:28:53

--
-- PostgreSQL database dump complete
--

--
-- Database "test-db" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3

-- Started on 2023-11-14 18:28:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3391 (class 1262 OID 16384)
-- Name: test-db; Type: DATABASE; Schema: -; Owner: test
--

CREATE DATABASE "test-db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE "test-db" OWNER TO test;

\connect -reuse-previous=on "dbname='test-db'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16488)
-- Name: account; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.account (
    numero_cuenta character varying(20),
    tipo_cuenta character varying(50),
    saldo_inicial numeric(38,2),
    estado character varying(1),
    cuenta_id bigint NOT NULL,
    client_id bigint NOT NULL,
    saldo_actual numeric(38,2)
);


ALTER TABLE public.account OWNER TO test;

--
-- TOC entry 220 (class 1259 OID 16701)
-- Name: account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.account_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_account_id_seq OWNER TO test;

--
-- TOC entry 3392 (class 0 OID 0)
-- Dependencies: 220
-- Name: account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.account_account_id_seq OWNED BY public.account.cuenta_id;


--
-- TOC entry 217 (class 1259 OID 16680)
-- Name: client; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.client (
    cliente_id bigint NOT NULL,
    contrasena character varying(255),
    estado character varying(255),
    usuario_id bigint
);


ALTER TABLE public.client OWNER TO test;

--
-- TOC entry 216 (class 1259 OID 16679)
-- Name: cliente_cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.cliente_cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_cliente_id_seq OWNER TO test;

--
-- TOC entry 3393 (class 0 OID 0)
-- Dependencies: 216
-- Name: cliente_cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.cliente_cliente_id_seq OWNED BY public.client.cliente_id;


--
-- TOC entry 215 (class 1259 OID 16495)
-- Name: transaction; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.transaction (
    movimiento_id bigint NOT NULL,
    fecha date NOT NULL,
    tipo_movimiento character varying(50) NOT NULL,
    valor numeric(38,2),
    saldo numeric(38,2),
    account_id bigint NOT NULL
);


ALTER TABLE public.transaction OWNER TO test;

--
-- TOC entry 221 (class 1259 OID 16749)
-- Name: transaction_movimiento_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.transaction_movimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_movimiento_id_seq OWNER TO test;

--
-- TOC entry 3394 (class 0 OID 0)
-- Dependencies: 221
-- Name: transaction_movimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.transaction_movimiento_id_seq OWNED BY public.transaction.movimiento_id;


--
-- TOC entry 219 (class 1259 OID 16689)
-- Name: users; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    direccion character varying(255),
    edad integer NOT NULL,
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);


ALTER TABLE public.users OWNER TO test;

--
-- TOC entry 218 (class 1259 OID 16688)
-- Name: usuario_persona_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.usuario_persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_persona_id_seq OWNER TO test;

--
-- TOC entry 3395 (class 0 OID 0)
-- Dependencies: 218
-- Name: usuario_persona_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.usuario_persona_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 3214 (class 2604 OID 16702)
-- Name: account cuenta_id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.account ALTER COLUMN cuenta_id SET DEFAULT nextval('public.account_account_id_seq'::regclass);


--
-- TOC entry 3216 (class 2604 OID 16683)
-- Name: client cliente_id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.client ALTER COLUMN cliente_id SET DEFAULT nextval('public.cliente_cliente_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 16750)
-- Name: transaction movimiento_id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.transaction ALTER COLUMN movimiento_id SET DEFAULT nextval('public.transaction_movimiento_id_seq'::regclass);


--
-- TOC entry 3217 (class 2604 OID 16692)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.usuario_persona_id_seq'::regclass);


--
-- TOC entry 3378 (class 0 OID 16488)
-- Dependencies: 214
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.account (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cuenta_id, client_id, saldo_actual) FROM stdin;
478758	Ahorro	2000.00	A	1	4	1425.00
225487	Corriente	100.00	A	2	5	100.00
585545	Corriente	2000.00	A	3	4	2000.00
496825	Ahorros	540.00	A	4	5	540.00
495878	Ahorros	0.00	A	5	6	0.00
\.


--
-- TOC entry 3381 (class 0 OID 16680)
-- Dependencies: 217
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.client (cliente_id, contrasena, estado, usuario_id) FROM stdin;
3	$2a$10$XZ7Yb4cB/PUeH697fJmRmezAh0koU2tLeLhYGPq2c6DPe1lb/cna.	ACTIVO	1
4	$2a$10$gIkIP80y/9cMGM9nb737UOXWqCkzOrVXknkOAeFOKXz7JYFo85qoe	ACTIVO	2
5	$2a$10$ItQV7ezFPnkjVgfQK0g3Yeizaa/CdaJ/q9ZeoWwUjSoYas9nuW7W2	ACTIVO	3
6	$2a$10$T6UWMgGVpl3YTeM.pNSfye0/XiZ2dGPe.RjJC8m5.WAX8MapTSPVC	ACTIVO	4
\.


--
-- TOC entry 3379 (class 0 OID 16495)
-- Dependencies: 215
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.transaction (movimiento_id, fecha, tipo_movimiento, valor, saldo, account_id) FROM stdin;
\.


--
-- TOC entry 3383 (class 0 OID 16689)
-- Dependencies: 219
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.users (user_id, direccion, edad, genero, identificacion, nombre, telefono) FROM stdin;
1	Neiva	24	M	1075321220	Pablo Escobar	3170273990
2	Otavalo sn y principal	30	M	1234567890	Jose Lema	098254785
3	 Amazonas y NNUU	26	F	7845623	Marianela Montalvo	097548965
4	13 junio y Equinoccial	28	M	7845623	Juan Osorio	098874587
\.


--
-- TOC entry 3396 (class 0 OID 0)
-- Dependencies: 220
-- Name: account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.account_account_id_seq', 7, true);


--
-- TOC entry 3397 (class 0 OID 0)
-- Dependencies: 216
-- Name: cliente_cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.cliente_cliente_id_seq', 6, true);


--
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 221
-- Name: transaction_movimiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.transaction_movimiento_id_seq', 7, true);


--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 218
-- Name: usuario_persona_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.usuario_persona_id_seq', 4, true);


--
-- TOC entry 3219 (class 2606 OID 16717)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (cuenta_id);


--
-- TOC entry 3228 (class 2606 OID 16687)
-- Name: client cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id);


--
-- TOC entry 3222 (class 2606 OID 16494)
-- Name: account cuenta_numero_cuenta_key; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT cuenta_numero_cuenta_key UNIQUE (numero_cuenta);


--
-- TOC entry 3225 (class 2606 OID 16499)
-- Name: transaction movimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT movimientos_pkey PRIMARY KEY (movimiento_id);


--
-- TOC entry 3232 (class 2606 OID 16696)
-- Name: users usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3220 (class 1259 OID 16725)
-- Name: client_id_idx; Type: INDEX; Schema: public; Owner: test
--

CREATE INDEX client_id_idx ON public.account USING btree (client_id) WITH (deduplicate_items='true');


--
-- TOC entry 3223 (class 1259 OID 16731)
-- Name: fki_account_user_id_user_user_id_fk; Type: INDEX; Schema: public; Owner: test
--

CREATE INDEX fki_account_user_id_user_user_id_fk ON public.account USING btree (client_id);


--
-- TOC entry 3229 (class 1259 OID 16724)
-- Name: fki_u; Type: INDEX; Schema: public; Owner: test
--

CREATE INDEX fki_u ON public.client USING btree (usuario_id);


--
-- TOC entry 3226 (class 1259 OID 16713)
-- Name: transaction_client_id_idx; Type: INDEX; Schema: public; Owner: test
--

CREATE INDEX transaction_client_id_idx ON public.transaction USING btree (account_id);


--
-- TOC entry 3230 (class 1259 OID 16718)
-- Name: user_id_idx; Type: INDEX; Schema: public; Owner: test
--

CREATE INDEX user_id_idx ON public.client USING btree (usuario_id) WITH (deduplicate_items='true');


--
-- TOC entry 3233 (class 2606 OID 16726)
-- Name: account account_user_id_user_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_user_id_user_user_id_fk FOREIGN KEY (client_id) REFERENCES public.client(cliente_id) NOT VALID;


--
-- TOC entry 3235 (class 2606 OID 16719)
-- Name: client client_user_id_user_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_user_id_user_user_id_fk FOREIGN KEY (usuario_id) REFERENCES public.users(user_id) NOT VALID;


--
-- TOC entry 3234 (class 2606 OID 16744)
-- Name: transaction fk6g20fcr3bhr6bihgy24rq1r1b; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk6g20fcr3bhr6bihgy24rq1r1b FOREIGN KEY (account_id) REFERENCES public.account(cuenta_id);


-- Completed on 2023-11-14 18:28:55

--
-- PostgreSQL database dump complete
--

-- Completed on 2023-11-14 18:28:55

--
-- PostgreSQL database cluster dump complete
--


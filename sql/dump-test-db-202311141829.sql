PGDMP     7                
    {            test-db    15.3 (Debian 15.3-1.pgdg120+1)    15.3 +    <           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            =           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            >           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16384    test-db    DATABASE     t   CREATE DATABASE "test-db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE "test-db";
                test    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            @           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16488    account    TABLE       CREATE TABLE public.account (
    numero_cuenta character varying(20),
    tipo_cuenta character varying(50),
    saldo_inicial numeric(38,2),
    estado character varying(1),
    cuenta_id bigint NOT NULL,
    client_id bigint NOT NULL,
    saldo_actual numeric(38,2)
);
    DROP TABLE public.account;
       public         heap    test    false    4            �            1259    16701    account_account_id_seq    SEQUENCE        CREATE SEQUENCE public.account_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.account_account_id_seq;
       public          test    false    214    4            A           0    0    account_account_id_seq    SEQUENCE OWNED BY     P   ALTER SEQUENCE public.account_account_id_seq OWNED BY public.account.cuenta_id;
          public          test    false    220            �            1259    16680    client    TABLE     �   CREATE TABLE public.client (
    cliente_id bigint NOT NULL,
    contrasena character varying(255),
    estado character varying(255),
    usuario_id bigint
);
    DROP TABLE public.client;
       public         heap    test    false    4            �            1259    16679    cliente_cliente_id_seq    SEQUENCE        CREATE SEQUENCE public.cliente_cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cliente_cliente_id_seq;
       public          test    false    4    217            B           0    0    cliente_cliente_id_seq    SEQUENCE OWNED BY     P   ALTER SEQUENCE public.cliente_cliente_id_seq OWNED BY public.client.cliente_id;
          public          test    false    216            �            1259    16495    transaction    TABLE        CREATE TABLE public.transaction (
    movimiento_id bigint NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    tipo_movimiento character varying(50) NOT NULL,
    valor numeric(38,2),
    saldo numeric(38,2),
    account_id bigint NOT NULL
);
    DROP TABLE public.transaction;
       public         heap    test    false    4            �            1259    16749    transaction_movimiento_id_seq    SEQUENCE     �   CREATE SEQUENCE public.transaction_movimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.transaction_movimiento_id_seq;
       public          test    false    215    4            C           0    0    transaction_movimiento_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.transaction_movimiento_id_seq OWNED BY public.transaction.movimiento_id;
          public          test    false    221            �            1259    16689    users    TABLE       CREATE TABLE public.users (
    user_id bigint NOT NULL,
    direccion character varying(255),
    edad integer NOT NULL,
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE public.users;
       public         heap    test    false    4            �            1259    16688    usuario_persona_id_seq    SEQUENCE        CREATE SEQUENCE public.usuario_persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.usuario_persona_id_seq;
       public          test    false    219    4            D           0    0    usuario_persona_id_seq    SEQUENCE OWNED BY     L   ALTER SEQUENCE public.usuario_persona_id_seq OWNED BY public.users.user_id;
          public          test    false    218            �           2604    16702    account cuenta_id    DEFAULT     w   ALTER TABLE ONLY public.account ALTER COLUMN cuenta_id SET DEFAULT nextval('public.account_account_id_seq'::regclass);
 @   ALTER TABLE public.account ALTER COLUMN cuenta_id DROP DEFAULT;
       public          test    false    220    214            �           2604    16683    client cliente_id    DEFAULT     w   ALTER TABLE ONLY public.client ALTER COLUMN cliente_id SET DEFAULT nextval('public.cliente_cliente_id_seq'::regclass);
 @   ALTER TABLE public.client ALTER COLUMN cliente_id DROP DEFAULT;
       public          test    false    217    216    217            �           2604    16750    transaction movimiento_id    DEFAULT     �   ALTER TABLE ONLY public.transaction ALTER COLUMN movimiento_id SET DEFAULT nextval('public.transaction_movimiento_id_seq'::regclass);
 H   ALTER TABLE public.transaction ALTER COLUMN movimiento_id DROP DEFAULT;
       public          test    false    221    215            �           2604    16692    users user_id    DEFAULT     s   ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.usuario_persona_id_seq'::regclass);
 <   ALTER TABLE public.users ALTER COLUMN user_id DROP DEFAULT;
       public          test    false    218    219    219            2          0    16488    account 
   TABLE DATA           x   COPY public.account (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cuenta_id, client_id, saldo_actual) FROM stdin;
    public          test    false    214   �0       5          0    16680    client 
   TABLE DATA           L   COPY public.client (cliente_id, contrasena, estado, usuario_id) FROM stdin;
    public          test    false    217   <1       3          0    16495    transaction 
   TABLE DATA           f   COPY public.transaction (movimiento_id, fecha, tipo_movimiento, valor, saldo, account_id) FROM stdin;
    public          test    false    215   82       7          0    16689    users 
   TABLE DATA           c   COPY public.users (user_id, direccion, edad, genero, identificacion, nombre, telefono) FROM stdin;
    public          test    false    219   �2       E           0    0    account_account_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.account_account_id_seq', 7, true);
          public          test    false    220            F           0    0    cliente_cliente_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cliente_cliente_id_seq', 6, true);
          public          test    false    216            G           0    0    transaction_movimiento_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.transaction_movimiento_id_seq', 7, true);
          public          test    false    221            H           0    0    usuario_persona_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.usuario_persona_id_seq', 4, true);
          public          test    false    218            �           2606    16717    account account_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (cuenta_id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            test    false    214            �           2606    16687    client cliente_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.client
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id);
 =   ALTER TABLE ONLY public.client DROP CONSTRAINT cliente_pkey;
       public            test    false    217            �           2606    16494     account cuenta_numero_cuenta_key 
   CONSTRAINT     d   ALTER TABLE ONLY public.account
    ADD CONSTRAINT cuenta_numero_cuenta_key UNIQUE (numero_cuenta);
 J   ALTER TABLE ONLY public.account DROP CONSTRAINT cuenta_numero_cuenta_key;
       public            test    false    214            �           2606    16499    transaction movimientos_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT movimientos_pkey PRIMARY KEY (movimiento_id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT movimientos_pkey;
       public            test    false    215            �           2606    16696    users usuario_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.users
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (user_id);
 <   ALTER TABLE ONLY public.users DROP CONSTRAINT usuario_pkey;
       public            test    false    219            �           1259    16725    client_id_idx    INDEX     f   CREATE INDEX client_id_idx ON public.account USING btree (client_id) WITH (deduplicate_items='true');
 !   DROP INDEX public.client_id_idx;
       public            test    false    214            �           1259    16731 #   fki_account_user_id_user_user_id_fk    INDEX     \   CREATE INDEX fki_account_user_id_user_user_id_fk ON public.account USING btree (client_id);
 7   DROP INDEX public.fki_account_user_id_user_user_id_fk;
       public            test    false    214            �           1259    16724    fki_u    INDEX     >   CREATE INDEX fki_u ON public.client USING btree (usuario_id);
    DROP INDEX public.fki_u;
       public            test    false    217            �           1259    16713    transaction_client_id_idx    INDEX     W   CREATE INDEX transaction_client_id_idx ON public.transaction USING btree (account_id);
 -   DROP INDEX public.transaction_client_id_idx;
       public            test    false    215            �           1259    16718    user_id_idx    INDEX     d   CREATE INDEX user_id_idx ON public.client USING btree (usuario_id) WITH (deduplicate_items='true');
    DROP INDEX public.user_id_idx;
       public            test    false    217            �           2606    16726 '   account account_user_id_user_user_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_user_id_user_user_id_fk FOREIGN KEY (client_id) REFERENCES public.client(cliente_id) NOT VALID;
 Q   ALTER TABLE ONLY public.account DROP CONSTRAINT account_user_id_user_user_id_fk;
       public          test    false    3228    214    217            �           2606    16719 %   client client_user_id_user_user_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_user_id_user_user_id_fk FOREIGN KEY (usuario_id) REFERENCES public.users(user_id) NOT VALID;
 O   ALTER TABLE ONLY public.client DROP CONSTRAINT client_user_id_user_user_id_fk;
       public          test    false    3232    219    217            �           2606    16744 '   transaction fk6g20fcr3bhr6bihgy24rq1r1b    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk6g20fcr3bhr6bihgy24rq1r1b FOREIGN KEY (account_id) REFERENCES public.account(cuenta_id);
 Q   ALTER TABLE ONLY public.transaction DROP CONSTRAINT fk6g20fcr3bhr6bihgy24rq1r1b;
       public          test    false    215    3219    214            2   m   x�M�A
�P����0%/f��x��X��?6Uw��c�=:�cY����*&48��Č�$����h�2�B����.�U%~�X�ޠ��\:"	�Ǘ ��)�ADv1�&R      5   �   x�5�Kr�0  е9k�?��t���(����%�k�t��]����`��)u�hkuo����P�L����MKi<O�t�ތ�y�\����z��"���,�������ץQ��-2Q��dĞ}W��E��0;�;Jh����p���O�<W��!��X���&���1\Y7���+�|��Ǜ	"�|L�ȧpJ�Q�>LZ�T]?���5X��a�^�$p'[%~��l-r\���
 �YBUW      3   E   x�3�4202�54�54Q00�#Nǌ���|N]SsS= ���Bs��Und` VfSn�O9L5��=... >�m      7   �   x�5�1��0��z|
�`e��]R�ڄm�h�(�Q�1��_SP?}�0Lyc@=XC��E4�˗Y���r��%��R2
�����X�~���˘�<�3�t>t����I�L7�"O1(zw�������7Pl
��d.�̺���y��)����<X��k����o�E�1�2�V�\V.�Xe�o#�I���R��>h     
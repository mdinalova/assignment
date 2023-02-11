CREATE DATABASE "TestingDataUser"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


CREATE TABLE public.oauth_access_token
(
  token_id character varying(256),
  token bytea,
  authentication_id character varying(256) NOT NULL,
  user_name character varying(256),
  client_id character varying(256),
  authentication bytea,
  refresh_token character varying(256),
  CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
);


CREATE TABLE public.oauth_client_details
(
  client_id character varying(256) NOT NULL,
  resource_ids character varying(256),
  client_secret character varying(256),
  scope character varying(256),
  authorized_grant_types character varying(256),
  web_server_redirect_uri character varying(256),
  authorities character varying(256),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information character varying(4096),
  autoapprove character varying(256),
  CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
);


CREATE TABLE public.oauth_refresh_token
(
  token_id character varying(256),
  token bytea,
  authentication bytea
);


CREATE TABLE public.users
(
  username character varying(256) NOT NULL,
  password character varying(256) NOT NULL,
  enabled boolean NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (username)
);


CREATE TABLE public.groups
(
  id bigint NOT NULL,
  group_name character varying(50) NOT NULL,
  CONSTRAINT groups_pkey PRIMARY KEY (id)
);


CREATE TABLE public.group_authorities
(
  id bigint NOT NULL,
  group_id bigint NOT NULL,
  authority character varying(50) NOT NULL,
  CONSTRAINT pk_group_authorities PRIMARY KEY (id),
  CONSTRAINT fk_group_authorities_group FOREIGN KEY (group_id)
      REFERENCES public.groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE public.group_members
(
  id bigint NOT NULL,
  username character varying(50) NOT NULL,
  group_id bigint NOT NULL,
  authorities_id bigint NOT NULL,
  CONSTRAINT group_members_pkey PRIMARY KEY (id),
  CONSTRAINT fk_group_members_authorities FOREIGN KEY (authorities_id)
      REFERENCES public.group_authorities (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_group_members_group FOREIGN KEY (group_id)
      REFERENCES public.groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

insert into users (username, password, enabled) VALUES ('halsey.grande@gmail.com', '$2a$10$Cw7LdBHdz0nMekJkKAY60uFEwktSGhTqi0xAuzx0Tg1/v2Ht0HmQ6', true);

insert into groups (id, group_name) VALUES (1, 'SysAdmin');

insert into group_authorities (id, group_id, authority) VALUES (1, 1, 'SysAdmin');

insert into group_members (id, username, group_id, authorities_id) VALUES (1, 'halsey.grande@gmail.com', 1, 1);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('demoapps', 'allstore', '$2a$10$Cw7LdBHdz0nMekJkKAY60uFEwktSGhTqi0xAuzx0Tg1/v2Ht0HmQ6', 'read,write', 'authorization_code,check_token,refresh_token,password', '', 'ROLE_CLIENT', 43200, 43200, '', '');

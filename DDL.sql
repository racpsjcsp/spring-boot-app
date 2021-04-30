drop schema if exists forum;

create schema forum;

use forum;

drop user 'user'@'localhost';
create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on forum.* to user@'localhost';

  CREATE TABLE usuario (
  id bigint unsigned NOT NULL auto_increment,
  nome varchar(50) NOT NULL,
  email varchar(30) NOT NULL,
  nickname varchar(20) NOT NULL,
  admin boolean NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE post (
  id bigint unsigned NOT NULL auto_increment,
  titulo varchar(50) NOT NULL,
  conteudo varchar(500) NOT NULL,
  usuario bigint unsigned DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_usuario (usuario),
  CONSTRAINT fk_usuario FOREIGN KEY (usuario) REFERENCES usuario (id)
);


CREATE TABLE comentario (
  id bigint unsigned NOT NULL auto_increment,
  conteudo varchar(500) NOT NULL,
  post bigint unsigned DEFAULT NULL,
  usuario bigint unsigned DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_post (post),
  KEY fk_usuario2 (usuario),
  CONSTRAINT fk_post FOREIGN KEY (post) REFERENCES post (id),
  CONSTRAINT fk_usuario2 FOREIGN KEY (usuario) REFERENCES usuario (id)
);


INSERT INTO usuario (nome, email, nickname) 
VALUES ("Rafael", "rafa@gmail.com", "Rafa");

INSERT INTO post (titulo, conteudo, usuario)
VALUES ("Meu Post", "Conteudo do Post...", 1);

INSERT INTO comentario (conteudo, post, usuario)
VALUES ("Conteudo do Comentario...", 1, 1);
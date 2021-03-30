
create schema forum;

use forum;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on forum.* to user@'localhost';

  CREATE TABLE usuario (
  id bigint unsigned NOT NULL auto_increment,
  nome varchar(50) NOT NULL,
  email varchar(30) NOT NULL,
  nickname varchar(20) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE post (
  id bigint unsigned NOT NULL auto_increment,
  titulo varchar(50) NOT NULL,
  conteudo varchar(500) NOT NULL,
  usuario int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_usuario (usuario),
  CONSTRAINT fk_usuario FOREIGN KEY (usuario) REFERENCES usuario (id)
);


CREATE TABLE comentario (
  id bigint unsigned NOT NULL auto_increment,
  conteudo varchar(500) NOT NULL,
  post int(11) DEFAULT NULL,
  usuario int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_post (post),
  KEY fk_usuario2 (usuario),
  CONSTRAINT fk_post FOREIGN KEY (post) REFERENCES post (id),
  CONSTRAINT fk_usuario2 FOREIGN KEY (usuario) REFERENCES usuario (id)
);
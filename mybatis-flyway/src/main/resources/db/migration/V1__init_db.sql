use jl_mybatis_flyway;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  birthday DATE DEFAULT null,
  birth_place varchar(100) not null,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user (first_name,last_name,birthday,birth_place) values('Dursun','KOC', STR_TO_DATE('02/10/1982', '%m/%d/%Y'),'Erzincan');

insert into user (first_name,last_name,birthday,birth_place) values('Durseeun','KeeOC', STR_TO_DATE('05/10/1982', '%m/%d/%Y'),'Erzeeincan');

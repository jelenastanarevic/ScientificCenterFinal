insert into Editor (id,first_name,last_name,city,country,email, password,head_editor,scientific_field_id) values (11,'Jelena', 'Stanarevic', 'Novi Sad', 'Serbia', 'jelenastanarevic95@gmail.com', 'jelena123',1,1);
insert into Author (id,first_name,last_name,city,country,email, password) values
(11, 'Marko', 'Ivanovic', 'Novi Sad', 'Serbia', 'js.lenchi@gmail.com', 'marko123');
insert into User (id, first_name, last_name, city, country, email, password)
  values (11, 'Smiljana', 'Dragoljevic', 'Berlin', 'Germany', 'smiljana.dragoljevic-1@gmail.com', 'Smiljana1234++');
insert into User (id, first_name, last_name, city, country, email, password)
  values (12, 'Marko', 'Markovic', 'Oslo', 'Norway', 'marko@gmail.com', 'Marko1234++');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (13, 'Marko', 'Ivanovic', 'Novi Sad', 'Serbia', 'js.lenchi@gmail.com', 'marko123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (14, 'Jelena', 'Stanarevic', 'Novi Sad', 'Serbia', 'jelenastanarevic95@gmail.com', 'jelena123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (15, 'Pera', 'Peric', 'Sombor', 'Serbia', 'pera.peric@gmail.com', 'pera123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (16 , 'John', 'Hobs', 'New York', 'USA', 'john.hobs@gmail.com', 'john123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (17, 'Philip', 'Meier', 'Berlin', 'Germany', 'philip.meier@gmail.com', 'philip123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (18, 'Michael', 'Alling', 'Hamburg', 'Germany', 'michael@gmail.com', 'michael123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (19, 'Milan', 'Milanovic', 'New York', 'USA', 'milan.milanovic@gmail.com', 'milan123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (20, 'Jovan', 'Jovanovic', 'New York', 'USA', 'jovan.jovanovic@gmail.com', 'milan123');
 insert into User (id, first_name, last_name, city, country, email, password)
  values (21, 'Dusan', 'Dusanic', 'New York', 'USA', 'dusan.dusaniv@gmail.com', 'milan123');
 
  
   insert into Editor (id, first_name, last_name, city, country, email, password,head_editor,scientific_field_id)
  values (12, 'Pera', 'Peric', 'Sombor', 'Serbia', 'pera.peric@gmail.com', 'pera123',0,1);
 insert into Editor (id, first_name, last_name, city, country, email, password,head_editor,scientific_field_id)
  values (13, 'John', 'Hobs', 'New York', 'USA', 'john.hobs@gmail.com', 'john123',0,1);

  
   insert into Editor (id, first_name, last_name, city, country, email, password,head_editor,scientific_field_id)
  values (14, 'Philip', 'Meier', 'Berlin', 'Germany', 'philip.meier@gmail.com', 'philip123',0,2);
 insert into Editor (id, first_name, last_name, city, country, email, password,head_editor,scientific_field_id)
  values (15, 'Michael', 'Alling', 'Hamburg', 'Germany', 'michael@gmail.com', 'michael123',0,2);

 insert into Reviewer (id, first_name, last_name, city, country, email, password,scientific_field_id)
  values (11, 'Milan', 'Milanovic', 'New York', 'USA', 'milan.milanovic@gmail.com', 'milan123',1);
 insert into Reviewer (id, first_name, last_name, city, country, email, password,scientific_field_id)
  values (12, 'Jovan', 'Jovanovic', 'New York', 'USA', 'jovan.jovanovic@gmail.com', 'milan123',1);
 insert into Reviewer (id, first_name, last_name, city, country, email, password,scientific_field_id)
  values (13, 'Dusan', 'Dusanic', 'New York', 'USA', 'dusan.dusaniv@gmail.com', 'milan123',2);

insert into Magazine (id, title,issn,head_editor_id) values (1,'Galileu', '1415-9856',11);
insert into Magazine (id, title,issn,head_editor_id) values (2,'Da znaem poveche', '0350-1221',11);
insert into Magazine (id, title,issn,head_editor_id) values (3,'New Scientist', '0262-4079',11);
insert into Magazine (id, title,issn,head_editor_id) values (4,'EuroScientist', '1073-8584',11);
insert into Magazine (id, title,issn,head_editor_id) values (5,'Airone', '1124-8343',11);

insert into scientific_field (id,scientific_field_name) values (1, 'Medicine');
insert into scientific_field (id,scientific_field_name) values (2, 'Information Technology');
insert into scientific_field (id,scientific_field_name) values (3, 'Biomedicine');
insert into scientific_field (id,scientific_field_name) values (4, 'Mathematics');
insert into scientific_field (id,scientific_field_name) values (5, 'Statistics');
insert into scientific_field (id,scientific_field_name) values (6, 'Physical medicine');
insert into scientific_field (id,scientific_field_name) values (7, 'History');


insert into article (id,author_id,title,key_words,abstract_description,magazine_id,accepted,scientific_field_id,price,file_name) values (111,1,'Cilindros de 4 mil anos têm medidas exatas de Stonehenge, diz arqueólogo','Reino Unido,rocha,Universidade de Manchester,Arqueólogos,Stonehenge','Quando ligadas por um fio, antigas estruturas têm a mesma distância de uma rocha para outra do famoso monumento localizado no Reino Unido',1,1,7,2,'techdoc.pdf');
insert into article (id,author_id,title,key_words,abstract_description,magazine_id,accepted,scientific_field_id,price) values (112,1,'Седна Мечо на чина','Учител,строгият, Дрозд,Омеси','Подчертай глаголите с две прави линии. Четвъртото и петото изречения превърни във въпросителни',2,1,7,3);


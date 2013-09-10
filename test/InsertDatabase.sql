INSERT INTO Categories (cat_name, cat_url) VALUES ('Test','http//Test'),('Test2','http//Test2'),('Test3','http//Test3'),('Test4','http//Test4') ;

INSERT INTO Auteurs (aut_nom , aut_prenom ,aut_url , aut_email)
VALUES ('NomTest','PrenomTest','http//NomTest','NomTest@NomTest.test'),('NomTest2','PrenomTest2','http//NomTest2','NomTest2@NomTest.test');

INSERT INTO Articles (cat_id, art_titre , art_chapeau,art_contenu, art_date, aut_id ,art_en_ligne)
VALUES (1,'Test','Test du blog','cet article parme du test','2013-07-22',1,true),(2,'Test2','Test2 du blog','cet article parme du test2','2013-07-23',1,true),
(3,'Test3','Test3 du blog','cet article parme du test3','2013-07-22',2,true),(2,'Test4','Test4 du blog','cet article parme du test4','2013-07-24',1,false),
(3,'Test5','Test5 du blog','cet article parme du test5','2013-07-20',2,true),(2,'Test6','Test6 du blog','cet article parme du test6','2013-06-04',1,true);

INSERT INTO Commentaires (art_id , com_nom , com_email ,com_texte, com_date , com_validation)
VALUES (1,'TOTO11','TOTO11@TOTO.TO','cet article parle du test a toto11','2013-07-22',true),(1,'TOTO12','TOTO12@TOTO.TO','cet article parle du test a toto12','2013-07-22',true),
(1,'TOTO13','TOTO13@TOTO.TO','cet article parle du test a toto13','2013-07-22',true),(2,'TOTO21','TOTO21@TOTO.TO','cet article parle du test a toto21','2013-07-23',false),
(2,'TOTO22','TOTO@TOTO.TO','cet article parle du test a toto22','2013-07-23',true),(1,'TOTO14','TOTO14@TOTO.TO','cet article parle du test a toto14','2013-07-23',true);


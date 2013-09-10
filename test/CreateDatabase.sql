CREATE DATABASE testblogspring;
USE testblogspring;

CREATE TABLE Articles (art_id INT NOT NULL AUTO_INCREMENT,cat_id INT NOT NULL, art_titre VARCHAR(250) NOT NULL, art_chapeau TEXT NOT NULL,
art_contenu TEXT NOT NULL, art_date TIMESTAMP NOT NULL, aut_id INT NOT NULL, art_en_ligne BOOLEAN , PRIMARY KEY (art_id));
CREATE TABLE Commentaires (com_id INT NOT NULL AUTO_INCREMENT, art_id INT NOT NULL, com_nom VARCHAR(250) NOT NULL, com_email VARCHAR(250) NOT NULL,
com_texte TEXT NOT NULL, com_date TIMESTAMP NOT NULL, com_validation BOOLEAN NOT NULL, PRIMARY KEY (com_id));
CREATE TABLE Categories (cat_id INT NOT NULL AUTO_INCREMENT, cat_name VARCHAR(250) NOT NULL, cat_url VARCHAR(250) NOT NULL, PRIMARY KEY (cat_id));
CREATE TABLE Auteurs (aut_id INT NOT NULL AUTO_INCREMENT , aut_nom VARCHAR(250) NOT NULL, aut_prenom VARCHAR(250) NOT NULL,
aut_url VARCHAR(250) NOT NULL, aut_email VARCHAR(250) NOT NULL, PRIMARY KEY (aut_id));

ALTER TABLE Articles ADD CONSTRAINT fk_Articles_Categories_cat_id FOREIGN KEY (cat_id) REFERENCES Categories (cat_id);
ALTER TABLE Articles ADD CONSTRAINT fk_Articles_Auteurs_aut_id FOREIGN KEY (aut_id) REFERENCES Auteurs (aut_id);
ALTER TABLE Commentaires ADD CONSTRAINT fk_Commentaires_Articles_art_id FOREIGN KEY (art_id) REFERENCES Articles (art_id);

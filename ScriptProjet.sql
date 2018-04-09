DROP TABLE Livre CASCADE CONSTRAINT PURGE
/
CREATE TABLE Document
(IdDoc INTEGER CONSTRAINT PK_IDLIVRE PRIMARY KEY,
NomDoc VARCHAR(30),
AuteurDoc VARCHAR(30),
DocEmprunt INTEGER,
DocType VARCHAR(30)
)
/

DROP TABLE Utilisateur CASCADE CONSTRAINT PURGE
/
CREATE TABLE Utilisateur
(IdUSER INTEGER CONSTRAINT PK_IDUSER PRIMARY KEY,
NomUser VARCHAR(30),
Password VARCHAR(30),
DateNaissance DATE,
UserType VARCHAR(30)
)
/

ALTER TABLE Document
ADD CONSTRAINT DOC_FAKEBOOLEAN
CHECK (DocEmprunt = 1 OR DocEmprunt = 0);

ALTER TABLE Utilisateur
ADD CONSTRAINT UTILISATEUR_TYPE
CHECK (UserType = 'Abonne' OR UserType = 'Bibliothecaire');

ALTER TABLE Document
ADD CONSTRAINT DOCUMENT_TYPE
CHECK (DocType = 'Livre' OR DocType = 'CD' OR DocType = 'DVD');

DROP SEQUENCE seq_doc;
/

CREATE SEQUENCE seq_doc
INCREMENT BY 1
START WITH 0
MINVALUE 0
CACHE 30
ORDER;

INSERT INTO Utilisateur (IdUSER, NomUser, Password, UserType) VALUES(0, 'JeanAdmin', '123', 'Admin');
INSERT INTO Utilisateur (IdUSER, NomUser, Password, UserType) VALUES(1, 'JeanAbo', '123', 'Abonné');
INSERT INTO Utilisateur (IdUSER, NomUser, Password, UserType) VALUES(2, 'JeanBib', '123', 'Bibliothécaire');

INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'LivrePHP', 'Jean', 0, 'Livre');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'LivreCSS', 'Jean', 0, 'Livre');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'LivreHTML', 'Jean', 0, 'Livre');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'PHP : Armageddon', 'Spielberg', 0, 'DVD');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'PHP 2 : Le Retour', 'Spielberg', 0, 'DVD');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'PHP & JS : Apocalypse', 'Spielberg', 0, 'DVD');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, '10 tubes de Java', 'Jean', 0, 'CD');
INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES(seq_doc.nextval, 'CD inconnu', 'Inconuu', 0, 'CD');


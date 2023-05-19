USE `impflix`;
DROP PROCEDURE IF EXISTS generarDocument;


DELIMITER //

CREATE PROCEDURE generarDocument (IN _id_client int, OUT document TEXT)
BEGIN 
    DECLARE _nom VARCHAR(50);
    DECLARE _DNI VARCHAR(9);
    DECLARE _edat_data_naixement DATE;
    DECLARE _adreça VARCHAR(50);
    DECLARE _nacionalitat VARCHAR(20);
    DECLARE _email VARCHAR(50);
    DECLARE _telefon INT;
    DECLARE _num_tarjeta VARCHAR(50);
    DECLARE _num_compte_banc VARCHAR(50);
    DECLARE _preu_cost DOUBLE;

    SELECT nom, DNI, edat_data_naixement, adreça, nacionalitat, email, telefon, num_tarjeta, num_compte_banc
    INTO _nom, _DNI, _edat_data_naixement, _adreça, _nacionalitat, _email, _telefon, _num_tarjeta, _num_compte_banc
    FROM client
    WHERE id_client = _id_client;

    SELECT SUM(fact.preu_cost)
    INTO _preu_cost
    FROM factura fact, client cli, compte comp
    WHERE fact.id_compte = comp.id_compte
    AND comp.id_client = cli.id_client
    AND cli.id_client = _id_client;

    SET document = CONCAT('Id:', _id_client,'\n',
                        'Nom:', _nom,'\n',
                        'DNI:', _DNI,'\n',
                        'Data Naixement:', _edat_data_naixement,'\n',
                        'Adreça:', _adreça,'\n',
                        'Nacionalitat:', _nacionalitat,'\n',
                        'Email:', _email,'\n',
                        'Telefon:', _telefon,'\n',
                        'Num tarjeta:', _num_tarjeta,'\n',
                        'Num compte banc:', _num_compte_banc,'\n',
                        'Cost total:', _preu_cost);

END //

CALL generarDocument(4,@document);
SELECT @document;
USE `impflix`;
CREATE USER if NOT EXISTS directorContinguts@'localhost' IDENTIFIED by 'Fat/3232';
CREATE USER if NOT EXISTS administratiu@'localhost' IDENTIFIED by 'Fat/3232';
CREATE USER if NOT EXISTS accesClient@'localhost' IDENTIFIED by 'Fat/3232';

CREATE ROLE if NOT exists directiu with admin directorContinguts@localhost;
CREATE ROLE if NOT exists operador with admin administratiu@localhost;
CREATE ROLE if NOT exists client with admin accesClient@localhost;

GRANT SELECT ON hellflix.director TO directiu;
GRANT SELECT ON hellflix.`client` TO client;

GRANT ALL PRIVILEGES ON directiu TO directorContinguts@'localhost' IDENTIFIED BY 'Fat/3232'
WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON operador TO administratiu@'localhost' IDENTIFIED BY 'Fat/3232'
WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON client TO accesClient@'localhost' IDENTIFIED BY 'Fat/3232'
WITH GRANT OPTION;

SELECT * FROM mysql.user;

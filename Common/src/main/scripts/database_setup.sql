CREATE TABLESPACE cs360_tablespace DATAFILE SIZE 10M AUTOEXTEND ON;
CREATE TEMPORARY TABLESPACE cs360_tablespace_temp TEMPFILE SIZE 5M AUTOEXTEND ON;

CREATE USER cs360_user IDENTIFIED BY "password" DEFAULT TABLESPACE cs360_tablespace TEMPORARY TABLESPACE cs360_tablespace_temp;

GRANT CONNECT TO cs360_user;
GRANT CONNECT, RESOURCE, DBA TO cs360_user;
GRANT CREATE TABLE TO cs360_user;
GRANT UNLIMITED TABLESPACE TO cs360_user;

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    'bookid' int(11) NOT NULL AUTO_INCREMENT,
    'name'   varchar(255) DEFAULT NULL,
    'author' varchar(255) DEFAULT NULL,
    PRIMARY KEY ('bookid')
)
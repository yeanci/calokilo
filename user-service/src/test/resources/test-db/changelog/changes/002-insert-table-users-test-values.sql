--liquibase formatted SQL

--changeset yeanci:1
INSERT INTO users (first_name, email, password, birth_date, height)
VALUES
    ('Albert', 'albert@gmail.com', 'myNameIs@lbert', '1980-12-01', 180),
    ('Bernard', 'bernard@gmail.com', 'myNameIsBern@d', '1981-12-01', 140),
    ('Charles', 'charles@gmail.com', 'myNameIsCh@rles', '1982-12-01', 185),
    ('Davis', 'davis@gmail.com', 'myNameIsD@vis', '1989-12-01', 168),
    ('Elton', 'elton@gmail.com', 'myNameIs3lt0n', '1980-12-01', 180),
    ('Fabrice', 'fabrice@gmail.com', 'myNameIsF@brice', '1975-12-01', 105),
    ('Gerard', 'gerard@gmail.com', 'myNameIsG3r@rD', '1998-12-01', 169),
    ('Harry', 'harry@gmail.com', 'myNameIsH@rry', '1996-12-01', 180),
    ('Irene', 'irene@gmail.com', 'myNameIs1r3n3', '1946-08-01', 196),
    ('Jacques', 'jacques@gmail.com', 'myNameIsJ@cqu3s', '1932-11-25', 164);
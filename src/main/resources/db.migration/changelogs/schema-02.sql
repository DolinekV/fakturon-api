ALTER TABLE customer
    ADD cin VARCHAR(255);

ALTER TABLE customer
    ADD city VARCHAR(255);

ALTER TABLE customer
    ADD email VARCHAR(255);

ALTER TABLE customer
    ADD number VARCHAR(255);

ALTER TABLE customer
    ADD phone VARCHAR(255);

ALTER TABLE customer
    ADD state VARCHAR(255);

ALTER TABLE customer
    ADD street VARCHAR(255);

ALTER TABLE customer
    ADD tin VARCHAR(255);

ALTER TABLE customer
    ADD zip VARCHAR(255);

CREATE UNIQUE INDEX IX_pk_customer ON customer (id, id);
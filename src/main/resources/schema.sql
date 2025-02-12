CREATE TABLE waste_category (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE disposal_guidelines (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    guideline VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE recycling_tips (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    tip VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
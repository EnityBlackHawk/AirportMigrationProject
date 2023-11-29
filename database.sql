CREATE TABLE manufacturer (
                              id INT PRIMARY KEY,
                              name TEXT
);

CREATE TABLE airline(
                        id INT PRIMARY KEY,
                        name TEXT
);

CREATE TABLE aircraft (
                          id INT PRIMARY KEY,
                          type TEXT UNIQUE,
                          airline INT REFERENCES airline(id),
                          manufacturer_id INT REFERENCES manufacturer(id),
                          registration TEXT UNIQUE,
                          max_passengers INT
);

CREATE TABLE airport (
                         id VARCHAR(3) PRIMARY KEY,
                         name TEXT,
                         city TEXT,
                         country TEXT
);

CREATE TABLE flight (
                        number TEXT PRIMARY KEY,
                        airport_from varchar(3) REFERENCES airport(id),
                        airport_to varchar(3) REFERENCES airport(id),
                        departure_time_scheduled TIMESTAMP,
                        departure_time_actual TIMESTAMP,
                        arrival_time_scheduled TIMESTAMP,
                        arrival_time_actual TIMESTAMP,
                        gate INT,
                        connects_to TEXT REFERENCES flight(number)
);

CREATE TABLE agencies(
                         username VARCHAR PRIMARY KEY,
                         agency_name VARCHAR,
                         password VARCHAR
);

CREATE TABLE clients(
                        client_id INTEGER PRIMARY KEY,
                        client_name VARCHAR,
                        address varchar
);

CREATE TABLE users(
                        user_id INTEGER PRIMARY KEY,
                        username VARCHAR,
                        password VARCHAR
);


CREATE TABLE flights(
                        flight_id INTEGER PRIMARY KEY,
                        destination VARCHAR,
                        departure_date_time timestamp,
                        airport varchar,
                        available_seats integer
);

CREATE TABLE bookings(
                         booking_id INTEGER PRIMARY KEY,
                         flight_id INTEGER,
                         client_id INTEGER,
                         clients_name VARCHAR,
                         CONSTRAINT fk_flight
                             FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
                         CONSTRAINT fk_client
                             FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

-- CREATE TABLE tickets(
--                         ticket_id INTEGER PRIMARY KEY,
--                         flight_id INTEGER,
--                         client_id INTEGER,
--                         CONSTRAINT fk_flight
--                             FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
--                         CONSTRAINT fk_client
--                             FOREIGN KEY (client_id) REFERENCES clients(client_id)
-- );
--
-- CREATE TABLE payments(
--                         payment_id INTEGER PRIMARY KEY,
--                         ticket_id INTEGER,
--                         amount_paid INTEGER,
--                         CONSTRAINT fk_ticket
--                             FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
-- );
--
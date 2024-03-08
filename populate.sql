INSERT INTO agencies(username, agency_name, password) VALUES
                    ('agency1', 'Agency One', 'password1'),
                    ('agency2', 'Agency Two', 'password2'),
                    ('agency3', 'Agency Three', 'password3');

INSERT INTO clients(client_id, client_name, address) VALUES
                    (1, 'Ana Maria', 'Trifoiului 3'),
                    (2, 'Ion Pop', 'Bobului 2'),
                    (3, 'Maria Anghel', 'Spiclului 1'),
                    (4, 'Alex Popescu', '1 Decembrie'),
                    (5, 'Cristina Ionescu', 'Mihai Viteazu'),
                    (6, 'Alexandru Pop', 'Closca 9');

INSERT INTO users(user_id, username, password) VALUES
                    (1, 'mihai_ilie', '123'),
                    (2, 'adriana_dragan', '1234'),
                    (3, 'alexandru_pop', '12345');

-- INSERT INTO flights(flight_id, destination, departure_date_time, airport, available_seats) VALUES
--                     (1, 'Charles de Gaulle', '2023-03-17 21:00', 'Avram Iancu', 180),
--                     (2, 'Bucuresti', '2023-03-18 14:00', 'Timisoara', 70),
--                     (3, 'Barcelona-El Prat', '2023-06-03 10:00', 'Schipol', 120),
--                     (4, 'Roma Fiumicino', '2022-05-06 07:00', 'London Heathrow', 125),
--                     (5, 'New York', '2024-03-10 08:00:00', 'JFK', 200),
--                     (6, 'Los Angeles', '2024-03-11 09:00:00', 'LAX', 150),
--                     (7, 'Chicago', '2024-03-12 10:00:00', 'New York', 180);

INSERT INTO bookings(booking_id, flight_id, client_id, clients_name) VALUES
                   (1, 1, 1, 'Andrei,Rares'),
                   (2, 2, 2, 'Vlad'),
                   (3, 1, 3, 'Adrian,Mihai'),
                   (4, 7, 4, 'Andreea,Ionut,Cristina'),
                   (5, 5, 5, 'Carmemn,Ioana'),
                   (6, 6, 6, 'Raluca,George'),
                   (7, 7, 7, 'Tudor,Daria,Bogdan');
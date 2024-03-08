package org.example.persistence.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Booking;
import org.example.model.Client;
import org.example.model.Flight;
import org.example.persistence.IBookingRepository;
import org.example.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BookingDbRepository implements IBookingRepository {

    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public BookingDbRepository(Properties properties) {
        logger.info("Initialising BookingRepository with properties {}", properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Booking findOne(Long aLong) {
        logger.traceEntry();
        String sql = "SELECT * FROM bookings b " +
                "INNER JOIN flights f on f.flight_id = b.flight_id " +
                "INNER JOIN clients c on c.client_id = b.client_id WHERE b.booking_id = ?";

        Booking booking = null;

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long bookingId = resultSet.getLong("booking_id");
                System.out.println(bookingId);

                Long idClient = resultSet.getLong("client_id");
                String clientName = resultSet.getString("client_name");
                String address = resultSet.getString("address");

                Client client = new Client(idClient,clientName, address);
                client.setId(idClient);

                Long idFlight = resultSet.getLong("flight_id");
                String destination = resultSet.getString("destination");
                LocalDateTime departureDateTime = resultSet
                        .getTimestamp("departure_date_time").toLocalDateTime();
                String airport = resultSet.getString("airport");
                int availableSeats = resultSet.getInt("available_seats");

                Flight flight = new Flight(idFlight,destination, departureDateTime, airport, availableSeats);
                flight.setId(idFlight);

                String passengersString = resultSet.getString("clients_name");

                List<String> passengers = Arrays.asList(passengersString.split(","));

                booking = new Booking(bookingId,flight, client, passengers);
                booking.setId(bookingId);

            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB find one booking"+e);
            e.printStackTrace();
        }

        logger.traceExit();
        return booking;
    }

    @Override
    public Iterable<Booking> getAll() {
        logger.traceEntry();
        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings b " +
                "INNER JOIN flights f on f.flight_id = b.flight_id " +
                "INNER JOIN clients c on c.client_id = b.client_id";

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idBooking = resultSet.getLong("booking_id");
                System.out.println(idBooking);

                Long idClient = resultSet.getLong("client_id");
                String clientName = resultSet.getString("client_name");
                String address = resultSet.getString("address");
                Client client = new Client(idClient,clientName, address);
                client.setId(idClient);

                Long idFlight = resultSet.getLong("flight_id");
                String destination = resultSet.getString("destination");
                LocalDateTime departureDateTime = resultSet
                        .getTimestamp("departure_date_time").toLocalDateTime();
                String airport = resultSet.getString("airport");
                int availableSeats = resultSet.getInt("available_seats");
                Flight flight = new Flight(idFlight,destination, departureDateTime, airport, availableSeats);
                flight.setId(idFlight);

                String passengersString = resultSet.getString("clients_name");

                List<String> passengers = Arrays.asList(passengersString.split(","));

                Booking booking = new Booking(idBooking,flight, client, passengers);
                booking.setId(idBooking);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB get all booking"+e);
            e.printStackTrace();
        }

        logger.traceExit();
        return bookings;
    }

    @Override
    public void clear() {

    }

    @Override
    public Booking add(Booking entity) {
        logger.traceEntry();
        String sql = "INSERT INTO bookings VALUES (?, ?, ?, ?)";

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setLong(2, entity.getFlight().getId());
            preparedStatement.setLong(3, entity.getClient().getId());
            String clientsList = String.join(",", entity.getPassengers());
            preparedStatement.setString(4, clientsList);

            preparedStatement.executeUpdate();
            entity = null;
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB la add booking"+ e);
            e.printStackTrace();
        }

        logger.traceExit();
        return entity;
    }

    @Override
    public void update(Booking entity) {
        logger.traceEntry();
        String sql = "UPDATE bookings SET flight_id = ?, client_id = ?, clients_name = ? WHERE booking_id = ?";
        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getFlight().getId());
            preparedStatement.setLong(2, entity.getClient().getId());
            String clientsList = String.join(",", entity.getPassengers());
            preparedStatement.setString(3, clientsList);
            preparedStatement.setLong(4, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB la update booking"+ e);
            e.printStackTrace();

    }
        logger.traceExit();
    }

    @Override
    public void delete(Long id) {

        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("delete from bookings where booking_id=?")){
            preSmt.setLong(1,id);
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la delete booking "+e);
        }
        logger.traceExit(id);

    }

    @Override
    public int getNumberOfBookingsForFlight(Long id) {
        logger.traceEntry();
        int numberOfBookings = 0;

        List<Booking> flights = (List<Booking>) getAll();

        for (Booking booking : flights) {
            if (booking.getFlight().getId().equals(id)) {
                numberOfBookings += booking.getPassengers().size() + 1;
            }
        }
        logger.traceExit();
        return numberOfBookings;
    }


//    public Long getLowestAvbId() {
//        String sql = "SELECT MAX(id) FROM bookings";
//        Long id = null;
//
//        try (
//                Connection connection = jdbcUtils.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql)
//        ) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                id = resultSet.getLong(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }


}
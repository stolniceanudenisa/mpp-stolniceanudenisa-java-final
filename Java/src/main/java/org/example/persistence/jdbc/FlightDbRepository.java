package org.example.persistence.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Flight;
import org.example.persistence.IFlightRepository;
import org.example.utils.JdbcUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FlightDbRepository implements IFlightRepository {
    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public FlightDbRepository(Properties properties) {
        logger.info("Initialising FlightRepository with properties {}", properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Flight findOne(Long aLong) {
        logger.traceEntry();
        String sql = "SELECT * FROM flights WHERE flight_id = ?";

        Flight flight = null;

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("flight_id");
                String destination = resultSet.getString("destination");
                LocalDateTime departureDateTime = resultSet
                        .getTimestamp("departure_date_time").toLocalDateTime();

                String airport = resultSet.getString("airport");
                int availableSeats = resultSet.getInt("available_seats");


                flight = new Flight(id, destination, departureDateTime, airport, availableSeats);
                flight.setId(aLong);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB find one flight"+e);
            e.printStackTrace();
        }

        logger.traceExit();
        return flight;
    }

//    @Override
//    public Iterable<Flight> getAll() {
//        logger.traceEntry();
//        Connection con = jdbcUtils.getConnection();
//        List<Flight> flightList = new ArrayList<>();
//        try(PreparedStatement preSmt=con.prepareStatement("select * from flights")){
//            try(ResultSet resultSet = preSmt.executeQuery()){
//                while(resultSet.next()){
////                    Long id = resultSet.getLong("flight_id");
////                    String destination = resultSet.getString("destination");
////                    LocalDateTime departureDateTime = resultSet
////                            .getTimestamp("departure_date_time").toLocalDateTime();
////                    String airport = resultSet.getString("airport");
////                    int availableSeats = resultSet.getInt("available_seats");
////                    Flight flight = new Flight(id,destination, departureDateTime, airport, availableSeats);
////                    flight.setId(id);
////                    flightList.add(flight);
//
//                    Long id = resultSet.getLong("flight_id");
//                    String destination = resultSet.getString("destination");
//                    LocalDateTime departureDateTime = resultSet
//                            .getTimestamp("departure_date_time").toLocalDateTime();
//                    String airport = resultSet.getString("airport");
//                    int availableSeats = resultSet.getInt("available_seats");
//
//                    Flight flight = new Flight(id,destination, departureDateTime, airport, availableSeats);
//                    flight.setId(id);
//
//                    flightList.add(flight);
//
//
//                }
//            }
//        } catch(SQLException e){
//            logger.error(e);
//            System.err.println("Error DB get all flights"+e);
//        }
//        logger.traceExit(flightList);
//        return flightList;
//    }
@Override
public Iterable<Flight> getAll() {
    logger.traceEntry();
    Connection con = jdbcUtils.getConnection();
    List<Flight> flightList = new ArrayList<>();
    try (PreparedStatement preSmt = con.prepareStatement("SELECT * FROM flights")) {
        try (ResultSet resultSet = preSmt.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("flight_id");
                String destination = resultSet.getString("destination");
//                Timestamp timestamp = resultSet.getTimestamp("departure_date_time");
//
//                // Parsing timestamp to LocalDateTime with explicit format
//                LocalDateTime departureDateTime = timestamp.toLocalDateTime();
                LocalDateTime departureDateTime = resultSet
                        .getTimestamp("departure_date_time").toLocalDateTime();

                String airport = resultSet.getString("airport");
                int availableSeats = resultSet.getInt("available_seats");

                Flight flight = new Flight(id, destination, departureDateTime, airport, availableSeats);
                flight.setId(id);

                flightList.add(flight);
            }
        }
    } catch (SQLException e) {
        logger.error(e);
        System.err.println("Error fetching flights from DB: " + e);
    }
    logger.traceExit(flightList);
    return flightList;
}

    @Override
    public void clear() {

    }


    @Override
    public Flight add(Flight entity) {
        String sql = "INSERT INTO flights VALUES (?, ?, ?, ?, ?)";
        logger.traceEntry("Saving flight {} ",entity);

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getDestination());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDepartureDateTime()));
//            preparedStatement.setString(3, entity.getDepartureDateTime().toString());

            preparedStatement.setString(4, entity.getAirport());
            preparedStatement.setInt(5, entity.getAvailableSeats());

            preparedStatement.executeUpdate();
            entity = null;
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB la add flight"+ e);
            e.printStackTrace();
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public void delete(Long id) {
        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("delete from flights where flight_id=?")){
            preSmt.setLong(1,id);
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la delete flight "+e);
        }
        logger.traceExit(id);

    }


    @Override
    public void update(Flight entity) {
        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("update flights set destination = ?,departure_date_time = ?, airport = ?, available_seats = ? where flight_id=?")){
            preSmt.setString(1,entity.getDestination());
            preSmt.setTimestamp(3, Timestamp.valueOf(entity.getDepartureDateTime()));
            preSmt.setString(3,entity.getAirport());
            preSmt.setInt(4,entity.getAvailableSeats());
            preSmt.setLong(3,entity.getId());

            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la update flight "+e);
        }
        logger.traceExit(entity);

    }


    @Override
    public Iterable<Flight> findFlightByDestinationAndDate(String destination, LocalDateTime dateTime) {
        logger.traceEntry("findFlightByDestinationAndDate: destination = {}, dateTime = {}", destination, dateTime);
        LocalDateTime start = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0, 0);
        LocalDateTime end = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 23, 59);
        String sql = "SELECT * FROM flights WHERE destination = ? AND departure_date_time BETWEEN ? AND ?";

        try (
                Connection connection = jdbcUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, destination);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(end));

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Flight> flights = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String destination1 = resultSet.getString("destination");
                LocalDateTime departureDateTime = resultSet
                        .getTimestamp("departure_date_time").toLocalDateTime();
                String airport = resultSet.getString("airport");
                int availableSeats = resultSet.getInt("available_seats");

                Flight flight = new Flight(id,destination1, departureDateTime, airport, availableSeats);
                flight.setId(id);

                flights.add(flight);
            }
            logger.traceExit();
            return flights;
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();
        return null;
    }
}
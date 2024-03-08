package org.example.persistence.jdbc;

import org.example.model.Client;
import org.example.persistence.IClientRepository;
import org.example.utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientDbRepository implements IClientRepository {

    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public ClientDbRepository(Properties properties) {
        logger.info("Initialising ClientRepository with properties {}", properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Client findOne(Long aLong) {
        logger.traceEntry();
        String sql = "SELECT * FROM clients WHERE client_id = ?";

        Client client = null;

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("client_id");
                String clientName = resultSet.getString("client_name");
                String address = resultSet.getString("address");

                client = new Client(id, clientName, address);
                client.setId(aLong);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB find one client"+e);
            e.printStackTrace();
        }

        logger.traceExit();
        return client;
    }

    @Override
    public Iterable<Client> getAll() {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        List<Client> clientList = new ArrayList<>();
        try(PreparedStatement preSmt=con.prepareStatement("select * from clients")){
            try(ResultSet resultSet = preSmt.executeQuery()){
                while(resultSet.next()){
                    Long id = resultSet.getLong("client_id");
                    String clientName = resultSet.getString("client_name");
                    String address = resultSet.getString("address");
                    Client client = new Client(id,clientName,address);
                    clientList.add(client);
                }
            }
        } catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB get all client"+e);
        }
        logger.traceExit(clientList);
        return clientList;
    }

    @Override
    public void clear() {

    }


    @Override
    public Client add(Client entity) {
        String sql = "INSERT INTO clients VALUES (?, ?, ?)";
        logger.traceEntry("Saving client {} ",entity);

        try (

                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getAddress());

            preparedStatement.executeUpdate();
            entity = null;
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB la add client"+ e);
            e.printStackTrace();
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public void delete(Long id) {
        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("delete from clients where client_id=?")){
            preSmt.setLong(1,id);
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la delete client "+e);
        }
        logger.traceExit(id);

    }


    @Override
    public void update(Client entity) {
        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("update clients set client_name = ?,address = ? where client_id=?")){
            preSmt.setString(1,entity.getName());
            preSmt.setString(2,entity.getAddress());
            preSmt.setLong(3,entity.getId());
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la update client "+e);
        }
        logger.traceExit(entity);

    }



//    public Long getLowestAvbId() {
//        String sql = "SELECT MAX(id) FROM clients";
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
package org.example.persistence.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.User;
import org.example.persistence.IUserRepository;
import org.example.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDbRepository implements IUserRepository {

    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public UserDbRepository(Properties properties) {
        logger.info("Initialising UserRepository with properties {}", properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public User findOne(Long aLong) {
        logger.traceEntry();
        String sql = "SELECT * FROM users WHERE user_id = ?";

        User user = null;

        try (
                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(id, username, password);
                user.setId(aLong);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB find one user"+e);
            e.printStackTrace();
        }

        logger.traceExit();
        return user;
    }

    @Override
    public Iterable<User> getAll() {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        List<User> userList = new ArrayList<>();
        try(PreparedStatement preSmt=con.prepareStatement("select * from users")){
            try(ResultSet resultSet = preSmt.executeQuery()){
                while(resultSet.next()){
                    Long id = resultSet.getLong("user_id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    User user = new User(id,username,password);
                    userList.add(user);
                }
            }
        } catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB get all client"+e);
        }
        logger.traceExit(userList);
        return userList;
    }

    @Override
    public void clear() {

    }


    @Override
    public User add(User entity) {
        String sql = "INSERT INTO users VALUES (?, ?, ?)";
        logger.traceEntry("Saving user {} ",entity);

        try (

                Connection connection = jdbcUtils.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getPassword());

            preparedStatement.executeUpdate();
            entity = null;
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB la add user"+ e);
            e.printStackTrace();
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public void delete(Long id) {
        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("delete from users where user_id=?")){
            preSmt.setLong(1,id);
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la delete user "+e);
        }
        logger.traceExit(id);

    }


    @Override
    public void update(User entity) {
        logger.traceEntry();
        Connection conn = jdbcUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("update users set username = ?,password = ? where user_id=?")){
            preSmt.setString(1,entity.getUsername());
            preSmt.setString(2,entity.getPassword());
            preSmt.setLong(3,entity.getId());
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB la update user "+e);
        }
        logger.traceExit(entity);

    }

}
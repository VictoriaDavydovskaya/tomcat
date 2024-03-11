package com.andersen.dao;

import com.andersen.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// for CRUD operations
public class UserDao {
    final static String URL = "jdbc:postgresql://192.168.0.191:5432/postgres";
    final static String USER = "postgres";
    final static String PASSWORD = "admin";

    private final static String INSERT_USER_SQL = "INSERT INTO users "+
            "(name, surname, age) VALUES "+
            "(?, ?, ?);";

    private final static String SELECT_ALL_USERS = "SELECT * FROM users;";

    private final static String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";

    private final static String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?;";

    private final static String UPDATE_USER_SQL = "UPDATE users "+
            "SET name = ?, surname = ?, age = ? WHERE id = ?;";


    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // insert User
    public void insertUser(User user) throws SQLException {
        try(Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall("call insert_user(?, ?, ?)")){
                /*PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.executeUpdate();

             */

            callableStatement.setString(1, user.getName());
            callableStatement.setString(2, user.getSurname());
            callableStatement.setInt(3, user.getAge());
            callableStatement.execute();

        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rawUpdated = false;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());

            rawUpdated = preparedStatement.executeUpdate() > 0;
            //Thread.sleep(10_000);
            connection.commit();

        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        }
        return rawUpdated;
    }

    public User selectUser(int id){
        User user = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                user = new User(id, name, surname, age);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // select all users
    public List<User> selectAllUsers(){
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                users.add(new User(id, name, surname, age));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public Boolean deleteUser(int id) throws SQLException{
        boolean rawDeleted;
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, id);
            rawDeleted = preparedStatement.executeUpdate() > 0;

        }
        return rawDeleted;
    }

}

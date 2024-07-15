package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }

    Connection connection = getConnection();

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE if not exists users (Id INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE if exists users";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "INSERT INTO users (FIRSTNAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE ID = id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT FIRSTNAME, LASTNAME, AGE FROM users ";

        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
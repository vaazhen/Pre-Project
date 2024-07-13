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
        PreparedStatement preparedStatement = null;

        String sqlCommand = "CREATE TABLE if not exists users (Id INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";

        try{
            preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE if exists users";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO users (FIRSTNAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();

        }finally {
            if (preparedStatement != null) {
                    preparedStatement.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE ID = id";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT FIRSTNAME, LASTNAME, AGE FROM users ";

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
             while (resultSet.next()){
                 User user = new User();
                 user.setName(resultSet.getString("FirstName"));
                 user.setLastName(resultSet.getString("LastName"));
                 user.setAge(resultSet.getByte("Age"));

                 userList.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }

    }
}

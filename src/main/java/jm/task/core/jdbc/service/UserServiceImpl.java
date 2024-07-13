package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();
    }
}

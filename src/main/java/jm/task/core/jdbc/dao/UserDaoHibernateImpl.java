package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.Schema;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
        Schema.CreateCollectionOptions = l;
    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable(){

    }

    @Override
    public void saveUser(String name, String lastName, byte age)  {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
       return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}

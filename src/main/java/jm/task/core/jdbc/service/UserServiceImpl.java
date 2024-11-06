package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl dao = new UserDaoJDBCImpl();



    public void createUsersTable() {
        dao.createUsersTable();

    }

    public void dropUsersTable() {
       dao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }


    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}

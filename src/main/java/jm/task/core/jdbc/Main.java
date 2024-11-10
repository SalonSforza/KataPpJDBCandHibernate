package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Никита","Никитин", (byte) 32);
        userService.saveUser("Маринка","Никитина", (byte) 28);
        userService.saveUser("Заур","Трегулов", (byte) 28);
        userService.getAllUsers().stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();






    }
}

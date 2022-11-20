package redSocial.controllers;

import redSocial.DAO.PostDao;
import redSocial.DAO.UserDao;

public class Data {
    protected static UserDao principalUser = new UserDao();
    protected static PostDao p = new PostDao();
}

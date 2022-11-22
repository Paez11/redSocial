package redSocial.controllers;

import redSocial.DAO.PostDao;
import redSocial.DAO.UserDao;

public class Data {
    protected static UserDao aux = new UserDao();
    protected static UserDao principalUser = new UserDao();
    protected static PostDao p = new PostDao();
    protected static PostDao paux = new PostDao();
}

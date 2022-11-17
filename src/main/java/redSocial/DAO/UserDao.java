package redSocial.DAO;

import redSocial.interfaces.Dao;
import redSocial.model.User;
import redSocial.utils.Connection.Connect;
import redSocial.utils.Log;

import java.sql.*;
import java.util.List;

public class UserDao extends User implements Dao {
    private static Connection con = null;

    private final static String INSERT = "INSERT INTO user(name) VALUES (?)";
    private final static String DELETE = "DELETE FROM user WHERE id=?";
    private final static String UPDATE = "UPDATE user SET name=?, password=?, avatar=? WHERE id=? FROM user";
    private final static String SELECTBYID = "SELECT id,name,password,avatar WHERE id=? FROM user";
    private final static String SELECTBYNAME = "SELECT id,name,password,avatar WHERE name=? FROM user";
    private final static String SELECTALL = "SELECT id,name,avatar FROM user";
    private final static String FOLLOW = "INSERT INTO follower VALUES (?,?)";
    private final static String UNFOLLOW = "DELETE FROM follower WHERE id_follower=? AND id_follow=?";
    private final static String GETFOLLOWS = "SELECT id,name,avatar FROM user WHERE id IN (SELECT id_follow FROM follower WHERE id_follower=?)";
    private final static String GETFOLLOWERS = "SELECT id,name,avatar FROM user WHERE id IN (SELECT id_follower FROM follower WHERE id_follow=?)";
    //private final static String GETFOLLOWS = "SELECT u.id,u.name,u.avatar FROM user u JOIN follower f ON u.id=f.id_follow WHERE u.id=?";
    //private final static String GETFOLLOWERS = "SELECT u.id,u.name,u.avatar FROM user u JOIN follower f ON u.id=f.id_follower WHERE u.id=?";

    public UserDao(int id, String name){
        super(id,name);
    }
    public UserDao(int id, String name,String avatar){
        super(id,name,avatar);
    }
    public UserDao(int id, String name,String password,String avatar){
        super(id,name,password,avatar);
    }
    public UserDao(String name,String password){
        super(name,password);
    }
    public UserDao(){
        super();
    }
    public UserDao(User user){
        super(user.getId(),user.getName());
    }
    public UserDao(int id){
        this.getById(id);
    }

    @Override
    public void save() {
        if (id==-1){
            con = Connect.getConnect();
            if (con != null){
                PreparedStatement st = null;
                try {
                    st = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    st.setString(1,this.name);
                    st.executeUpdate();
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()){
                        this.id = rs.getInt(1);
                    }
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    Log.severe("No se ha podido realizar la consulta save en la base de datos");
                }
            }
        }
    }

    @Override
    public void delete() {
        if (id==-1){
            con = Connect.getConnect();
            if (con != null){
                PreparedStatement st = null;
                try {
                    st = con.prepareStatement(DELETE);
                    st.setInt(1,this.id);
                    if (st.executeUpdate()==1){
                        this.id=-1;
                    }
                    st.close();
                } catch (SQLException e) {
                    Log.severe("No se ha podido realizar la consulta delete en la base de datos");
                }
            }
        }
    }

    @Override
    public void update() {
        if (id==-1){
            con = Connect.getConnect();
            if (con != null){
                PreparedStatement st = null;
                try {
                    st = con.prepareStatement(UPDATE);
                    st.setInt(4,this.id);
                    st.setString(1,name);
                    st.setString(2,password);
                    st.setString(3,avatar);
                    st.executeUpdate();
                    st.close();
                } catch (SQLException e) {
                    Log.severe("No se ha podido realizar la consulta update en la base de datos");
                }
            }
        }
    }

    protected User getById(int id){
        UserDao user = new UserDao(id,name,password,avatar);
        if (id==-1){
            con = Connect.getConnect();
            if (con != null){
                PreparedStatement st = null;
                try {
                    st = con.prepareStatement(SELECTBYID);
                    st.setInt(1,id);
                    if (st.execute()){
                        ResultSet rs = st.getResultSet();
                        if (rs.next()){
                            id=rs.getInt(1);
                            this.name = rs.getString("name");
                            this.password = rs.getString("password");
                            this.avatar = rs.getString("avatar");
                        }
                        rs.close();
                    }
                    st.close();
                } catch (SQLException e) {
                    Log.severe("No se ha podido realizar la consulta getById en la base de datos");
                }
            }
        }
        return user;
    }

    private User getByName(String name){
        UserDao user = new UserDao(id,name,password,avatar);
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(SELECTBYNAME);
                st.setString(2,name);
                if (st.execute()){
                    ResultSet rs = st.getResultSet();
                    if (rs.next()){
                        name=rs.getString(2);
                        this.id = rs.getInt("id");
                        this.password = rs.getString("password");
                        this.avatar = rs.getString("avatar");
                    }
                    rs.close();
                }
                st.close();
            } catch (SQLException e) {
                Log.severe("No se ha podido realizar la consulta getByName en la base de datos");
            }
        }
        return user;
    }

    private List<User> getAllUsers(){
        List<User> users = null;
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(SELECTALL);
                if (st.execute()){
                    ResultSet rs = st.getResultSet();
                    while (rs.next()){
                        User u = new User(rs.getInt("id"),rs.getString("name"),
                                rs.getString("avatar"));
                        users.add(u);
                    }
                    rs.close();
                }
                st.close();
            } catch (SQLException e) {
                Log.severe("No se ha podido realizar la consulta getAllUsers en la base de datos");
            }
        }
        return users;
    }

    private void follow(User u){
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(FOLLOW);
                st.setInt(2,this.id);
                st.setInt(1,u.getId());
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                Log.severe("No se ha podido realizar la consulta follow en la base de datos");
            }
        }
    }

    private void unFollow(User u){
        if (id==-1){
            con = Connect.getConnect();
            if (con != null){
                PreparedStatement st = null;
                try {
                    st = con.prepareStatement(UNFOLLOW);
                    st.setInt(1,this.id);
                    st.setInt(2,u.getId());
                    if (st.executeUpdate()==1){
                        this.id=-1;
                    }
                    st.close();
                } catch (SQLException e) {
                    Log.severe("No se ha podido realizar la consulta unfollow en la base de datos");
                }
            }
        }
    }

    private List<User> getFollowers(){
        List<User> followers = null;
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(GETFOLLOWERS);
                st.setInt(1,this.id);
                followers=getAllUsers();
                st.close();
            } catch (SQLException e) {
                Log.severe("No se ha podido realizar la consulta getFollowers en la base de datos");
            }
        }
        return followers;
    }

    private List<User> getFollows(){
        List<User> follows = null;
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(GETFOLLOWERS);
                st.setInt(1,this.id);
                follows=getAllUsers();
                st.close();
            } catch (SQLException e) {
                Log.severe("No se ha podido realizar la consulta getFollows en la base de datos");
            }
        }
        return follows;
    }
}

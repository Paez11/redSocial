package redSocial.DAO;

import redSocial.interfaces.Dao;
import redSocial.model.User;
import redSocial.utils.Connection.Connect;
import redSocial.utils.Log;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class UserDao extends User implements Dao {
    private static Connection con = null;

    private final static String INSERT = "INSERT INTO user(id,name,password,avatar) VALUES (NULL,?,?,?)";
    private final static String DELETE = "DELETE FROM user WHERE id=?";
    private final static String UPDATE = "UPDATE user SET name=?, password=?, avatar=? WHERE id=?";
    private final static String SELECTBYID = "SELECT id,name,password,avatar FROM user WHERE id=?";
    private final static String SELECTBYNAME = "SELECT id,name,password,avatar FROM user WHERE name=?";
    private final static String SELECTALL = "SELECT id,name,avatar FROM user";
    private final static String FOLLOW = "INSERT INTO follower VALUES (?,?)";
    private final static String UNFOLLOW = "DELETE FROM follower WHERE id_follower=? AND id_follow=?";
    private final static String GETFOLLOWS = "SELECT id,name,avatar FROM user WHERE id IN (SELECT id_follow FROM follower WHERE id_follower=?)";
    private final static String GETFOLLOWERS = "SELECT id,name,avatar FROM user WHERE id IN (SELECT id_follower FROM follower WHERE id_follow=?)";

    public UserDao(int id, String name){
        super(id,name);
    }
    public UserDao(int id, String name,byte[] avatar){
        super(id,name,avatar);
    }
    public UserDao(int id, String name,String password,byte[] avatar){
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
        con = Connect.getConnect();
        Blob imageBlob = null;
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(INSERT);
                st.setString(1,this.name);
                st.setString(2,this.password);
                try {
                    imageBlob = new SerialBlob(this.avatar);
                    st.setBlob(3,imageBlob);
                } catch (SQLException e) {
                    Log.severe("Error al actualizar foto de usuario: "+e.getMessage());
                }
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                Log.severe("Error al insertar usuario: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete() {
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(DELETE);
                st.setInt(1,this.id);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                Log.severe("Error al eliminar usuario: " + e.getMessage());
            }
        }
    }

    @Override
    public void update() {
        con = Connect.getConnect();

        //Blob imageBlob = null;
        File imageBlob = null;

        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(UPDATE);
                st.setInt(4,this.id);
                st.setString(1,name);
                st.setString(2,password);
                try {
                    imageBlob = new File(String.valueOf(this.avatar));
                    FileInputStream in = new FileInputStream(imageBlob);
                    //imageBlob = new SerialBlob(this.avatar);
                    //imageBlob.setBytes(1,this.avatar);
                    //this.avatar = imageBlob.getBytes(1,(int)imageBlob.length());
                    st.setBinaryStream(3,in,(int)imageBlob.length());
                    //st.setInt(3,(int)imageBlob.length());
                    //st.setBlob(3, imageBlob);
                //} catch (SQLException e) {
                    //Log.severe("Error al actualizar foto de usuario: "+e.getMessage());
                } catch (FileNotFoundException e) {
                    Log.severe("imagen: "+e.getMessage());
                }
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                Log.severe("Error al actualizar usuario: " + e.getMessage());
            }
        }
    }

    public User getById(int id){
        UserDao user = new UserDao(id,name,password,avatar);
        if (id!=-1){
            con = Connect.getConnect();
            if (con != null){
                PreparedStatement st = null;
                try {
                    st = con.prepareStatement(SELECTBYID);
                    st.setInt(1,id);
                    if (st.execute()){
                        ResultSet rs = st.getResultSet();
                        if (rs.next()){
                            user.id=rs.getInt(1);
                            user.name = rs.getString("name");
                            user.password = rs.getString("password");
                            //user.avatar = rs.getString("avatar");
                            Blob imageBlob = rs.getBlob("avatar");
                            byte[] bdata = imageBlob.getBytes(1, (int) imageBlob.length());
                            user.avatar = bdata;

                        }
                        rs.close();
                    }
                    st.close();
                } catch (SQLException e) {
                    Log.severe("Error al obtener usuario: " + e.getMessage());
                }
            }
        }
        return user;
    }

    public User getByName(String name){
        UserDao user = new UserDao(id,name,password,avatar);
        con = Connect.getConnect();
        if (con != null){
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(SELECTBYNAME);
                st.setString(1,name);
                if (st.execute()){
                    ResultSet rs = st.getResultSet();
                    if (rs.next()){
                        this.name=rs.getString("name");
                        this.id = rs.getInt("id");
                        this.password = rs.getString("password");
                        //this.avatar = rs.getString("avatar");
                        Blob imageBlob = rs.getBlob("avatar");
                        byte[] bdata = imageBlob.getBytes(1, (int) imageBlob.length());
                        this.avatar = bdata;

                    }
                    rs.close();
                }
                st.close();
            } catch (SQLException e) {
                Log.severe("Error al obtener usuario: " + e.getMessage());
            }
        }
        user.setName(this.name);
        user.setId(this.id);
        user.setPassword(this.password);
        user.setAvatar(this.avatar);
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
                        Blob imageBlob = rs.getBlob("avatar");
                        byte[] bdata = imageBlob.getBytes(1, (int) imageBlob.length());
                        avatar = bdata;
                        User u = new User(rs.getInt("id"),rs.getString("name"),
                                rs.getString("avatar").getBytes());
                                    //String.valueOf(imageBlob.getBytes(0, (int) imageBlob.length())));
                        users.add(u);
                    }
                    rs.close();
                }
                st.close();
            } catch (SQLException e) {
                Log.severe("Error al obtener usuarios: " + e.getMessage());
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
                Log.severe("Error al seguir usuario: " + e.getMessage());
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
                    Log.severe("Error al dejar de seguir usuario: " + e.getMessage());
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
                Log.severe("Error al obtener seguidores: " + e.getMessage());
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
                Log.severe("Error al obtener seguidos: " + e.getMessage());
            }
        }
        return follows;
    }
}

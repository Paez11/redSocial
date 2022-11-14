package es.iesfranciscodelosrios.red.DAO;

import es.iesfranciscodelosrios.red.interfaces.Dao;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Connection.Connect;
import es.iesfranciscodelosrios.red.utils.Log;

import java.sql.*;
import java.util.List;

public class UserDao extends User implements Dao {
    private static Connection con = null;

    private final static String INSERT = "INSERT INTO User(name) VALUES (?)";
    private final static String DELETE = "DELETE FROM User WHERE id=?";
    private final static String UPDATE = "UPDATE User SET name=?, password=?, avatar=? WHERE id=?";
    private final static String SELECTBYID = "SELECT id,name,password,avatar WHERE id=?";
    private final static String SELECTALL = "SELECT id,name,avatar  FROM User";


    public UserDao(int id, String name){
        super(id,name);
    }
    public UserDao(int id, String name,String avatar){
        super(id,name,avatar);
    }
    public UserDao(int id, String name,String password,String avatar){
        super(id,name,password,avatar);
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

    private User getById(int id){
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
    private List<User> getAlUsers(){
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
}

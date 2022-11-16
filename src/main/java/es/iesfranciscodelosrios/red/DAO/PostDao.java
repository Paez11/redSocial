package es.iesfranciscodelosrios.red.DAO;

import es.iesfranciscodelosrios.red.interfaces.Dao;
import es.iesfranciscodelosrios.red.model.Comment;
import es.iesfranciscodelosrios.red.model.Post;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Connection.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class PostDao extends Post implements Dao {

    private final static String INSERT = "INSERT INTO post (id,id_user,fecha_creacion,fecha_modificacion,texto) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE post SET fecha_modificacion=?,texto=? WHERE id=?";
    private final static String DELETE = "DELETE FROM post WHERE id=?";
    private final static String SELECTBYID = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post WHERE id=?";
    private final static String SELECTALL = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post";
    private final static String SELECTBYUSER = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post WHERE id_user=?";

    public PostDao(User u, int id){
        super(u,id);
    }
    public PostDao(User u,int id, Date fCreacion, Date fModificacion,String texto){
        super(u,id,fCreacion,fModificacion,texto);
    }
    public PostDao(User u, int id, Date fCreacion, Date fModificacion, String texto, List<Comment> comentarios, Set<User> likes){
        super(u,id,fCreacion,fModificacion,texto,comentarios,likes);
    }
    public PostDao(){
        super();
    }
    public PostDao(int id){
        this.getById(id);
    }


    @Override
    public void save() {
        if(this.getId()==-1) {
            //INSERT
            Connection cn = Connect.getConnect();
            if(cn != null) {
                PreparedStatement ps;
                try {
                    ps = cn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, this.getId());
                    ps.setInt(2, this.getUserName().getId());
                    ps.setDate(3, this.getDateCreate());
                    ps.setDate(4, this.getDateUpdate());
                    ps.setString(5, this.getText());
                    ps.executeUpdate();  //devuelve 1 si ha salido bien
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()) {
                        this.setId(rs.getInt(1));
                    }
                    ps.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete() {
        if(this.getId()!=-1) {
            //UPDATE
            Connection cn = Connect.getConnect();
            if(cn != null) {
                PreparedStatement ps;
                try {
                    ps = cn.prepareStatement(DELETE);
                    ps.setInt(1, this.getId());
                    if(ps.executeUpdate()==1) {
                        this.setId(-1);
                    }
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void update() {
        if(this.getId()!=-1) {
            //UPDATE
            Connection cn = Connect.getConnect();
            if(cn != null) {
                PreparedStatement ps;
                try {
                    ps = cn.prepareStatement(UPDATE);
                    ps.setDate(1, this.getDateUpdate());
                    ps.setString(2, this.getText());
                    ps.setInt(3, this.getId());
                    ps.executeUpdate();  //devuelve 1 si funciona
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static List<Post> getAll(){
        List<Post> result = new ArrayList();
        Connection cn = Connect.getConnect();
        UserDao user = new UserDao();
        if(cn != null) {
            PreparedStatement ps;
            try {
                ps = cn.prepareStatement(SELECTALL);
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while(rs.next()) {
                        Post p = new Post(user.getById(rs.getInt("id_user")),
                                rs.getInt("id"),
                                rs.getDate("fecha_creacion"),
                                rs.getDate("fecha_modificacion"),
                                rs.getString("texto"));
                        //p.setComments(CommentDao.getCommentsByPostId(rs.getInt(1)));
                        //p.setLikes((rs.getInt(1)));
                        result.add(p);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void getById(int id) {
        Connection cn = Connect.getConnect();
        if(cn != null) {
            PreparedStatement ps;
            try {
                ps = cn.prepareStatement(SELECTBYID);
                ps.setInt(1, id);
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    if(rs.next()) {
                        this.setId(rs.getInt("id"));
                        this.setUserNameID(rs.getInt("id_user"));
                        this.setDateCreate(rs.getDate("fecha_creacion"));
                        this.setDateUpdate(rs.getDate("fecha_modificacion"));
                        this.setText(rs.getString("texto"));
                        //this.setLikes(rs.getInt(1));
                        //this.setComments(CommentDao.getCommentsByPostId(rs.getInt(1)));
                    }
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


}

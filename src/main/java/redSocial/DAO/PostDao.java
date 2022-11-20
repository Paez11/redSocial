package redSocial.DAO;

import redSocial.interfaces.Dao;
import redSocial.model.Comment;
import redSocial.model.Post;
import redSocial.model.User;
import redSocial.utils.Connection.Connect;
import redSocial.utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PostDao extends Post implements Dao {

    private final static String INSERT = "INSERT INTO post (id,id_user,fecha_creacion,texto) VALUES (NULL,?,?,?)";
    private final static String UPDATE = "UPDATE post SET fecha_modificacion=?,texto=? WHERE id=?";
    private final static String DELETE = "DELETE FROM post WHERE id=?";
    private final static String SELECTBYID = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post WHERE id=?";
    private final static String SELECTALL = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post";
    private final static String SELECTBYUSER = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post WHERE id_user=?";
    private final static String SELECTCOMMENTS = "SELECT id,id_user,fecha_creacion,fecha_modificacion,texto FROM post WHERE id_user=?";

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
        //INSERT
        Connection cn = Connect.getConnect();
        if(cn != null) {
            PreparedStatement ps;
            try {
                ps = cn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, this.getUserName().getId());
                ps.setDate(2, this.getDateCreate());
                ps.setString(3, this.getText());
                ps.executeUpdate();  //devuelve 1 si ha salido bien
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    this.setId(rs.getInt(1));
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                Log.severe("Error al insertar el post: " + e.getMessage());
            }
        }
}

    @Override
    public void delete() {
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
                Log.severe("Error al borrar el post: "+e.getMessage());
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
                    Log.severe("Error al actualizar el post: "+e.getMessage());
                }

            }
        }
    }

    public static List<Post> getAll(){
        List<Post> result = new ArrayList();
        Connection cn = Connect.getConnect();
        CommentDao comments = null;
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
                                p.setComments((List<Comment>) comments.getById(rs.getInt(1)));
                        result.add(p);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                Log.severe("Error al obtener todos los posts"+e.getMessage());
            }
        }
        return result;
    }

    protected Post getById(int id) {
        Connection cn = Connect.getConnect();
        UserDao user = new UserDao();
        PostDao post = new PostDao(userName, id, dateCreate, dateUpdate, text, comments, likes);
        CommentDao comments = null;
        if(cn != null) {
            PreparedStatement ps;
            try {
                ps = cn.prepareStatement(SELECTBYID);
                ps.setInt(1, id);
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    if(rs.next()) {
                        id=rs.getInt(1);
                        userName = user.getById(rs.getInt("id_user"));
                        this.dateCreate = rs.getDate("fecha_creacion");
                        this.dateUpdate = rs.getDate("fecha_modificacion");
                        this.text = rs.getString("texto");
                        this.comments = (List<Comment>) comments.getById(rs.getInt(1));
                    }
                }
                ps.close();
            } catch (SQLException e) {
                Log.severe("Error al obtener el post con id: "+id+"\n"+e.getMessage());
            }

        }
        return post;
    }

    public List<Comment> getComments(){
        List<Comment> comments = new ArrayList();
        Connection cn = Connect.getConnect();
        if (cn != null) {
            PreparedStatement ps = null;
            try {
                ps = cn.prepareStatement(SELECTCOMMENTS);
                ps.setInt(1, this.id);
                if (ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while (rs.next()) {

                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                Log.severe("Error al obtener los comentarios del post con id: "+id+"\n"+e.getMessage());
            }
        }
        return comments;
    }

    public List<User> getWhoLikes(){
        List<User> likes = new ArrayList<>();

        return likes;
    }


}

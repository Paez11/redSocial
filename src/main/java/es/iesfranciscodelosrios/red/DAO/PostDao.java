package es.iesfranciscodelosrios.red.DAO;

import es.iesfranciscodelosrios.red.model.Post;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Connection.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PostDao {
    private Connection cn = Connect.getConnect("conexion.xml");

    public void savePost(Post post) {
        String sql = "INSERT INTO post (id, id_user, fecha_creacion, fecha_modificacion, texto) VALUES (NULL, '" + post.getUserName().getId() + "', '" + post.getDateCreate() + "', '" + post.getDateUpdate() + "', '" + post.getText() +"');";
        try {
            cn.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Post> getAllPost() {
        ArrayList<Post> posts = new ArrayList<>();

        String sql = "SELECT * FROM post";


        try {
            PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rq= ps.executeQuery();
            while(rq.next()){
                Post aux= new Post();
                aux.setId(rq.getInt(1));
                aux.setUserNameID(rq.getInt(2));
                aux.setUserName(UserDao.getUserById(rq.getInt(2)));
                aux.setDateCreate(rq.getDate(3));
                aux.setDateUpdate(rq.getDate(4));
                aux.setText(rq.getString(5));
                aux.setComments(CommentDao.getCommentsByPostId(rq.getInt(1)));
                aux.setLikes(LikeDao.getLikesByPostId(rq.getInt(1)));
                posts.add(aux);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public Post getPostById(int id) {
        Post post = new Post();

        String sql = "SELECT * FROM post WHERE id = " + id;
        try {
            PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rq= ps.executeQuery();
            while(rq.next()){
                Post aux= new Post();
                aux.setId(rq.getInt(1));
                aux.setUserNameID(rq.getInt(2));
                aux.setUserName(UserDao.getUserById(rq.getInt(2)));
                aux.setDateCreate(rq.getDate(3));
                aux.setDateUpdate(rq.getDate(4));
                aux.setText(rq.getString(5));
                aux.setComments(CommentDao.getCommentsByPostId(rq.getInt(1)));
                aux.setLikes(LikeDao.getLikesByPostId(rq.getInt(1)));
                posts.add(aux);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    public boolean updatePostById(Integer id){
        boolean IsUpdated = false;
        Post post = getPostById(id);
        String sql = "UPDATE post SET fecha_modificacion = "+ post.getDateUpdate() + "','"+ "texto = "+post.getText() +" WHERE id = " + post.getId();
        return IsUpdated;
    }

    public void deletePost(Integer id) {
        String sql = "DELETE FROM post WHERE id = " + id + ";";
        try {
            cn.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

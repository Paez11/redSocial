package redSocial.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import redSocial.model.Comment;
import redSocial.model.User;
import redSocial.model.Post;
import redSocial.utils.Connection.Connect;



public class CommentDao extends Comment {
	 private final static String INSERT = "INSERT INTO comments (id_user,texto,id_post,time) VALUES (?,?,?.?)";
	 private final static String SELECTBYUSERPOST = "SELECT id,id_user,texto,id_post,time FROM comments where id_user=? and id_post=?";
	 private final static String SELECTBYPOST = "SELECT id,id_user,texto,id_post,time FROM comments where id_post=?";
	 
	
	public CommentDao(int id,User userComment, String textComment, Post post) {
		super(id,userComment,textComment,post);
	}
	public CommentDao(User userComment, String textComment, Post post) {
		super(userComment,textComment,post);
	}
	
	
	public void saveComment() {
        if(this.getId()==-1) {
            //INSERT
            Connection cn = Connect.getConnect();
            if(cn != null) {
                PreparedStatement ps;
                try {
                    ps = cn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, this.getUserComment().getId());
                    ps.setString(2, this.getTextComment());
                    ps.setInt(3, this.getPost().getId());
                    ps.setDate(4, Date.valueOf(time.toString()));
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
	
	public static List<Comment> getAllByUser(User userByS,Post postByS ){
        List<Comment> result = new ArrayList<Comment>();
        Connection cn = Connect.getConnect();
        if(cn != null) {
            PreparedStatement ps;
            try {
                ps = cn.prepareStatement(SELECTBYUSERPOST);
                ps.setInt(1,userByS.getId());
                ps.setInt(2,postByS.getId());
                
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    UserDao udao= new UserDao();
                    while(rs.next()) {
                        int id=rs.getInt("id");
                        User user =udao.getById(rs.getInt("id_user"));
                        String texto = rs.getString("texto");
                        Post post= new Post(rs.getInt("id_post"));
                        result.add(new Comment(id,user, texto, post));
                           
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
	
	public static List<Comment> getAllByPost(Post postByS ){
        List<Comment> result = new ArrayList<Comment>();
        Connection cn = Connect.getConnect();
        if(cn != null) {
            PreparedStatement ps;
            try {
                ps = cn.prepareStatement(SELECTBYPOST);
                ps.setInt(1,postByS.getId());
                
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    UserDao udao= new UserDao();
                    while(rs.next()) {
                        int id=rs.getInt("id");
                        User user =udao.getById(rs.getInt("id_user"));
                        String texto = rs.getString("texto");
                        Post post= new Post(rs.getInt("id_post"));
                        result.add(new Comment(id,user, texto, post));
                           
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
}
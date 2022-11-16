package es.iesfranciscodelosrios.red.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.iesfranciscodelosrios.red.model.Post;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Connection.Connect;

public class PostDao extends Post {
	private final static String INSERT = "INSERT INTO post (id_user, texto, fecha_creaccion) VALUES (?,?,?)";
	private final static String DELETE = "DELETE FROM post (id) VALUES (?)";

	public PostDao(User userName,String text, LocalDateTime dateCreate ) {
		super(userName, text, dateCreate);
	}

	public void insert() {
			//INSERT
			Connection con = Connect.getConnect(null);
			if (con != null) {
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, this.userName.getId());
					ps.setString(2, this.text);
					ps.setDate(3, Date.valueOf(dateCreate.toString()));
					ps.executeUpdate(); // devuelve 1 si todo ok
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
	}
			
			
	
	
	public void deleteforIdPost() {
			Connection conn = Connect.getConnect("jdbc:mariadb://localhost:3306/redsocial");
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(DELETE);
					ps.setInt(1, this.id);
					if(ps.executeUpdate()==1) {
						this.id=-1;
					}	
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
}
package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;
    public User getUser(User u){
        User user = new User();

        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "SELECT * FROM user WHERE id_user = ? ";
            this.stmt = connection.prepareStatement(this.sql);
            stmt.setInt(1, u.getId());
            this.rs = stmt.executeQuery();
            while(this.rs.next()){
                user.setId(this.rs.getInt("id_user"));
                user.setName(this.rs.getString("name"));
                user.setCpf(this.rs.getString("cpf"));
                user.setEmail(this.rs.getString("email"));
                user.setPassword(this.rs.getString("password"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public boolean insertUser(User u){
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "INSERT INTO user (name, cpf, email, password) VALUES (?,?,?,?)";
            this.stmt = connection.prepareStatement(this.sql);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPassword());
            stmt.executeQuery();
            return true;
            }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

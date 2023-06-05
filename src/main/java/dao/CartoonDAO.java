package dao;

import model.Cartoon;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartoonDAO{
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ArrayList<Cartoon> getCartoons(User u){
        ArrayList<Cartoon> cartoons = new ArrayList<Cartoon>();
        Cartoon cartoon = new Cartoon();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "Select * FROM cartoon WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1,u.getId());
            this.rs = this.stmt.executeQuery();
            while(rs.next()){
                cartoon.setId(rs.getInt("id_filme"));
                cartoon.setTitle(rs.getString("title"));
                cartoon.setNote(rs.getInt("note"));
                cartoon.setEpisodes(rs.getInt("pages"));
                cartoon.setPlataform(rs.getString("plataform"));
                cartoons.add(cartoon);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartoons;
    }

    public boolean setCartoon(Cartoon c, User u){
        try(Connection connection =  new ConnectDB().getConexao()){
            this.sql = "INSERT INTO cartoon (title, note, episodes, platform, id_user) VALUES (?, ?, ?, ?, ?)";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, c.getTitle());
            this.stmt.setInt(2, c.getNote());
            this.stmt.setInt(3, c.getEpisodes());
            this.stmt.setString(4, c.getPlataform());
            this.stmt.setInt(5, u.getId());
            this.stmt.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean upCartoon(Cartoon c, User u){
        try(Connection connection = new ConnectDB().getConexao()) {
            this.sql = "UPDATE cartoon SET title = ?, note = ?, episodes = ?, plataform = ? WHERE id_cartoon = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, c.getTitle());
            this.stmt.setInt(2, c.getNote());
            this.stmt.setInt(3, c.getEpisodes());
            this.stmt.setString(4, c.getPlataform());
            this.stmt.setInt(5, c.getId());
            this.stmt.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean dellCartoon(Cartoon c){
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "DELETE FROM cartoon WHERE id_cartoon = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, c.getId());
            return this.stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}

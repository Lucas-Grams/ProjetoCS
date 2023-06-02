package dao;

import model.Movie;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ArrayList<Movie> getMovies(User u){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie = new Movie();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "Select * FROM movie WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1,u.getId());
            this.rs = this.stmt.executeQuery();
            while(rs.next()){
                movie.
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.rs;
    }

}

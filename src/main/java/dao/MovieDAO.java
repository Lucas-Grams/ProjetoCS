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
                movie.setId(rs.getInt("id_filme"));
                movie.setTitle(rs.getString("title"));
                movie.setNote(rs.getInt("note"));
                movie.setDuration(rs.getInt("duration"));
                movie.setPlataform(rs.getString("plataform"));
                movies.add(movie);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return movies;
    }

    public boolean setMovie(Movie m, User u){
        try(Connection connection =  new ConnectDB().getConexao()){
            this.sql = "INSERT INTO movie (title, note, duration, platform, id_user) VALUES (?, ?, ?, ?, ?)";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, m.getTitle());
            this.stmt.setInt(2, m.getNote());
            this.stmt.setInt(3, m.getDuration());
            this.stmt.setString(4, m.getPlataform());
            this.stmt.setInt(5, u.getId());
            this.stmt.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean upMovies(Movie m, User u){
        try(Connection connection = new ConnectDB().getConexao()) {
            this.sql = "UPDATE movie SET title = ?, note = ?, duration = ?, plataform = ? WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, m.getTitle());
            this.stmt.setInt(2, m.getNote());
            this.stmt.setInt(3, m.getDuration());
            this.stmt.setString(4, m.getPlataform());
            this.stmt.setInt(5, u.getId());
            this.stmt.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean dellMovie(Movie m){
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "DELETE FROM movie WHERE id_movie = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, m.getId());
            return this.stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}

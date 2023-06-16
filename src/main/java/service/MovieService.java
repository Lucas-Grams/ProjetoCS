package service;

import dao.MovieDAO;
import model.Movie;

import java.util.ArrayList;

public class MovieService {

    private MovieDAO md = new MovieDAO();

    public Movie listMovie(int id){
        Movie movie = new Movie();
        movie = md.getMovie(id);
        return movie;
    }

    public ArrayList<Movie> listMovies(int idUser){
        ArrayList<Movie> movies = new ArrayList<>();
        movies = md.getMovies(idUser);
        return movies;
    }

    public boolean insertMovie(Movie m, int id){
        if(md.setMovie(m, id)){
            System.out.println("deu tudo certo negao");
            return true;
        }else{
            return false;
        }
    }
}

package controller;

import model.Movie;
import service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("MovieController")
public class MovieController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        String acao = req.getParameter("acao");
        int idUser = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int note = Integer.parseInt(req.getParameter("note"));
        String plataform = req.getParameter("plataform");
        int duration = Integer.parseInt(req.getParameter("duration"));
        int idMovie = Integer.parseInt(req.getParameter("idMovie"));
        MovieService ms = new MovieService();
        Movie m = new Movie();
        ArrayList<Movie> movies = new ArrayList<>();

        switch(acao){
            case "listMovie" ->{
                movies = ms.listMovies(idUser);
                req.setAttribute("movies", movies);
                req.setAttribute("idUser", idUser);
                req.getRequestDispatcher("listMovie.jsp");
            }

            case "insert"->{
                req.setAttribute("idUser", idUser);
                req.getRequestDispatcher("insertMovie.jsp");
            }

            case "insertMovie" ->{
                m.setTitle(title);
                m.setNote(note);
                m.setPlataform(plataform);
                m.setDuration(duration);
                if(ms.insertMovie(m, idUser)){
                    System.out.println("filme inserido com sucesso");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listMovie.jsp");
                }else{
                    System.out.println("filme nao inserido, tente novamente mais tarde");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listMovie.jsp");
                }
            }
            case "edit" ->{
                Movie movie = new Movie();
                movie = ms.listMovie(idMovie);
                req.setAttribute("movie", movie);
                req.getRequestDispatcher("insertMovie.jsp");
            }
            }

        }
    }


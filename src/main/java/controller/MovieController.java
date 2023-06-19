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
        MovieService ms = new MovieService();
        Movie m = new Movie();
        ArrayList<Movie> movies = new ArrayList<>();

        switch(acao){
            case "listMovie" ->{
                int idUser = Integer.parseInt(req.getParameter("id"));
                movies = ms.listMovies(idUser);
                req.getSession().setAttribute("movies", movies);
                req.getSession().setAttribute("idUser", idUser);
                resp.sendRedirect("listMovie.jsp");
            }

            case "insert"->{
                int idUser = Integer.parseInt(req.getParameter("id"));
                req.getSession().setAttribute("idUser", idUser);
                resp.sendRedirect("insertMovie.jsp");
            }

            case "insertMovie" ->{
                int idUser = Integer.parseInt(req.getParameter("id"));
                String title = req.getParameter("title");
                int note = Integer.parseInt(req.getParameter("note"));
                String plataform = req.getParameter("plataform");
                int duration = Integer.parseInt(req.getParameter("duration"));
                m.setTitle(title);
                m.setNote(note);
                m.setPlataform(plataform);
                m.setDuration(duration);
                if(ms.insertMovie(m, idUser)){
                    System.out.println("filme inserido com sucesso");
                    movies = ms.listMovies(idUser);
                    req.getSession().setAttribute("movies", movies);
                    req.getSession().setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listMovie.jsp");
                }else{
                    System.out.println("filme nao inserido, tente novamente mais tarde");
                    movies = ms.listMovies(idUser);
                    req.getSession().setAttribute("movies", movies);
                    req.getSession().setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listMovie.jsp");
                }
            }
            case "edit" ->{
                int idMovie = Integer.parseInt(req.getParameter("idMovie"));
                Movie movie = new Movie();
                movie = ms.listMovie(idMovie);
                req.getSession().setAttribute("movie", movie);
                req.getRequestDispatcher("editMovie.jsp");
            }
            case "editMovie" ->{
                int idUser = Integer.parseInt(req.getParameter("id"));
                int idMovie = Integer.parseInt(req.getParameter("idMovie"));
                String title = req.getParameter("title");
                int note = Integer.parseInt(req.getParameter("note"));
                String plataform = req.getParameter("plataform");
                int duration = Integer.parseInt(req.getParameter("duration"));
                m.setId(idMovie);
                m.setTitle(title);
                m.setNote(note);
                m.setPlataform(plataform);
                m.setDuration(duration);
                if(ms.editMovie(m)){
                    System.out.println("alterado com sucesso!");
                    movies = ms.listMovies(idUser);
                    req.getSession().setAttribute("movies", movies);
                    req.getSession().setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listMovie.jsp");
                }
            }
            case "dellMovie" ->{
                int idMovie = Integer.parseInt(req.getParameter("idMovie"));
                int idUser = Integer.parseInt(req.getParameter("id"));
                m.setId(idMovie);
                if(ms.dellMovie(m.getId())){
                    System.out.println("escluido com sucesso");
                    movies = ms.listMovies(idUser);
                    req.getSession().setAttribute("movies", movies);
                    req.getRequestDispatcher("listMovies.jsp");
                }
            }
            }

        }
    }


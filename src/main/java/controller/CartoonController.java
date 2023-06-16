package controller;

import model.Cartoon;
import service.CartoonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("CartoonController")
public class CartoonController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        String acao = req.getParameter("acao");
        int idUser = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int note = Integer.parseInt(req.getParameter("note"));
        String plataform = req.getParameter("plataform");
        int episodes = Integer.parseInt(req.getParameter("episodes"));
        int idCartoon = Integer.parseInt(req.getParameter("idCartoon"));
        CartoonService cs = new CartoonService();
        Cartoon c = new Cartoon();
        ArrayList<Cartoon> cartoons = new ArrayList<>();

        switch (acao) {
            case "listCartoon" -> {
                cartoons = cs.listCartoons(idUser);
                req.setAttribute("cartoons", cartoons);
                req.setAttribute("idUser", idUser);
                req.getRequestDispatcher("listCartoon.jsp");
            }

            case "insert" -> {
                req.setAttribute("idUser", idUser);
                req.getRequestDispatcher("insertCartoon.jsp");
            }

            case "insertCartoon" -> {
                c.setTitle(title);
                c.setNote(note);
                c.setPlataform(plataform);
                c.setEpisodes(episodes);
                if (cs.insertCartoon(c, idUser)) {
                    System.out.println("desenho inserido com sucesso");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listCartoon.jsp");
                } else {
                    System.out.println("desenho nao inserido, tente novamente mais tarde");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listCartoon.jsp");
                }
            }
            case "edit" -> {
                Cartoon cartoon = new Cartoon();
                cartoon = cs.listCartoon(idCartoon);
                req.setAttribute("cartoon", cartoon);
                req.getRequestDispatcher("editCartoon.jsp");
            }
            case "editCartoon" -> {
                c.setId(idCartoon);
                c.setTitle(title);
                c.setNote(note);
                c.setPlataform(plataform);
                c.setEpisodes(episodes);
                if (cs.editCartoon(c)) {
                    System.out.println("alterado com sucesso!");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listCartoon.jsp");
                }
            }
            case "dellCartoon" -> {
                c.setId(idCartoon);
                if (cs.dellCartoon(c.getId())) {
                    System.out.println("excluido com sucesso");
                    req.getRequestDispatcher("listCartoon.jsp");
                }
            }
        }
    }
}
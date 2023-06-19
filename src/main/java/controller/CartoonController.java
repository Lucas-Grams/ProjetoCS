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
        CartoonService cs = new CartoonService();
        Cartoon c = new Cartoon();
        ArrayList<Cartoon> cartoons = new ArrayList<>();

        switch (acao) {
            case "listCartoon" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                cartoons = cs.listCartoons(idUser);
                req.getSession().setAttribute("cartoons", cartoons);
                req.getSession().setAttribute("idUser", idUser);
                resp.sendRedirect("listCartoon.jsp");
            }

            case "insert" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                req.getSession().setAttribute("idUser", idUser);
                resp.sendRedirect("insertCartoon.jsp");
            }

            case "insertCartoon" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                String title = req.getParameter("title");
                int note = Integer.parseInt(req.getParameter("note"));
                String plataform = req.getParameter("plataform");
                int episodes = Integer.parseInt(req.getParameter("episodes"));
                c.setTitle(title);
                c.setNote(note);
                c.setPlataform(plataform);
                c.setEpisodes(episodes);
                if (cs.insertCartoon(c, idUser)) {
                    System.out.println("desenho inserido com sucesso");
                    cartoons = cs.listCartoons(idUser);
                    req.getSession().setAttribute("cartoons", cartoons);
                    req.getSession().setAttribute("idUser", idUser);
                    resp.sendRedirect("listCartoon.jsp");
                } else {
                    System.out.println("desenho nao inserido, tente novamente mais tarde");
                    cartoons = cs.listCartoons(idUser);
                    req.getSession().setAttribute("cartoons", cartoons);
                    req.getSession().setAttribute("idUser", idUser);
                    resp.sendRedirect("listCartoon.jsp");
                }
            }
            case "edit" -> {
                int idCartoon = Integer.parseInt(req.getParameter("idCartoon"));
                Cartoon cartoon = new Cartoon();
                cartoon = cs.listCartoon(idCartoon);
                req.getSession().setAttribute("cartoon", cartoon);
                resp.sendRedirect("editCartoon.jsp");
            }
            case "editCartoon" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                int idCartoon = Integer.parseInt(req.getParameter("idCartoon"));
                String title = req.getParameter("title");
                int note = Integer.parseInt(req.getParameter("note"));
                String plataform = req.getParameter("plataform");
                int episodes = Integer.parseInt(req.getParameter("episodes"));
                c.setId(idCartoon);
                c.setTitle(title);
                c.setNote(note);
                c.setPlataform(plataform);
                c.setEpisodes(episodes);
                if (cs.editCartoon(c)) {
                    System.out.println("alterado com sucesso!");
                    cartoons = cs.listCartoons(idUser);
                    req.getSession().setAttribute("cartoons", cartoons);
                    req.getSession().setAttribute("idUser", idUser);
                    resp.sendRedirect("listCartoon.jsp");
                }
            }
            case "dellCartoon" -> {
                int idCartoon = Integer.parseInt(req.getParameter("idCartoon"));
                int idUser = Integer.parseInt(req.getParameter("id"));
                c.setId(idCartoon);
                if (cs.dellCartoon(c.getId())) {
                    System.out.println("excluido com sucesso");
                    cartoons = cs.listCartoons(idUser);
                    req.getSession().setAttribute("cartoons", cartoons);
                    resp.sendRedirect("listCartoon.jsp");
                }
            }
        }
    }
}
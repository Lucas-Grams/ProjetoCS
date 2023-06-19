package controller;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("UserController")
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        String acao = req.getParameter("acao");
        User u = new User();
        UserService us = new UserService();
        RequestDispatcher rd;
        switch (acao) {
            case "login" -> {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                u.setEmail(email);
                u.setPassword(password);
                User u2 = us.login(u);
                if (u2 == null) {
                    System.out.println("usuario nao encontrado");
                    String erro = "Usário não encontrado";
                    req.getSession().setAttribute("erro", erro);
                    resp.sendRedirect("index.jsp");
                }else{
                    System.out.println("logou");
                    req.getSession().setAttribute("user", u2);
                    resp.sendRedirect("mainMenu.jsp");
                }

            }
            case "insert" -> {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String cpf = req.getParameter("cpf");
                u.setName(name);
                u.setCpf(cpf);
                u.setEmail(email);
                u.setPassword(password);
                if (us.insertUser(u)) {
                    System.out.println("cadastrou");
                    resp.sendRedirect("index.jsp");
                }
            }
            case "editUser" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                u = us.getUserId(id);
                req.getSession().setAttribute("user", u);
                resp.sendRedirect("insertUser.jsp");
            }
            case "edit" -> {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String cpf = req.getParameter("cpf");
                int id = Integer.parseInt(req.getParameter("id"));
                u.setId(id);
                u.setName(name);
                u.setCpf(cpf);
                u.setEmail(email);
                u.setPassword(password);
                if (us.editUser(u)) {
                    System.out.println("editou");
                    req.getSession().setAttribute("user", u);
                    resp.sendRedirect("mainMenu.jsp");
                }
            }
            case "dell" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                u.setId(id);
                if (us.dellUser(u.getId())) {
                    System.out.println("excluiu o user");
                    resp.sendRedirect("index.jsp");
                }
            }
        }
    }
}

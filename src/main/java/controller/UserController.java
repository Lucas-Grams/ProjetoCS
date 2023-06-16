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
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String cpf = req.getParameter("cpf");
        int id = Integer.parseInt(req.getParameter("id"));
        User u = new User();
        UserService us = new UserService();
        RequestDispatcher rd;
        switch (acao) {
            case "login" -> {
                u.setEmail(email);
                u.setPassword(password);
                User u2 = us.login(u);
                if (u2 != null) {
                    System.out.println("logou");
                    req.setAttribute("user", u2);
                    rd = req.getRequestDispatcher("mainMenu.jsp");
                }else{
                    String erro = "Usário não encontrado";
                    req.setAttribute("erro", erro);
                    rd = req.getRequestDispatcher("index.jsp");
                }
            }
            case "insert" -> {
                u.setName(name);
                u.setCpf(cpf);
                u.setEmail(email);
                u.setPassword(password);
                if (us.insertUser(u)) {
                    System.out.println("cadastrou");
                    rd = req.getRequestDispatcher("index.jsp");
                }
            }
            case "editUser" -> {
                u = us.getUserId(id);
                req.setAttribute("user", u);
                rd = req.getRequestDispatcher("insertUser.jsp");
            }
            case "edit" -> {
                u.setId(id);
                u.setName(name);
                u.setCpf(cpf);
                u.setEmail(email);
                u.setPassword(password);
                if (us.editUser(u)) {
                    System.out.println("editou");
                    req.setAttribute("user", u);
                    rd = req.getRequestDispatcher("mainMenu.jsp");
                }
            }
            case "dell" -> {
                u.setId(id);
                if (us.dellUser(u.getId())) {
                    System.out.println("excluiu o user");
                    rd = req.getRequestDispatcher("index.jsp");
                }
            }
        }
    }
}

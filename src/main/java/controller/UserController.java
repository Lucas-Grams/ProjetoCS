package controller;

import model.User;
import service.UserService;

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
        switch (acao){
            case "login":
                User u = new User();
                u.setEmail(email);
                u.setPassword(password);
                UserService us = new UserService();
                if(us.login(u)){

                }

                break;
        }

    }
}

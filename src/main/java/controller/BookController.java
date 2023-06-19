package controller;


import model.Book;
import service.BookService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("BookController")
public class BookController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        String acao = req.getParameter("acao");
        BookService bs = new BookService();
        Book b = new Book();
        ArrayList<Book> books = new ArrayList<>();

        switch (acao) {
            case "listBook" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                books = bs.listBooks(idUser);
                req.getSession().setAttribute("books", books);
                req.getSession().setAttribute("idUser", idUser);
                resp.sendRedirect("listbook.jsp");
            }

            case "insert" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                req.getSession().setAttribute("idUser", idUser);
                resp.sendRedirect("insertBook.jsp");
            }

            case "insertBook" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                String title = req.getParameter("title");
                int note = Integer.parseInt(req.getParameter("note"));
                String plataform = req.getParameter("plataform");
                int pages = Integer.parseInt(req.getParameter("pages"));
                b.setTitle(title);
                b.setNote(note);
                b.setPlataform(plataform);
                b.setPages(pages);
                if (bs.insertBook(b, idUser)) {
                    System.out.println("livro inserido com sucesso");
                    books = bs.listBooks(idUser);
                    req.getSession().setAttribute("books", books);
                    req.getSession().setAttribute("idUser", idUser);
                    resp.sendRedirect("listBook.jsp");
                } else {
                    System.out.println("livro nao inserido, tente novamente mais tarde");
                    books = bs.listBooks(idUser);
                    req.getSession().setAttribute("books", books);
                    req.getSession().setAttribute("idUser", idUser);
                    resp.sendRedirect("listBook.jsp");
                }
            }
            case "edit" -> {
                int idBook = Integer.parseInt(req.getParameter("idBook"));
                Book book = new Book();
                book = bs.listBook(idBook);
                req.getSession().setAttribute("book", book);
                resp.sendRedirect("editBook.jsp");
            }
            case "editBook" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                int idBook = Integer.parseInt(req.getParameter("idBook"));
                String title = req.getParameter("title");
                int note = Integer.parseInt(req.getParameter("note"));
                String plataform = req.getParameter("plataform");
                int pages = Integer.parseInt(req.getParameter("pages"));
                b.setId(idBook);
                b.setTitle(title);
                b.setNote(note);
                b.setPlataform(plataform);
                b.setPages(pages);
                if (bs.editBook(b)) {
                    System.out.println("alterado com sucesso!");
                    books = bs.listBooks(idUser);
                    req.getSession().setAttribute("books", books);
                    req.getSession().setAttribute("idUser", idUser);
                    resp.sendRedirect("listBook.jsp");
                }
            }
            case "dellBook" -> {
                int idUser = Integer.parseInt(req.getParameter("id"));
                int idBook = Integer.parseInt(req.getParameter("idBook"));
                b.setId(idBook);
                if (bs.dellBook(b.getId())) {
                    System.out.println("excluido com sucesso");
                    books = bs.listBooks(idUser);
                    req.getSession().setAttribute("books", books);
                    req.getRequestDispatcher("listBook.jsp");
                }
            }
        }
    }
}
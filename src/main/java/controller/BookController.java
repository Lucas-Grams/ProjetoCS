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
        int idUser = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int note = Integer.parseInt(req.getParameter("note"));
        String plataform = req.getParameter("plataform");
        int pages = Integer.parseInt(req.getParameter("pages"));
        int idBook = Integer.parseInt(req.getParameter("idBook"));
        BookService bs = new BookService();
        Book b = new Book();
        ArrayList<Book> books = new ArrayList<>();

        switch (acao) {
            case "listBook" -> {
                books = bs.listBooks(idUser);
                req.setAttribute("books", books);
                req.setAttribute("idUser", idUser);
                req.getRequestDispatcher("listbook.jsp");
            }

            case "insert" -> {
                req.setAttribute("idUser", idUser);
                req.getRequestDispatcher("insertBook.jsp");
            }

            case "insertBook" -> {
                b.setTitle(title);
                b.setNote(note);
                b.setPlataform(plataform);
                b.setPages(pages);
                if (bs.insertBook(b, idUser)) {
                    System.out.println("livro inserido com sucesso");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listBook.jsp");
                } else {
                    System.out.println("livro nao inserido, tente novamente mais tarde");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listBook.jsp");
                }
            }
            case "edit" -> {
                Book book = new Book();
                book = bs.listBook(idBook);
                req.setAttribute("book", book);
                req.getRequestDispatcher("editBook.jsp");
            }
            case "editBook" -> {
                b.setId(idBook);
                b.setTitle(title);
                b.setNote(note);
                b.setPlataform(plataform);
                b.setPages(pages);
                if (bs.editBook(b)) {
                    System.out.println("alterado com sucesso!");
                    req.setAttribute("idUser", idUser);
                    req.getRequestDispatcher("listBook.jsp");
                }
            }
            case "dellBook" -> {
                b.setId(idBook);
                if (bs.dellBook(b.getId())) {
                    System.out.println("excluido com sucesso");
                    req.getRequestDispatcher("listBook.jsp");
                }
            }
        }
    }
}
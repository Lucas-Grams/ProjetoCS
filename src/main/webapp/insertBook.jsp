<%--
  Created by IntelliJ IDEA.
  User: Lucas Grams
  Date: 07/06/2023
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>InsertBook</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="header">
                <h2 class="text-center mb-4">Inserir Livro</h2>
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <button class="btn btn-danger">Sair</button>
                </div>
            </div>
            <div class="content">
                <form id="book-form" action="BookController">
                    <input type="hidden" name="acao" value="insertBook">
                    <input type="hidden" name="idUser" value=${idUser}>
                    <div class="form-group">
                        <label for="title">Título:</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>
                    <div class="form-group">
                        <label for="rating">Nota:</label>
                        <input type="number" class="form-control" id="rating" name="rating" step="0.1" min="0" max="10" required>
                    </div>
                    <div class="form-group">
                        <label for="pages">Páginas:</label>
                        <input type="text" class="form-control" id="pages" name="pages"  required>
                    </div>
                    <div class="form-group">
                        <label for="platform">Plataforma:</label>
                        <input type="text" class="form-control" id="platform" name="platform" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Inserir</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="script.js"></script>
</body>
</html>

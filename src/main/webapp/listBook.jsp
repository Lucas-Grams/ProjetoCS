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
    <title>ListBook</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <div class="header">
                <h2 class="text-center mb-4">Lista de Filmes</h2>
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <form action="BOOKController" method="post">
                        <input type="hidden" name="acao" value="insert"></input>
                        <input type="hidden" name="idUser" value=${idUser}></input>
                        <button type="submit" class="btn btn-primary">Adicionar Livros</button>
                    </form>
                    <button class="btn btn-danger">Sair</button>
                </div>
            </div>
            <div class="content">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Título</th>
                        <th>Nota</th>
                        <th>Páginas</th>
                        <th>Plataforma</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="b">
                        <tr>
                            <td>${b.getTitle()}</td>
                            <td>${b.getNote()}</td>
                            <td>${b.getPages()}</td>
                            <td>${b.getPlataform()}</td>
                            <td>
                                <form action="insertBook.jsp" method="post">
                                    <input type="hidden" name="acao" value="edit"></input>
                                    <input type="hidden" name="idBook" value=${b.getId()}></input
                                    <button type="submit" class="btn btn-primary btn-sm mr-2">Editar</button>
                                </form>
                                <form action="insertBook.jsp" method="post">
                                    <input type="hidden" name="acao" value="dellBook"></input>
                                    <input type="hidden" name="idBook" value=${b.getId()}></input
                                    <button type="submit" class="btn btn-primary btn-sm mr-2">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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

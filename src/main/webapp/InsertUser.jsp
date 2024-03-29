<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cadastro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <h2 class="text-center mb-4">Cadastro de Usuário</h2>
            <form id="register-form" method="post" action="UserController">
                <input type="hidden" name="acao" value="insert">
                <div class="form-group">
                    <label for="name">Nome:</label>
                    <input type="text" class="form-control" id="name" name="name" required placeholder="Meu Nome">
                </div>
                <div class="form-group">
                    <label for="cpf">CPF:</label>
                    <input type="text" class="form-control" id="cpf" name="cpf" required placeholder="xxx.xxx.xxx-xx">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required placeholder="email@email.com">
                </div>
                <div class="form-group">
                    <label for="password">Senha:</label>
                    <input type="password" class="form-control" id="password" name="password" required placeholder="insiraSuaSenha123">
                </div>
                <div class="form-group">
                    <label for="confirm-password">Confirmação de Senha:</label>
                    <input type="password" class="form-control" id="confirm-password" name="confirm-password" required placeholder="insiraSuaSenha123">
                </div>
                <button type="submit" class="btn btn-outline-success btn-block">Cadastrar</button>
                <a href="index.jsp" class="btn btn-outline-primary btn-block">Voltar</a>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="script.js"></script>
</body>
</html>

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Lista de Celulares - crud-manager-celulares</title>
<jsp:include page="/base-head.jsp" />
<style> body { padding-top: 20px; } .container { max-width: 1000px; } </style>
</head>
<body>
<div class="container">
<jsp:include page="/nav-menu.jsp" />

        <h2 class="mb-4">Lista de Celulares</h2>

        <c:if test="${requestScope.message != null}">
            <div class="alert <c:out value="${requestScope.alertType == 1 ? 'alert-success' : 'alert-danger'}" />" role="alert">
                <c:out value="${requestScope.message}" />
            </div>
        </c:if>

        <a href="<%= request.getContextPath() %>/celular/form" class="btn btn-primary mb-3">Adicionar Novo Celular</a>

        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Modelo</th>
                    <th>Cor</th>
                    <th>Armazenamento (GB)</th>
                    <th>Preço</th>
                    <th>Data Lançamento</th>
                    <th>Marca</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="celular" items="${requestScope.celulares}">
                    <tr>
                        <td>${celular.id}</td>
                        <td>${celular.modelo}</td>
                        <td>${celular.cor}</td>
                        <td>${celular.armazenamentoGB}</td>
                        <td><fmt:formatNumber value="${celular.preco}" type="currency" currencySymbol="R$" /></td>
                        <td><fmt:formatDate value="${celular.dataLancamento}" pattern="dd/MM/yyyy" /></td>
                        <td>${celular.marca.nome}</td>
                        <td>
                            <a href="<%= request.getContextPath() %>/celular/update?celularId=${celular.id}" class="btn btn-sm btn-info">Editar</a>
                            <a href="<%= request.getContextPath() %>/celular/delete?id=${celular.id}" class="btn btn-sm btn-danger" onclick="return confirm('Tem certeza que deseja excluir este celular?');">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
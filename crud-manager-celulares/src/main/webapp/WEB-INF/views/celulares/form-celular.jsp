
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title><c:out
		value="${action == 'update' ? 'Editar Celular' : 'Adicionar Novo Celular'}" />
	- crud-manager-celulares</title>
<jsp:include page="/base-head.jsp" />
<style>
body {
	padding-top: 20px;
}

.container {
	max-width: 600px;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="/nav-menu.jsp" />

		<h2 class="mb-4">
			<c:out
				value="${action == 'update' ? 'Editar Celular' : 'Adicionar Novo Celular'}" />
		</h2>

		<form action="<%= request.getContextPath() %>/celular/${action}"
			method="post">
			<c:if test="${action == 'update'}">
				<input type="hidden" name="celularId" value="${celular.id}" />
			</c:if>

			<div class="form-group">
				<label for="modelo">Modelo:</label> <input type="text"
					class="form-control" id="modelo" name="modelo"
					value="${celular.modelo}" required>
			</div>

			<div class="form-group">
				<label for="cor">Cor:</label> <input type="text"
					class="form-control" id="cor" name="cor" value="${celular.cor}"
					required>
			</div>

			<div class="form-group">
				<label for="armazenamentoGB">Armazenamento (GB):</label> <input
					type="number" class="form-control" id="armazenamentoGB"
					name="armazenamentoGB" value="${celular.armazenamentoGB}" required
					min="1">
			</div>

			<div class="form-group">
				<label for="preco">Preço:</label> <input type="number" step="0.01"
					class="form-control" id="preco" name="preco"
					value="${celular.preco}" required min="0.01">
			</div>

			<div class="form-group">
				<label for="dataLancamento">Data de Lançamento:</label> <input
					type="date" class="form-control" id="dataLancamento"
					name="dataLancamento"
					value="<c:if test="${celular.dataLancamento != null}"><fmt:formatDate value="${celular.dataLancamento}" pattern="yyyy-MM-dd" /></c:if>"
					required>
			</div>

			<div class="form-group">
				<label for="marca">Marca:</label> <select class="form-control"
					id="marca" name="marca" required>
					<option value="">Selecione uma Marca</option>
					<c:forEach var="marca" items="${requestScope.marcas}">
						<option value="${marca.id}"
							<c:if test="${celular.marca.id == marca.id}">selected</c:if>>
							<c:out value="${marca.nome}" />
						</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn btn-success">Salvar</button>
			<a href="<%=request.getContextPath()%>/celulares"
				class="btn btn-secondary">Cancelar</a>
		</form>
	</div>
</body>
</html>
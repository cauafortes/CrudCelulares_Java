
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login - crud-manager-celulares</title>
<jsp:include page="/base-head.jsp" />
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-color: #f8f9fa;
}

.login-container {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 400px;
}

.form-group {
	margin-bottom: 15px;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2 class="text-center mb-4">Login</h2>
		<c:if test="${requestScope.message != null}">
			<div
				class="alert <c:out value="${requestScope.alertType == 1 ? 'alert-success' : 'alert-danger'}" />"
				role="alert">${requestScope.message}</div>
		</c:if>
		<c:if test="${sessionScope.message != null}">
			<div
				class="alert <c:out value="${sessionScope.alertType == 1 ? 'alert-success' : 'alert-danger'}" />"
				role="alert">
				${sessionScope.message}
				<c:set var="message" value="${null}" scope="session" />
				<c:set var="alertType" value="${null}" scope="session" />
			</div>
		</c:if>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<div class="form-group">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="password">Senha:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<button type="submit" class="btn btn-primary btn-block">Entrar</button>
		</form>
	</div>
</body>
</html>
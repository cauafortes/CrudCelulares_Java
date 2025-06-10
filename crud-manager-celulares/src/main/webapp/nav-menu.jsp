<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand"
		href="<%=request.getContextPath()%>/index.jsp">crud-manager-celulares</a>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/index.jsp">Home (Posts)</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/users">Usuários</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/companies">Empresas</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/celulares">Celulares</a></li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<c:if test="${sessionScope.usuarioLogado != null}">
				<li class="nav-item"><span class="navbar-text">Olá,
						${sessionScope.usuarioLogado.name}!</span></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/logout">Sair</a></li>
			</c:if>
			<c:if test="${sessionScope.usuarioLogado == null}">
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/login">Login</a></li>
			</c:if>
		</ul>
	</div>
</nav>
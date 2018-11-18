<%@ page language="java" contentType="application/json; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ "players": [
<c:forEach var="p" items="${players}">
		{"id": ${p.key}, "user": ${p.value}}
	</c:forEach>
] }

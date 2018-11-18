<%@ page language="java" contentType="application/json; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{"games": [
<c:forEach var="d" items="${games}">
		{
			"id": ${d.key}, "players":[${d.value[0]}, ${d.value[1]}]
		}
	</c:forEach>
	] 
}

<%@ page language="java" contentType="application/json; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ "challenges": [
<c:forEach var="c" items="${challenges}">
		{"id": ${c.getChalId()}, "challenger": ${c.getChallenger()}, "challengee": ${c.getChallengee()}, "status": ${c.getStatus()}}
	</c:forEach>
] }

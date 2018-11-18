<%@ page language="java" contentType="application/json; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ 
"deck": {
	"id": "${ deckid}",
	"cards": [
	
	<c:forEach var="d" items="${deck}">
		{"t": ${d.getCardType()}, "n": ${d.getCardName()}}
	</c:forEach>
		] 
	} 
}

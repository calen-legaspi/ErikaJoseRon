<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="customerList" type="java.util.Collection" scope="request"></jsp:useBean>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System - History</title>
</head>
<body>
	<h1>History</h1>
	
	<form name="order" method="post" action="HistoryServlet"> 
		<table border="1" id="orderTable">
			<tr>
				<td>Customer: </td>
				<td><select name="customer">
					<c:forEach var="customer" items="${customerList}">
						<option value="${customer.id}">${customer.name}</option>
					</c:forEach>
					</select>
				</td>
				<td>
					<input value="View" type="submit" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
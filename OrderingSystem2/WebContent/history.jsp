<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="customerList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="orderList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="customerId" type="java.lang.Integer" scope="request"></jsp:useBean>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System - History</title>
</head>
<body>
	<h1>History</h1>
	
	<form name="order" method="post" action="orderhistory"> 
		<table id="orderTable">
			<tr>
				<td>Customer: </td>
				<td><select name="customer">
					<c:forEach var="customer" items="${customerList}">
					<c:choose>
					<c:when test="${customerId eq customer.id}">
						<option value="${customer.id}" selected="selected">${customer.name}</option>
					</c:when>
					<c:otherwise>
						<option value="${customer.id}">${customer.name}</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
				</td>
				<td>
					<input value="Check Customer" type="submit" />
				</td>
			</tr>
			
			<tr>
				<td>Orders:</td>
			</tr>
		</table>
	</form>
	<table>	
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
		<th>Status</th>
			
	<c:forEach var="order" items="${orderList}">
		<form method="post" action="vieworder">
			<tr>
				<td>${order.id}</td>
				<td>${order.date}</td>
				<td>${order.total}</td>
				<td>
					<c:choose>
					<c:when test="${order.status==1}"> Paid </c:when>
					<c:otherwise> Unpaid </c:otherwise>
					</c:choose>
				</td>
				<td>
					<input type="hidden" value="${order.id}" name="selectedOrder"/>
					<input type="submit" value="View" />
				</td>
			</tr>
		</form>
	</c:forEach>		
	</table>
	
</body>
</html>



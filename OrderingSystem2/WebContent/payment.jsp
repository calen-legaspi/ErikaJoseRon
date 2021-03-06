<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="customerList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="orderList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="customerId" type="java.lang.Integer" scope="request"></jsp:useBean>
<jsp:useBean id="show" type="java.lang.Boolean" scope="request"></jsp:useBean>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System - Payment</title>
</head>
<body>
<h1>Payment</h1>
	
	<form name="order" method="post" action="payorder"> 
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
		
	<c:if test="${show}">
		<table>	
			<tr>
				<td><select name="order">
				<c:forEach var="order" items="${orderList}">
				<option value="${order.id}">Order Id:${order.id}  ${order.date}  Total:${order.total}</option>
				</c:forEach>
				</select>	
				</td>
			</tr>
			<tr> <input type="submit" value="Set As Paid" /> </tr>
		</table>	
	</c:if>	
	</form>
</body>
</html>

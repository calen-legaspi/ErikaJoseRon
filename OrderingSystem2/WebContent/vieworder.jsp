<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="orderItemList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="totalAmt" type="java.math.BigDecimal" scope="request"></jsp:useBean>
<jsp:useBean id="customerId" type="java.lang.Integer" scope="request"></jsp:useBean>
<jsp:useBean id="orderStatus" type="java.lang.String" scope="request"></jsp:useBean>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System - View Order</title>
</head>
<body>

<h2> Customer Id: ${customerId} </h2>
<h3>Order is ${orderStatus}</h3>
<table>
	<th>Id</th>
	<th>Item</th>
	<th>Price</th>
	<th>Qty</th>
	<th>Total</th>
	<c:forEach var="order" items="${orderItemList}">
		<tr>
			<td>${order.id} </td>
			<td>${order.product.name} </td>
			<td>${order.product.price} </td>
			<td>${order.quantity }</td>
			<td>${order.amount }</td>
		</tr>
	</c:forEach>
	
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td>Total Amount:</td>
		<td>${totalAmt}</td>
	</tr>
</table>

</body>
</html>
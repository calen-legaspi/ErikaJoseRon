

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.onb.orderingsystem.domain.Product, com.onb.orderingsystem.service.InventoryItemServiceManager, java.util.Collection, com.onb.orderingsystem.domain.InventoryItem"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:useBean id="customerList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="productList" type="java.util.Collection" scope="request"></jsp:useBean>
<jsp:useBean id="numItems" type="java.lang.String" scope="request"></jsp:useBean>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System - Order</title>
	<!-- 
	<script language="Javascript" type="text/javascript">
		function addItem(){
			var table = document.getElementById('orderTable');
			var length = table.rows.length;
			var row = table.insertRow(length);
			var cellLeft = row.insertCell(0);
			
			var qtyField = document.createElement('input');
			qtyField.type = 'text';
			qtyField.name = 'quantity'+(table.rows.length-3);
			
			var cellRight = row.insertCell(1);
			cellRight.appendChild(qtyField);
			
			var productField = document.createElement('select');
			productField.name = 'product'+(table.rows.length-3);
			var productOpt = document.createElement('option');
			//productOpt.text = 'ipod';
			//productOpt.value = '1066';//skuNumber goes here
			//productField.options[0] = productOpt;
			
			<c:forEach var="product"	
			items="${productList}" varStatus="status">
				productField.option[${status.index}] = new Option('${product.name}', '${product.id}');
			</c:forEach> 	
			cellLeft.appendChild(productField);
			
		}
	</script>
	-->
</head>

<body>


	<h1>Order</h1>
	<form name="order" method="post" action="AddItemServlet"> 
		<table border="1" id="orderTable">
			<tr>
				<td>Customer: </td>
				<td><select name="customer">
					<c:forEach var="customer" items="${customerList}">
						<option value="${customer.id}">${customer.name}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<!--  <td><input type="button" name="addOrderItem" value="Add Item" onClick="addItem();"/> </td> -->
				<input value = "addItem" type = "submit" />  
				<td><input type="button" name="updateOrder" value="Update" /> </td>
			</tr>
			</table>
	</form>
	
	<form name="order" method="post" action="OrderServlet"> 
		<table border="1" id="orderTable">
			<tr>
				<td>OrderItems:</td>
				<td><input type = "hidden" name= "numItems" value="" /></td>
			</tr>
			<c:choose>
			<c:when test = "${numItems ne 0}">
      		<c:forEach var="i" begin="1" end="${numItems}" varStatus="status"><!-- use a javabean for this? -->
			<tr>
				<td><select name="product${i}">
					<c:forEach var="product" items="${productList}">
						<option value="${product.id}">${product.name}</option>
					</c:forEach>
					</select>
				</td>
				<td>
				<input type="text" size="25" name = "quantity${i}">
				</td>
			</tr>
			</c:forEach>
			</c:when>
			</c:choose>
		</table>
	</form>
</body>
</html>

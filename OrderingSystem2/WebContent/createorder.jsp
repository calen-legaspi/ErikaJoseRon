

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.onb.orderingsystem.service.CustomerServiceManager, com.onb.orderingsystem.domain.Customer"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp='http://java.sun.com/JSP/Page'>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System - Order</title>
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
			productOpt.text = 'coke';
			productOpt.value = '1066';//skuNumber goes here
			productField.options[0] = productOpt;
			cellLeft.appendChild(productField);
			
			/*<c:forEach var="product"	
			items="${productList}" varStatus="status">
				productField.option["${status.index}"] = new Option("${product.name}", "${product.skuNumber}");
			</c:forEach>
			
			*/
		}
		
	</script>

</head>

<body>
	<h1>Order</h1>
	<form name="order" method="post" action="OrderServlet"> 
		<table border="1" id="orderTable" name="orderTable">
			<tr>
				<td>Customer: </td>
				<td><select name="customer">
				
					<c:forEach var="customer" items="${customerList}">
						<option value="${customer.id}" ${customer.name}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="button" name="addOrderItem" value="Add Item" onClick="addItem();"/> </td>
				<td><input type="button" name="updateOrder" value="Update" /> </td>
			</tr>
		</table>
		<input type="submit" />
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordering System</title>
</head>
<body>
<h1>Ordering System</h1>
<form name="main" method="post" action="RedirectServlet">
	<select name="useCase">
		<option value="createOrder">Create Order</option>
		<option value="payment">Set Payment</option>
		<option value="history">Show Order History</option>
	</select>
	<input type="submit" />
</form>
</body>
</html>

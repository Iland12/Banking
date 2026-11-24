<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Register</h1>

<form action="register_servlet" method="post" style="display:flex; flex-direction:column; width:50rem; margin:3rem;">
	<input type="text" name="name" placeholder="Full name">
	<input type="email" name="email" placeholder="Email">
	<input type="tel" name="phone" placeholder="Phone">
	<input type="password" name="password" placeholder="Password">
	<input type="password" name="confirmPassword" placeholder="Confirm password">
	<button type="submit">Create Account</button>
</form>
</body>
</html>
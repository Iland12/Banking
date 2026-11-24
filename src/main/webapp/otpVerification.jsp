<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="VerifyOTPServlet" method="post">
<input type="text" name="otp" placeholder="Enter your Name" pattern="[0-9]{6}"
 maxlength="6"
required
> <br>

<input type="submit" value="Register"> <br>
</form>
</body>
</html>
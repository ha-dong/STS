<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WritePersonForm</title>
</head>
<body>
	<form name="writePersonForm"
		action="/springmvc2/person/writePersonProc.do" method="post">
		이름: <input type="text" name="name" /><br>
		나이: <input type="text" name="age" /><br>
		<input type="submit" value="등록" />
	</form>
</body>
</html>
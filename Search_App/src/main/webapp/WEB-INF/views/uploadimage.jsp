<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Uploaded Successfully</title>
</head>
<body>
     <h1>${msg}</h1>
     <img alt="Profile Image" src="<c:url  value="/resources/image/${filename}"/>"/>
</body>
</html>	
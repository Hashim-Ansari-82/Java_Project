<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Final Page</title>
</head>
<body>
<h1>Welcome ${student.name}</h1>
<h1>Id ${student.id}</h1>
<h1>Date of Birth ${student.dob}</h1>
<h1>Courses ${student.courses}</h1>
<h1>Gender ${student.gender}</h1>
<h1>Student Type  ${student.type}</h1>
                         <hr>
<h1>City  ${student.address.city}</h1>
<h1>State  ${student.address.state}</h1>
<h1>Pin Code  ${student.address.pinCode}</h1>
</body>
</html>
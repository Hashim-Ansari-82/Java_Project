<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complex Form</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body{
        background-color:#f8f9fa;
    }
    .form-container{
    width: 500px;
    margin: 10px auto;      
    padding: 15px;          
    background: white;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

</style>

</head>
<body>

<div class="form-container">
    <h3 class="text-center mb-8">Complex Form</h3>
   <div class="alert alert-danger" role="alert">
    <form:errors path="student.*" />
</div>
    <form action="submitForm" method="post">

        <!-- Name -->
        <div class="form-group">
            <label>Your name</label>
            <input type="text" class="form-control" name="name" placeholder="Enter Name">
            <small class="form-text text-muted">
                We'll never share your name with anyone else.
            </small>
        </div>

        <!-- ID -->
        <div class="form-group">
            <label>Your id</label>
            <input type="text" class="form-control" name="id" placeholder="Enter ID">
        </div>

        <!-- DOB -->
        <div class="form-group">
            <label>Your DOB</label>
            <input type="text" class="form-control" name="dob" placeholder="dd/mm/yyyy">
        </div>

        <!-- Select Courses -->
        <div class="form-group">
            <label>Select Courses</label>
            <select multiple class="form-control" name="courses">
                <option>Java</option>
                <option>Python</option>
                <option>C++</option>
                <option>Django</option>
                <option>C</option>
                <option>Skala</option>
                <option>Spring Boot</option>
            </select>
        </div>

        <!-- Gender -->
        <div class="form-group">
            <label>Select Gender</label><br>
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female" class="ml-3"> Female
        </div>

        <!-- Type -->
        <div class="form-group">
            <label>Select Type</label>
            <select class="form-control" name="type">
                <option>Old Student</option>
                <option>New Student</option>
            </select>
        </div>

   <div class="card">
        <div class="card-body">
        <p>Your Details</p>
        
        <div class="form-group">
            <input
            name="address.city"
             type="text"
            class="form-control"
            placeholder="City">
        </div>
        <div class="form-group">
            <input 
            name="address.state"
            type="text"
            class="form-control"
            placeholder="State">
        </div>
        <div class="form-group">
            <input
              name="address.pinCode"
             type="text"
            class="form-control"
            placeholder="Pin Code">
        </div>
           
        </div>
   </div>

        <!-- Submit -->
        <div class="text-center">
            <button type="submit" class="btn btn-primary px-4">Submit</button>
        </div>

    </form>
</div>

</body>
</html>
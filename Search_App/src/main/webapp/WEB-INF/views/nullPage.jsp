<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            margin: 0; padding: 0; font-family: Arial, sans-serif; background: #f7f7f7;
        }

        .container {
            width: 100%; text-align: center; margin-top: 40px;
        }

        h1 { 
        font-size: 28px;  color: #333;  margin-bottom: 5px;
        }

        h2 {
            font-size: 22px;color: #222;margin: 0;font-weight: bold;
        }

        p {
            color: #555;font-size: 14px;margin-top: 10px;
        }

        .links {
            margin-top: 25px;
        }

        .links a {
            text-decoration: none; color: #1a73e8; font-size: 18px; margin: 0 15px;
        }

        .links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Oops! Sorry</h1>
        <h2> ${msg} </h2>
        <p>Sorry, an error has occured, Requested page not found!</p>

        <div class="links">
            <a href="home">Take Me Home</a>
            <a href="contact">Contact Support</a>
        </div>
    </div>

</body>
</html>
<%-- 
    Document   : login
    Created on : Apr 25, 2025, 9:38:58 AM
    Author     : thari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        

        <form action="login" method="post"> 
          Username: <input type="text" name="username"/><br/> 
          Password: <input type="password" name="password"/><br/> 
          <div class="g-recaptcha" data-sitekey="6LcVyiorAAAAACKMnz1BFHoygTDqz01js_NWbSPB"></div>
          <input type="hidden" name="g-recaptcha-response" id="g-recaptcha-response">
          
          <input type="submit" value="Login"/> 
        </form> 
        
       

        
        
        
        
        
    </body>
</html>

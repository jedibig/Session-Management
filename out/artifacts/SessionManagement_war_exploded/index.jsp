+++++<%--
  Created by IntelliJ IDEA.
  User: jedidiahbowo
  Date: 4/1/20
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <form action="login" method="post" style="align-content: center">
      <label for="username"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" id="username" name="username" required>
      <br/>
      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" id="password" name="password" required>
      <input type="submit" value="login">
    </form>
  </body>
</html>

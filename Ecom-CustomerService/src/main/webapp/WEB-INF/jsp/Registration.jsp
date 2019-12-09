<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
    
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User Registration Page</title>

<script type="text/javascript">
            function validate()
            {
                var name = document.getElementById("name");
                var password = document.getElementById("password");
                var email = document.getElementById("email");
                var mobile = document.getElementById("mobile");
                var zipcode = document.getElementById("zipcode");
                var valid = true;
                if(name.value.length<=0 || password.value.length<=0 || email.value.length <=0 || zipcode.value.length <=0 || mobile.value.length<=0)
                    {
                        alert("Don't leave the field empty!");
                        valid = false;
                    }
                    
                        if(!IsNumeric(mobile.value) || !IsNumeric(zipcode.value))
                            alert("Enter a number");
                    valid = false;
                
                return valid;
            };

        </script>
         <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>

<h2> Register Here !!!!!!!</h2>
<form action="${contextPath}/createCustomer" method="post" onsubmit="return validate();">
			<table style="with: 50%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name="first_name" id="name" placeholder="Enter your name" /></td><td> </td>
				</tr>
				
				<tr>
					<td> Gender</td>
					<td><input type="radio" name="gender" value="male" checked> Male<br>
  <input type="radio" name="gender" value="female"> Female<br>
  </td><td> </td>
  </tr>			
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" id="password" placeholder="Password"/></td><td> </td>
				</tr>
				<tr>
					<td>Email Id</td>
					<td><input type="text" name="emailId" id="email" placeholder="Email Id" /></td><td> </td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><input type="text" name="mobileNum" id="mobile" placeholder="Mobile Number"/></td><td> </td>
				</tr>
				
				<tr>
					<td>Zipcode</td>
					<td><input type="text" name="zipcode" id="zipcode" placeholder="Zipcode"/></td><td> </td>
				</tr>
				
				</table><br>
			<input type="submit" value="Submit" /></form>
			
			<h4 class="text-center"><a href="${contextPath}/welcome">Login? If you already registered</a></h4>
</body>
</html>


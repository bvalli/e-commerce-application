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
</head>
<body>
<h6><c:out value="${customer.custName}"/> logged in successfully</h6>
 <form method="POST" action="${contextPath}/addToCart" class="form-signin">
<table style="with: 50%" border="1px">
				
		<tr><td>Product Name</td> <td> Product Description </td><td> Price</td> <td>Quantity</td></tr>			
				
				<c:forEach items="${prod}" var="prod">
    <tr>
        <td> <input type="hidden" name="productId" value="${prod.productId}"/><c:out value="${prod.productName}"/></td>
        <td> <c:out value="${prod.productDescription}"/></td>  
        <td><input type="hidden" name="price" value="${prod.productPrice}"/> <c:out value="${prod.productPrice}"/></td>  
          <td><input type="hidden" name="quantity" value="${prod.quantityLeft}"/> <c:out value="${prod.quantityLeft}"/></td> 
          <td><input type="submit" value="Add to Cart" /></td> 
    </tr>
</c:forEach>

</table></form>
</body>
</html>
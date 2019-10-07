<%-- 
    Document   : register
    Created on : Oct 3, 2019, 4:16:48 PM
    Author     : 738634
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}
        <a href="/shoppinglist?action=logout">Logout</a>
        <h2>List</h2>
        <label>Add an item</label>
        <form action="" method="post">
        <input type="text" name="itemToAdd" value="" />
        <input type="hidden" name="action" value="addItem">
        <input type="submit" value="Add" /><br>
        </form>
            <form action="" method="post">
                 <table>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td><input type="radio" name="addedItems" value="${item}" />${item}</td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${list != null}">
            <input type="submit" value="delete"/>
            <input type="hidden" name="action" value="delete">
        </c:if>
        </form>
    </body>
</html>

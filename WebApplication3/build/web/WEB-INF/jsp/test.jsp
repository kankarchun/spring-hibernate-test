<%-- 
    Document   : test
    Created on : 2017年3月18日, 下午07:06:14
    Author     : 1
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form:form method = "POST" action = "addTest.htm" modelAttribute="test" >
            <table>
                <tr>
                    <td><form:label path = "name">Name</form:label></td>
                    <td><form:input path = "name" /></td>
                </tr>
                <tr>
                    <td colspan = "2">
                        <input type = "submit" value = "Submit"/>
                    </td>
                </tr>
            </table>  
        </form:form>
    </body>
</html>

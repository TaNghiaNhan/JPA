<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/10/2024
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<title>Edit category</title>
<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
    <input type="text" id="categoryId" name="categoryID" value="${cate.categoryID}" readonly><br>
    <label for="categoryname">Category Name: </label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
    <label for="images">Images: </label><br>
    <c:if test="${cate.images.substring(0,5) !='https'}">
        <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <c:if test="${cate.images.substring(0,5) == 'https'}">
        <c:url value="${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <img height="150" width="200" src="${imgUrl}"/> <br>
    <input type="file" id="images" name="images" value="${cate.images}"><br>
    <label for="status">Status: </label><br>
    <input type="text" id="status" name="status" value="${cate.status}"><br><br>

    <input type="submit" value="Submit">
</form>

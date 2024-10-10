<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/10/2024
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Edit video</title>
<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data" >
  <label for="cateID">Category ID:</label> <br>
  <input type="text" name="cateID" id="cateID" readonly value="${category.categoryID}" style="text-align: center"> <br><br>

  <label for="videoid">Video ID:</label> <br>
  <input type="text" name="videoid" id="videoid" value="${vId.videoId}" readonly style="text-align: center"> <br> <br>

  <label for="images">Poster: </label><br>
  <c:if test="${vId.poster.substring(0,5) !='https'}">
    <c:url value="/gif?fname=${vId.poster}" var="imgUrl"></c:url>
  </c:if>
  <c:if test="${vId.poster.substring(0,5) == 'https'}">
    <c:url value="${vId.poster}" var="imgUrl"></c:url>
  </c:if>
  <img height="150" width="200" src="${imgUrl}" /> <br>
  <input type="file" id="images" name="images" value="${vId.poster}"><br>

  <label for="title">Title: </label><br>
  <input type="text" id="title" name="title" value="${vId.title}"><br><br>

  <label for="description">Description: </label><br>
  <input type="text" id="description" name="description" value="${vId.description}"><br><br>

  <label for="views">Views: </label><br>
  <input type="number" id="views" name="views" value="${vId.views}"><br><br>

  <label for="active">Active: </label><br>
  <input type="text" id="active" name="active" value="${category.status}" readonly style="text-align: center"><br><br>

  <input type="submit" value="Update">
</form>

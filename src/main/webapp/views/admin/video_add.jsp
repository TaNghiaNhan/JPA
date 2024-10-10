<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/10/2024
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Add Video</title>
<h1>Add Video</h1>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">

  <label for="cateID">Category ID:</label> <br>
  <input type="text" name="cateID" id="cateID" readonly value="${category.categoryID}" style="text-align: center"> <br><br>

  <label for="videoid">Video ID:</label> <br>
  <input type="text" name="vId" id="videoid"> <br> <br>

  <label for="title">Title: </label><br>
  <input type="text" id="title" name="title"><br><br>

  <label for="description">Description: </label><br>
  <input type="text" id="description" name="description"><br><br>

  <label for="poster">Poster: </label><br>
  <img height="150" width="200" src=""/> <br>
  <input type="file" id="poster" name="poster"><br> <br>

  <label for="views">Views: </label><br>
  <input type="number" id="views" name="views"><br><br>

  <label for="status">Active: </label><br>
  <input type="text" id="status" name="status" value="${category.status}" readonly style="text-align: center"><br><br>

  <input type="submit" value="Add">
</form>

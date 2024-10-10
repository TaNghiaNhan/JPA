<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/10/2024
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<a href="${pageContext.request.contextPath}/admin/video/add">Add video</a> <br>
<a href="${pageContext.request.contextPath}/admin/categories">Category list</a>
<table border="1" width="100%">
    <tr>
        <th>Video ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Poster</th>
        <th>Views</th>
        <th>Active</th>
        <th>Chosse</th>
    </tr>
    <c:forEach var="video" items="${videoList}">
        <tr>
            <td style="text-align: center">${video.videoId}</td>
            <td style="text-align: center">${video.title}</td>
            <td style="text-align: center">${video.description}</td>
            <td style="width: 300px; height: 200px; overflow: hidden;">
                <img src="${pageContext.request.contextPath}/gif?fname=${video.poster}"
                     alt="${video.title}"
                     style="width: 100%; height: 100%; object-fit: cover; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);" />
            </td>

            <td style="text-align: center">${video.views}</td>
            <td style="text-align: center">${video.active == 1 ? 'Active' : 'Inactive'}</td>
            <td style="text-align: center">
                <a href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Edit</a>
                <a href="<c:url value='/admin/video/delete?id=${video.videoId}'/>" >Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

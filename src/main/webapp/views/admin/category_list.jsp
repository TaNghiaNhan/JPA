<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/10/2024
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<title>Category list</title>
<a href="${pageContext.request.contextPath}/admin/category/add">Add category</a>
<table border="1" width="100%">
  <tr>
    <th>STT</th>
    <th>Images</th>
    <th>CategoryID</th>
    <th>CategoryName</th>
    <th>Status</th>
    <th>Action</th>
  </tr>
  <c:forEach items="${listcate}" var="cate" varStatus="STT" >
    <tr>
      <td style="text-align: center">${STT.index+1 }</td>
      <td style="text-align: center">
        <c:if test="${cate.images.substring(0,5) !='https'}">
          <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
        </c:if>
        <c:if test="${cate.images.substring(0,5) == 'https'}">
          <c:url value="${cate.images}" var="imgUrl"></c:url>
        </c:if>
        <img height="150" width="200" src="${imgUrl}" />
      </td>
      <td style="text-align: center">${cate.categoryID }</td>
      <td style="text-align: center">${cate.categoryname }</td>
      <td style="text-align: center">
        <c:if test="${cate.status==1 }">
          <span>Hoạt động</span>
        </c:if>
        <c:if test="${cate.status!=1 }">
          <span>Khóa</span>
        </c:if>
      </td>
      <td style="text-align: center">
        <a href="<c:url value='/admin/category/edit?id=${cate.categoryID }'/>">Sửa</a>
        | <a href="<c:url value='/admin/category/delete?id=${cate.categoryID }'/>">Xóa</a>
        | <a href="<c:url value='/admin/videos?id=${cate.categoryID }'/>" >Video</a>
      </td>
    </tr>
  </c:forEach>
</table>

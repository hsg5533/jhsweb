<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../includes/header.jsp"%>
<!-- 파일 업로드 폼 기본 공식 -->
<form action="uploadFormAction" method="post" enctype="multipart/form-data">
  <!-- multiple: 한꺼번에 업로드 가능. 버전에 따라 제한이 있다. -->
  <input type="file" name="uploads" multiple />
  <input type="submit" value="제출" />
</form>

<%@ include file="../includes/footer.jsp"%>

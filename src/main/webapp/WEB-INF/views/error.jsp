<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="includes/header.jsp"%>
<section class="header">
  <div class="title">
    <h1>에러가 발생하였습니다.</h1>
    <p>다시 시도 하시거나 관라지에게 문의바랍니다.</p>
    <br />
    <button type="button" class="btn btn-primary btn-sm" id="btnBack">
      홈으로 돌아가기
    </button>
  </div>
</section>
<script>
  $("#btnBack").click(function () {
    location.href = "/index";
  });
</script>
<%@ include file="includes/footer.jsp"%>

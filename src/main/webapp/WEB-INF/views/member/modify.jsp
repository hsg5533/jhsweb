<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../includes/header.jsp"%>
<section class="header">
  <div class="title">
    <fieldset>
      <h2>${member.id}의회원수정</h2>
      <form action="#" method="post">
        <div class="id">
          <input
            type="text"
            class="form-control"
            id="id"
            placeholder="Enter id"
            name="id"
            value="${member.id}"
            readonly
          />
        </div>
        <br />
        <div class="pass">
          <input
            type="password"
            class="form-control"
            id="pass"
            placeholder="Enter password"
            name="pass"
            value="${member.pass}"
          />
        </div>
        <br />
        <div class="password_check">
          <input
            type="password"
            class="form-control"
            id="pass_check"
            placeholder="Re-Enter password"
            name="pass_check"
            value="${member.pass}"
          />
        </div>
        <br />
        <div class="name">
          <input
            type="text"
            class="form-control"
            id="name"
            placeholder="Enter name"
            name="name"
            value="${member.name}"
          />
        </div>
        <br />
        <div class="addr">
          <input
            type="text"
            class="form-control"
            id="addr"
            placeholder="Enter address"
            name="addr"
            value="${member.addr}"
          />
        </div>
        <br />
        <button type="button" class="btn btn-primary btn-sm" id="btnModify">
          회원수정
        </button>
        <c:if test="${sMember.id != 'admin' }">
          <button type="button" class="btn btn-primary btn-sm" id="btnDelete">
            회원탈퇴
          </button>
        </c:if>
      </form>
    </fieldset>
  </div>
</section>
<script>
  $("#btnDelete").click(function () {
    if (confirm("정말 탈퇴할까요?")) {
      location.href = "/member/delete/${member.id}";
      alert("탈퇴가 완료되었습니다.");
    }
  });
</script>
<script type="text/javascript" src="/js/member.js"></script>
<% String strReferer = request.getHeader("referer"); if (strReferer == null) {
%>
<script language="javascript">
  //이전 URL 가져오기
  location.href = "/ban/modifyban/${member.id}";
</script>
<% } %> <%@ include file="../includes/footer.jsp"%>

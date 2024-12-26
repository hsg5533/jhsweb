<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../includes/header.jsp"%>
<section class="header">
  <div class="title">
    <fieldset>
      <h2>로그인</h2>
      <form action="/member/login" method="post">
        <div class="id">
          <input
            type="text"
            class="form-control"
            id="id"
            placeholder="아이디 입력"
            name="id"
          />
        </div>
        <br />
        <div class="pass">
          <input
            type="password"
            class="form-control"
            id="pass"
            placeholder="비밀번호 입력"
            name="pass"
          />
        </div>
        <br />
        <button type="button" class="btn btn-primary btn-sm" id="btnLogin">
          로그인
        </button>
      </form>
    </fieldset>
  </div>
</section>
<script type="text/javascript" src="/js/member.js"></script>
<%@ include file="../includes/footer.jsp"%>

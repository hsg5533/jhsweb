<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../includes/header.jsp"%>
<section class="header">
  <div class="title">
    <fieldset>
      <h2>회원가입</h2>
      <form action="#" method="post">
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
        <div class="password_check">
          <input
            type="password"
            class="form-control"
            id="pass_check"
            placeholder="비밀번호 확인"
            name="pass_check"
          />
        </div>
        <br />
        <div class="name">
          <input
            type="text"
            class="form-control"
            id="name"
            placeholder="이름 입력"
            name="name"
          />
        </div>
        <br />
        <div class="addr">
          <input
            type="text"
            class="form-control"
            id="addr"
            placeholder="주소 입력"
            name="addr"
          />
        </div>
        <br />
        <button type="button" class="btn btn-primary btn-sm" id="btnJoin">
          회원가입
        </button>
      </form>
    </fieldset>
  </div>
</section>
<script type="text/javascript" src="/js/member.js"></script>
<%@ include file="../includes/footer.jsp"%>

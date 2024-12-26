<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>Document</title>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css"
    />
    <link rel="stylesheet" href="/css/main.css" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="nav">
      <div class="logo">
        <img src="/images/logo.png" alt="logo" />
      </div>
      <div class="nav_but">
        <a href="/index">Home</a> <a href="/board/list">Community</a>
        <c:if test="${sMember.id  == 'admin' }"
          ><a class="join" href="/member/memberlist">MemberList</a></c:if
        >
        <!-- 세션 정보가 비어있다면. 즉, 로그인을 하지 않았다면. -->
        <c:choose>
          <c:when test="${empty sessionScope.sMember}">
            <a class="join" href="/member/join">Join</a>
            <a class="login" href="/member/login">Login</a>
          </c:when>
          <c:otherwise>
            <!-- 로그인 정보표시 및 로그아웃 버튼 표시 -->
            <a class="modify" href="/member/modify/${sMember.id}"
              >My info(${sMember.id})</a
            >
            <a class="logout" href="/member/logout">Logout(${sMember.id})</a>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </body>
</html>

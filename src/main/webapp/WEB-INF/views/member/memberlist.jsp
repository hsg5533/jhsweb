<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@ include
file="../includes/header.jsp"%>
<br />
<section class="serv_list">
  <div class="container">
    <h3>회원 목록(${count})</h3>
    <br />
    <table class="table table-hover">
      <thead>
        <tr>
          <th>아이디</th>
          <th>비밀번호</th>
          <th>이름</th>
          <th>주소</th>
          <th>가입일자</th>
          <th>회원추방</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${memberlist}" var="memberlist">
          <tr>
            <!-- MemberList에서 회원 수정이 가능. -->
            <td>
              <a href="/member/modify/${memberlist.id}">${memberlist.id}</a>
            </td>
            <td>${memberlist.pass}</td>
            <td>${memberlist.name}</td>
            <td>${memberlist.addr}</td>
            <td>
              <fmt:formatDate
                pattern="yyyy-MM-dd"
                value="${memberlist.regdate}"
              />
            </td>
            <!-- 회원추방 -->
            <c:if test="${sMember.id != memberlist.id }">
              <td>
                <a href="/member/ban/${memberlist.id}" id="btnDelete"
                  >회원추방</a
                >
              </td>
            </c:if>
            <c:if test="${memberlist.id == 'admin'}">
              <td>admin</td>
            </c:if>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="d-flex justify-content-between mt-3">
      <ul class="pagination">
        <!-- 이전 -->
        <c:if test="${p.startPage>p.blockSize }">
          <li class="page-item">
            <a
              class="page-link"
              href="memberlist?pageNum=${p.startPage-p.blockSize}&field=${field}&word=${word}"
              >Previous</a
            >
          </li>
        </c:if>
        <!--페이지 리스트-->
        <c:forEach begin="${p.startPage}" end="${p.endPage}" var="i">
          <li class="page-item">
            <a
              class="page-link"
              href="memberlist?pageNum=${i}&field=${field}&word=${word}"
              >${i}</a
            >
          </li>
        </c:forEach>
        <!-- 다음 -->
        <c:if test="${p.endPage < p.totPage }">
          <li class="page-item">
            <a
              class="page-link"
              href="memberlist?pageNum=${p.endPage+1}&field=${field}&word=${word}"
              >Next</a
            >
          </li>
        </c:if>
      </ul>
      <form class="form-inline" action="/member/memberlist" id="searchFrm">
        <select name="field" class="form-control mb-2 mr-sm-2">
          <option value="" disabled selected>--</option>
          <option value="id" ${ field=='id' ? 'selected' : '' }>아이디 <option
          value="name" ${ field=='name' ? 'selected' : '' }>이름 <option
          value="addr" ${ (field=='addr') ? 'selected' : '' }>주소 <option
          value="regdate" ${ (field=='regdate') ? 'selected' : '' }>가입날짜
        </select>
        <input
          type="text"
          class="form-control mb-2 mr-sm-2"
          placeholder="Enter Search"
          name="word"
          value="${word}"
        />
        <button type="submit" class="btn btn-secondary mb-2 btn-sm">
          Search
        </button>
      </form>
    </div>
  </div>
</section>
<br />
<% String strReferer = request.getHeader("referer"); if (strReferer == null) {
%>
<script language="javascript">
  //이전 URL 가져오기
  location.href = "/ban/memberlistban";
</script>
<% } %> <%@ include file="../includes/footer.jsp"%>

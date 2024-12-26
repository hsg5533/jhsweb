<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@ include
file="../includes/header.jsp"%>
<section class="header">
  <div class="title">
    <h1>Community</h1>
    <p>다른사람과 소통하면서 시작해보세요.</p>
  </div>
</section>
<br />
<section class="serv_list">
  <div class="container">
    <h2>게시글 목록(${count})</h2>
    <div class="form-group text-right">
      <!-- 세션이 비어있지 않다면. 즉, 로그인을 하였을 때 -->
      <c:if test="${not empty sessionScope.sMember}">
        <button type="button" class="btn btn-secondary btn-sm" id="btnWrite">
          글쓰기
        </button>
      </c:if>
    </div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일시</th>
          <th>조회수</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="board">
          <tr>
            <td>${board.bno}</td>
            <td>
              <a
                href="/board/detail?bno=${board.bno}&pageNum=${p.currentPage}&field=${field}&word=${word}"
                >${board.title}</a
              >
            </td>
            <td>${board.writer}</td>
            <td>
              <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" />
            </td>
            <td>${board.hitcount}</td>
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
              href="list?pageNum=${p.startPage-p.blockSize}&field=${field}&word=${word}"
              >Previous</a
            >
          </li>
        </c:if>
        <!--페이지 리스트-->
        <c:forEach begin="${p.startPage}" end="${p.endPage}" var="i">
          <li class="page-item">
            <a
              class="page-link"
              href="list?pageNum=${i}&field=${field}&word=${word}"
              >${i}</a
            >
          </li>
        </c:forEach>
        <!-- 다음 -->
        <c:if test="${p.endPage < p.totPage }">
          <li class="page-item">
            <a
              class="page-link"
              href="list?pageNum=${p.endPage+1}&field=${field}&word=${word}"
              >Next</a
            >
          </li>
        </c:if>
      </ul>
      <form class="form-inline" action="/board/list" id="searchFrm">
        <select name="field" class="form-control mb-2 mr-sm-2">
          <option value="" disabled selected>--</option>
          <option value="title" ${ field=='title' ? 'selected' : '' }>제목
          <option value="writer" ${ field=='writer' ? 'selected' : '' }>내용
          <option value="content" ${ (field=='content') ? 'selected' : ''
          }>작성자 <option value="cwt" ${ (field=='cwt') ? 'selected' : ''
          }>제목or내용or작성자
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
<%@ include file="../includes/footer.jsp"%>
<script type="text/javascript">
  $("#btnWrite").click(function () {
    location.href = "/board/register";
  });
</script>

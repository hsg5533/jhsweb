<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@ include
file="../includes/header.jsp"%>
<br />
<div class="container">
  <h2>${board.writer}의글보기</h2>

  <div class="form-group">
    <label for="num">글번호</label>
    <input
      type="text"
      class="form-control"
      id="bno"
      name="num"
      value="${board.bno}"
      readonly="readonly"
    />
  </div>
  <div class="form-group">
    <label for="title">제목</label>
    <input
      type="text"
      class="form-control"
      id="title"
      name="title"
      value="${board.title}"
      readonly="readonly"
    />
  </div>
  <div class="form-group">
    <label for="writer">작성자</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      name="writer"
      value="${board.writer}"
      readonly="readonly"
    />
  </div>
  <div class="form-group">
    <label for="file">첨부파일</label>
    <div>
      <ul>
        <c:forEach items="${board.fileList}" var="fileInfo">
          <li style="list-style: nome">
            <!-- 이미지 파일인 경우 -->
            <c:choose>
              <c:when test="${fileInfo.filetype=='image'}">
                <img
                  src="/resources/upload/${fileInfo.savefolder}/${fileInfo.savefile}"
                  height="50"
                />
              </c:when>
              <c:otherwise>
                <img src="/resources/upload/file.png" height="50" />
              </c:otherwise>
            </c:choose>
            ${fileInfo.originfile}
            <a
              class="filedown"
              href="#"
              fno="${fileInfo.fno}"
              sfolder="${fileInfo.savefolder}"
              ofile="${fileInfo.originfile }"
              sfile="${fileInfo.savefile}"
              >[다운로드]</a
            >
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div class="form-group">
    <label for="content">내용</label>
    <textarea
      class="form-control"
      rows="5"
      id="content"
      name="content"
      readonly="readonly"
    >
${board.content}</textarea
    >
  </div>
  <div class="form-group">
    <label for="writer">등록일</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      name="writer"
      value="${board.regdate}"
      readonly="readonly"
    />
  </div>
  <div class="form-group">
    <label for="writer">수정일</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      name="writer"
      value="${board.updatedate}"
      readonly="readonly"
    />
  </div>
  <div class="form-group">
    <label for="writer">조회수</label>
    <input
      type="text"
      class="form-control"
      id="writer"
      name="writer"
      value="${board.hitcount}"
      readonly="readonly"
    />
  </div>

  <div class="form-group text-right">
    <!-- 세션의 로그인 값과 게시글의 작성자가 같을 때. 즉, 로그인한 아이디와 게시글의 작성자가 같을 경우 -->
    <c:if test="${sMember.id==board.writer || sMember.id=='admin'}">
      <button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">
        수정하기
      </button>
      <button type="button" class="btn btn-secondary btn-sm" id="btnDelete">
        삭제하기
      </button>
    </c:if>
    <button type="button" class="btn btn-secondary btn-sm" id="btnList">
      목록보기
    </button>
  </div>
  <div class="container mt-5">
    <div class="form-group">
      <label for="comment">Comment:</label>
      <textarea class="form-control" rows="5" id="reply" name="text"></textarea>
    </div>
    <button type="button" class="btn btn-success" id="replyBtn">
      Reply Write
    </button>
  </div>
  <div id="replyResult"></div>
</div>
<br />
<script type="text/javascript">
  // 다운로드 링크를 눌렀을 경우
  $(".filedown").click(function () {
    // 현재 해당하는 fno의 값을 가져온다.
    var fno = $(this).attr("fno");
    // 파일 번호, 원본파일, 저장된 파일을 출력
    alert(
      "fno:" +
        fno +
        "원본:" +
        $(this).attr("ofile") +
        "실제:" +
        $(this).attr("sfile")
    );
    location.href = "/board/download/" + fno;
  });
  var init = function () {
    // ajax를 통해 get방식으로 bno를 전송한다.
    $.ajax({
      type: "get",
      url: "/replies/getList/${board.bno}",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
    }).done(function (resp) {
      var str = "<table class='table table-hover mt-3'>"; // 테이블 태그 열기
      // 댓글을 각각 하나씩 가져온다.
      $.each(resp, function (key, val) {
        // 댓글
        str += "<tr>";
        str += "<td>" + val.replyer + "</td>";
        str += "<td>" + val.reply + "</td>";
        str += "<td>" + val.replydate + "</td>";
        // 로그인한 아이디와 댓글 작성자의 아이디가 같을 경우
        if ("${sessionScope.sMember.id}" == val.replyer) {
          str += "<td><a href='javascript:fdel(" + val.rno + ")'>삭제</a></td>"; // 삭제를 누르면 fdel 함수를 호출한다.
        }
        str += "</tr>";
      });
      str += "</table>"; // 테이블 태그 닫기
      $("#replyResult").html(str); //아이디가 replyResult인 태그에 str을 삽입한다.
    });
  };
  // 댓글 삭제하기
  function fdel(rno) {
    $.ajax({
      type: "delete",
      url: "/replies/" + rno,
    })
      .done(function (resp) {
        alert(rno + "번 글 삭제완료");
        init();
      })
      .fail(function () {
        alert("댓글 삭제 실패");
      });
  }
  $("#replyBtn").click(function () {
    // 로그인을 하지 않았을 경우
    if ("${empty sessionScope.sMember}") {
      alert("로그인을 해주세요.");
      location.href = "/member/login";
      return;
    }
    var data = {
      bno: $("#bno").val(),
      reply: $("#reply").val(),
      replyer: "${sMember.id}", // 로그인한 사용자
    };
    $.ajax({
      type: "post",
      url: "/replies/new",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(data),
    })
      .done(function (resp) {
        alert("댓글 추가 성공");
        init();
      })
      .fail(function () {
        alert("댓글 추가 실패");
      });
  });

  $("#btnUpdate").click(function () {
    if (confirm("정말 수정할까요?")) {
      location.href = "/board/update/${board.bno}";
    }
  });

  $("#btnDelete").click(function () {
    if (confirm("정말 삭제할까요?")) {
      location.href = "/board/delete/${board.bno}";
    }
  });

  $("#btnList").click(function () {
    if (confirm("정말 이동할까요?")) {
      location.href =
        "/board/list?pageNum=${pageNum}&field=${field}&word=${word}";
    }
  });

  init();
</script>
<% String strReferer = request.getHeader("referer"); if (strReferer == null) {
%>
<script language="javascript">
  //이전 URL 가져오기
  location.href = "/ban/detailban/${board.bno}";
</script>
<% } %> <%@ include file="../includes/footer.jsp"%>

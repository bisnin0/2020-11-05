<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<script>
	function boardDelChk(no){ //javascript는 데이터형을 정의할필요가없으니 var no안해도된다.
		if(confirm("삭제하시겠습니까?")){
			location.href="/temp/boardDelete?no="+no;
		}
	}
</script>
</head>
<body>
<h1>글내용보기</h1>
<ul>
	<li>번호 : ${vo.no}</li>
	<li>작성자 : ${vo.userid}</li>
	<li>등록일 : ${vo.writedate }</li>
	<li>조회수 : ${vo.hit}</li>
	<li>제목 : ${vo.subject}</li>
	<li> 글내용 : <br/>
		${vo.content }</li>
</ul>
	<a href="/temp/boardEdit?no=${vo.no }">수정</a>
	<a href="javascript:boardDelChk(${vo.no})">삭제</a>
</body>
</html>
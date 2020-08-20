<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../top.jsp"%>
<%String path = config.getServletContext().getRealPath("/images");%>
<body>
	<script type="text/javascript">
		function checkMovie() {
			if (f.title.value == "") {
				alert("제목을 입력해주세요")
				f.title.focus()
				return false;
			}
			if (f.director.value == "") {
				alert("감독을 입력해주세요")
				f.director.focus()
				return false;
			}
			if (f.genre.value == "") {
				alert("장르를 입력해주세요")
				f.genre.focus()
				return false;
			}
			if (f.actor.value == "") {
				alert("배우를 입력해주세요")
				f.actor.focus()
				return false;
			}
			if (f.story.value == "") {
				alert("줄거리를 입력해주세요")
				f.story.focus()
				return false;
			}
			if (f.grade.value == "") {
				alert("등급을 입력해주세요")
				f.grade.focus()
				return false;
			}
			if (f.time.value == "") {
				alert("상영시간을 입력해주세요")
				f.time.focus()
				return false;
			}
			if (f.opendate_year.value == "") {
				alert("개봉년도를 선택해주세요")
				f.opendate_year.focus()
				return false;
			}
			if (f.opendate_month.value == "") {
				alert("개봉월을 선택해주세요")
				f.opendate_month.focus()
				return false;
			}
			if (f.opendate_day.value == "") {
				alert("개봉일을 입력해주세요")
				f.opendate_day.focus()
				return false;
			}
			if (f.trailer.value == "") {
				alert("예고편을 입력해주세요")
				f.trailer.focus()
				return false;
			}
			if (f.state.value == "") {
				alert("상영형태를 입력해주세요")
				f.state.focus()
				return false;
			}
			return true;
		}
	</script>
	<div class="container">
		<div align="center">
			<form name="f" action="movie_updateP.mo"
				onsubmit="return checkMovie()" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="movie_num" value="${mvdto.movie_num}">
				<table border="1" class="table">
					<tr class="active">
						<th bgcolor="yellow" width="10%">영화제목</th>
						<td><input type="text" name="title" class="box" value="${mvdto.title}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">감독</th>
						<td><input type="text" name="director" class="box" value="${mvdto.director}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">장르</th>
						<td><input type="text" name="genre" class="box" value="${mvdto.genre}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우1</th>
						<td><input type="text" name="actor" class="box" value="${mvdto.actor}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우1사진</th>
						<td><input type="file" name="actorimage1" class="box"></td>
						<input type="hidden" name="bactorimage1" value="${mvdto.actorimage1}">
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우1 역할</th>
						<td><input type="text" name="actor1role" class="box" value="${mvdto.actor1role}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우1 전작</th>
						<td><input type="text" name="actor1past" class="box" value="${mvdto.actor1past}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우2</th>
						<td><input type="text" name="actor2" class="box" value="${mvdto.actor2}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우2사진</th>
						<td><input type="file" name="actorimage2" class="box" value="${mvdto.actorimage2}"></td>
						<input type="hidden" name="bactorimage2" value="${mvdto.actorimage2}">
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우2 역할</th>
						<td><input type="text" name="actor2role" class="box" value="${mvdto.actor2role}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우2 전작</th>
						<td><input type="text" name="actor2past" class="box" value="${mvdto.actor2past}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우3</th>
						<td><input type="text" name="actor3" class="box" value="${mvdto.actor3}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우3사진</th>
						<td><input type="file" name="actorimage3" class="box" value="${mvdto.actorimage3}"></td>
						<input type="hidden" name="bactorimage3" value="${mvdto.actorimage3}">
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우3 역할</th>
						<td><input type="text" name="actor3role" class="box" value="${mvdto.actor3role}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우3 전작</th>
						<td><input type="text" name="actor3past" class="box" value="${mvdto.actor3past}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우4</th>
						<td><input type="text" name="actor4" class="box" value="${mvdto.actor4}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우4사진</th>
						<td><input type="file" name="actorimage4" class="box" value="${mvdto.actorimage4}"></td>
						<input type="hidden" name="bactorimage4" value="${mvdto.actorimage4}">
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우4 역할</th>
						<td><input type="text" name="actor4role" class="box" value="${mvdto.actor4role}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우4 전작</th>
						<td><input type="text" name="actor4past" class="box" value="${mvdto.actor4past}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우5</th>
						<td><input type="text" name="actor5" class="box" value="${mvdto.actor5}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우5사진</th>
						<td><input type="file" name="actorimage5" class="box" value="${mvdto.actorimage5}"></td>
						<input type="hidden" name="bactorimage5" value="${mvdto.actorimage5}">
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우5 역할</th>
						<td><input type="text" name="actor5role" class="box" value="${mvdto.actor5role}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우5 전작</th>
						<td><input type="text" name="actor5past" class="box" value="${mvdto.actor5past}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우6</th>
						<td><input type="text" name="actor6" class="box" value="${mvdto.actor6}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우6사진</th>
						<td><input type="file" name="actorimage6" class="box" value="${mvdto.actorimage6}"></td>
						<input type="hidden" name="bactorimage6" value="${mvdto.actorimage6}">
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우6 역할</th>
						<td><input type="text" name="actor6role" class="box" value="${mvdto.actor6role}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">배우6 전작</th>
						<td><input type="text" name="actor6past" class="box" value="${mvdto.actor6past}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">줄거리</th>
						<td><textarea name="story" rows="10" cols="50">${mvdto.story}</textarea></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">등급</th>
						<td><input type="text" name="grade" class="box" value="${mvdto.grade}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">상영시간</th>
						<td><input type="text" name="time" class="box" value="${mvdto.time}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">개봉일</th>
						<td><select name="opendate_year">
								<c:forEach var="i" begin="1950" end="2100" step="1">
									<option>${i}</option>
								</c:forEach>
						</select> 년 <select name="opendate_month">
								<c:forEach var="j" begin="1" end="12" step="1">
									<option>${j}</option>
								</c:forEach>
						</select> 월 <select name="opendate_day">
								<c:forEach var="k" begin="1" end="31" step="1">
									<option>${k}</option>
								</c:forEach>
						</select> 일</td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">예고편</th>
						<td><input type="text" name="trailer" value="${mvdto.trailer}"></td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">상영형태</th>
						<td>
							<input type="checkbox" name="2d" value="2D">2D &nbsp;
                    		<input type="checkbox" name="3d" value="3D">3D &nbsp;
                     		<input type="checkbox" name="4dx" value="4DX">4DX &nbsp;
                     		<input type="checkbox" name="imax" value="IMAX">IMAX
						</td>
					</tr>
					<tr class="active">
						<th bgcolor="yellow">이미지</th>
						<td>
						<c:set var="filename" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.image}"/>
						<img src="${filename}" border="0" width="50" height="50">
						<input type="hidden" name="image2" value="${mvdto.image}">
						<input type="file" name="image" class="box" ></td>
					</tr>
					<tr bgcolor="black">
						<td colspan="2" align="center"><input type="submit"
							value="등록"> <input type="reset" value="취소"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="../bottom.jsp"%>
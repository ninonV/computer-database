<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="ListComputer"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${totalComputers}" />
				Computers found
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" value="<c:out value="${param.search}"/>"
							placeholder="Search name" /> <input type="submit"
							id="searchsubmit" value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="AddComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name <a
							href="ListComputer?linesNb=${linesNb}&order=computer.name ASC&search=${search}"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
							href="ListComputer?linesNb=${linesNb}&order=computer.name DESC&search=${search}"><i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</th>
						<th>Introduced date <a
							href="ListComputer?linesNb=${linesNb}&order=introduced ASC&search=${search}"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
							href="ListComputer?linesNb=${linesNb}&order=introduced DESC&search=${search}"><i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date <a
							href="ListComputer?linesNb=${linesNb}&order=discontinued ASC&search=${search}"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
							href="ListComputer?linesNb=${linesNb}&order=discontinued DESC&search=${search}"><i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</th>
						<!-- Table header for Company -->
						<th>Company <a
							href="ListComputer?linesNb=${linesNb}&order=company.name ASC&search=${search}"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
							href="ListComputer?linesNb=${linesNb}&order=company.name DESC&search=${search}"><i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${listComputers}" var="computer"
						varStatus="status">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="EditComputer?computerId=${computer.id}"
								onclick=""><c:out value="${computer.name}" /></a></td>
							<td><c:out value="${computer.introduced}" /></td>
							<td><c:out value="${computer.discontinued}" /></td>
							<td><c:out value="${computer.company.name}" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">

				<%--For displaying Previous link except for the 1st page --%>
				<li><c:if test="${currentPage != 1}">
						<button
							onclick="window.location.href='ListComputer?linesNb=${linesNb}&pageNb=${currentPage - 1}&search=${search}&order=${order}'"
							aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</button>
					</c:if> <c:forEach begin="0" end="10" var="i">
						<c:if test="${currentPage+i<=totalPages}">
							<c:choose>
								<c:when test="${i==0}">
									<button type="button" class="btn active"
										onclick="window.location.href='ListComputer?linesNb=${linesNb}&pageNb=${currentPage}&search=${search}&order=${order}'">
										<c:out value="${currentPage}"></c:out>
									</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-default"
										onclick="window.location.href='ListComputer?linesNb=${linesNb}&pageNb=${currentPage+i}&search=${search}&order=${order}'">
										<c:out value="${currentPage+i}"></c:out>
									</button>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach> <%--For displaying Next link except for the last page --%> <c:if
						test="${currentPage != totalPages}">
						<button
							onclick="window.location.href='ListComputer?linesNb=${linesNb}&pageNb=${currentPage + 1}&search=${search}&order=${order}'"
							aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</button>
					</c:if></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<c:choose>
					<c:when test="${ linesNb eq 10 }">
						<button type="button" class="btn active"
							onclick="window.location.href='ListComputer?linesNb=10&search=${search}&order=${order}'">10</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-default"
							onclick="window.location.href='ListComputer?linesNb=10&search=${search}&order=${order}'">10</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ linesNb eq 50 }">
						<button type="button" class="btn active"
							onclick="window.location.href='ListComputer?linesNb=50&search=${search}&order=${order}'">50</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-default"
							onclick="window.location.href='ListComputer?linesNb=50&search=${search}&order=${order}'">50</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ linesNb eq 100 }">
						<button type="button" class="btn active"
							onclick="window.location.href='ListComputer?linesNb=100&search=${search}&order=${order}'">100</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-default"
							onclick="window.location.href='ListComputer?linesNb=100&search=${search}&order=${order}'">100</button>
					</c:otherwise>
				</c:choose>
			</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>
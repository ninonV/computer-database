<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="ListComputer"> Application - Computer Database </a>
		</div>
		<div class="container">
			<a id="en" href="RegisterUser?lang=en"><spring:message code="lang.en" /></a> | 
			<a id="fr" href="RegisterUser?lang=fr"><spring:message code="lang.fr" /></a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="label.newUser" />
					</h1>
					<form action="RegisterUser" method="POST" onsubmit="">
						<fieldset>
							<div class="form-group">
								<label for="username"><spring:message
										code="label.username" /></label> <input type="text"
									class="form-control" id="username" name="username"
									placeholder="Username">
							</div>
							<div class="form-group">
								<label for="password"><spring:message
										code="label.password" /></label> <input type="text"
									class="form-control" id="password" name="password"
									placeholder="Password">
							</div>

						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<spring:message code="button.add" />
								class="btn btn-primary"> <a href="ListComputer"
								class="btn btn-default"><spring:message code="button.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
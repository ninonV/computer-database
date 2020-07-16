<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="AddComputer" method="POST" onsubmit="return validateForm()">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" value="<c:out value="${param.computerName}"/>" placeholder="Computer name">
                                <p class="error">${errors['computerName']}</p>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced" value="<c:out value="${param.introduced}"/>" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" value="<c:out value="${param.discontinued}"/>" placeholder="Discontinued date">
                                <p class="error">${errors['discontinued']}</p>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                                    <option value="0" selected="selected">--</option>
                                     	<c:forEach items="${listCompanies}" var="company" varStatus="status">
                                			<option value="${company.companyId}"><c:out value="${company.companyName}"/></option>
                                		</c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="ListComputer" class="btn btn-default">Cancel</a>
                        </div>
                        <p class="${empty errors ? 'success' : 'error'}">${resultCreation}</p>
                    </form>
                </div>
            </div>
        </div>
    </section>
<script src="js/validatorForm.js"></script>
</body>
</html>
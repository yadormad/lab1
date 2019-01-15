<%--
  Created by IntelliJ IDEA.
  User: yador
  Date: 14.01.2019
  Time: 5:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="clientsBean" class="com.yador.lab1.controller.ClientController">
    <jsp:setProperty name="clientsBean" property="*"/>
</jsp:useBean>
${clientsBean.init()}

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/custom-style.css">
    <title>Petrovich Pit Stop</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Petrovich Pit Stop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="index.jsp">Clients<span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="#">Machinists</a>
            <a class="nav-item nav-link" href="#">Orders</a>
        </div>
    </div>
</nav>

<c:set var="clientEditable" value="${clientsBean.buildClient(
            param.id,
            param.firstName,
            param.lastName,
            param.fatherName,
            param.phoneNumber)}"/>

<c:choose>
    <c:when test="${param.action.equals('add')}">
        ${clientsBean.addClient(clientEditable)}
    </c:when>
    <c:when test="${param.action.equals('edit')}">
        ${clientsBean.editClient(clientEditable)}
    </c:when>
    <c:when test="${param.action.equals('delete')}">
        ${clientsBean.deleteClient(clientEditable.id)}
    </c:when>
</c:choose>


<!-- Edit Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalCenterTitle">Edit client</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!--/*@thymesVar id="client" type="com.mag.lab2.model.dto.Client"*/-->
            <form method="post" action="index.jsp">
                <div class="modal-body">
                    <input type="number" hidden id="editId" name="id" >
                    <div class="form-group">
                        <label for="firstNameEdit">First Name</label>
                        <input type="text" class="form-control mandatory" id="firstNameEdit" name="firstName" placeholder="First Name">
                    </div>
                    <div class="form-group">
                        <label for="lastNameEdit">Last Name</label>
                        <input type="text" class="form-control mandatory" id="lastNameEdit" name="lastName" placeholder="Last Name">
                    </div>
                    <div class="form-group">
                        <label for="fatherNameEdit">Father Name</label>
                        <input type="text" class="form-control" id="fatherNameEdit" name="fatherName" placeholder="Father Name">
                    </div>
                    <div class="form-group">
                        <label for="phoneNameEdit">Phone</label>
                        <input type="text" class="form-control" id="phoneNameEdit" name="phoneName" placeholder="Phone">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="editSave" name="action" value="edit">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Add Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalCenterTitle">Add client</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!--/*@thymesVar id="client" type="com.mag.lab2.model.dto.Client"*/-->
            <form  method="post" action="index.jsp">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="firstNameAdd">First Name</label>
                        <input type="text" class="form-control mandatory" id="firstNameAdd" name="firstName" placeholder="First Name">
                    </div>
                    <div class="form-group">
                        <label for="lastNameAdd">Last Name</label>
                        <input type="text" class="form-control mandatory" id="lastNameAdd" name="lastName" placeholder="Last Name">
                    </div>
                    <div class="form-group">
                        <label for="fatherNameAdd">Father Name</label>
                        <input type="text" class="form-control" id="fatherNameAdd" name="fatherName" placeholder="Father Name">
                    </div>
                    <div class="form-group">
                        <label for="phoneNameAdd">Phone</label>
                        <input type="text" class="form-control" id="phoneNameAdd" name="phoneName" placeholder="Phone">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="addSave" name="action" value="add">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container mt-3">
    <div class="row mt-3">
        <div class="col-3 align-self-end">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal">Add</button>
                <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#editModal" onclick="setClientFields()" id="editBtn">Edit</button>
                <button type="submit" class="btn btn-danger" id="deleteBtn" onclick="submitDelete()">Delete</button>
            </div>
        </div>
    </div>
    <form hidden action="index.jsp" method="post">
        <input type="number" hidden id="deleteId" name="id">
        <button type="submit" id="deleteFormBtn" name="action" value="delete">D</button>
    </form>
    <table class="table table-hover mt-3" id="editClientTable">
        <thead>
        <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Father Name</th>
            <th scope="col">Phone Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="client" items="${clientsBean.all}">
            <tr class="clickable-row" id="${client.id}">
                <td>${client.firstName}</td>
                <td>${client.lastName}</td>
                <td>${client.fatherName}</td>
                <td>${client.phoneNumber}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="scripts/custom.js"></script>
<script src="scripts/custom-client.js"></script>
</body>
</html>


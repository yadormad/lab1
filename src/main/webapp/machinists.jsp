
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="machinistsBean" class="com.yador.lab1.controller.MachinistController">
    <jsp:setProperty name="machinistsBean" property="*"/>
</jsp:useBean>

<c:set var="machinistEditable" value="${machinistsBean.buildMachinist(param.id,
            param.firstName,
            param.lastName,
            param.fatherName,
            param.valueCost)}"/>

<c:choose>
    <c:when test="${param.action.equals('add')}">
        ${machinistsBean.addMachinist(machinistEditable)}
    </c:when>
    <c:when test="${param.action.equals('edit')}">
        ${machinistsBean.editMachinist(machinistEditable)}
    </c:when>
    <c:when test="${param.action.equals('delete')}">
        ${machinistsBean.deleteMachinist(machinistEditable.id)}
    </c:when>
</c:choose>

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
            <a class="nav-item nav-link" href="index.jsp">Clients</a>
            <a class="nav-item nav-link active" href="machinists.jsp">Machinists <span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="orders.jsp">Orders</a>
        </div>
    </div>
</nav>


<!-- Edit Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalCenterTitle">Edit machinist</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!--/*@thymesVar id="machinist" type="com.mag.lab2.model.dto.Machinist"*/-->
            <form method="post" action="machinists.jsp">
                <div class="modal-body">
                    <input type="number" hidden id="editId" name="id">
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
                        <label for="valueCostEdit">Value Cost</label>
                        <input type="number" class="form-control mandatory" id="valueCostEdit" name="valueCost" placeholder="Value Cost">
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
                <h5 class="modal-title" id="addModalCenterTitle">Add machinist</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!--/*@thymesVar id="machinist" type="com.mag.lab2.model.dto.Machinist"*/-->
            <form method="post" action="machinists.jsp">
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
                        <label for="valueCostAdd">Value Cost</label>
                        <input type="text" class="form-control mandatory" id="valueCostAdd" name="valueCost" placeholder="Value Cost">
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
    <!--/*@thymesVar id="machinist" type="com.mag.lab2.model.dto.Machinist"*/-->
    <form hidden action="machinists.jsp" method="post">
        <input type="number" hidden id="deleteId" name="id" >
        <button type="submit" id="deleteFormBtn" name="action" value="delete">D</button>
    </form>
    <table class="table table-hover mt-3" id="editMachinistTable">
        <thead>
        <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Father Name</th>
            <th scope="col">Value Cost</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="machinist" items="${machinistsBean.all}">
            <tr class="clickable-row" id="${machinist.id}">
                <td>${machinist.firstName}</td>
                <td>${machinist.lastName}</td>
                <td>${machinist.fatherName}</td>
                <td>${machinist.valueCost}</td>
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
<script src="scripts/custom-machinist.js"></script>
</body>
</html>

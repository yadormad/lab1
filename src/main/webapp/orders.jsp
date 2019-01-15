
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orderBean" class="com.yador.lab1.controller.OrderController">
    <jsp:setProperty name="orderBean" property="*"/>
</jsp:useBean>

<jsp:useBean id="clientBean" class="com.yador.lab1.controller.ClientController">
    <jsp:setProperty name="clientBean" property="*"/>
</jsp:useBean>

<jsp:useBean id="machinistBean" class="com.yador.lab1.controller.MachinistController">
    <jsp:setProperty name="machinistBean" property="*"/>
</jsp:useBean>


<c:set var="orderEditable" value="${orderBean.createOrder(param.id,
            param.description,
            param.clientId,
            param.machinistId,
            param.startDate,
            param.endDate,
            param.cost,
            param.statusId)}"/>

<c:choose>
    <c:when test="${param.action.equals('add')}">
        ${orderBean.addOrder(orderEditable)}
    </c:when>
    <c:when test="${param.action.equals('edit')}">
        ${orderBean.editOrder(orderEditable)}
    </c:when>
    <c:when test="${param.action.equals('delete')}">
        ${orderBean.deleteOrder(orderEditable.id)}
    </c:when>
</c:choose>

<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" th:href="@{/custom-style.css}">
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
            <a class="nav-item nav-link" href="clients">Clients</a>
            <a class="nav-item nav-link" href="machinists">Machinists</a>
            <a class="nav-item nav-link active" href="orders">Orders<span class="sr-only">(current)</span></a>
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
            <form method="post" action="orders">
                <div class="modal-body">
                    <input type="number" hidden id="editId" name="id">
                    <div class="form-group">
                        <label for="descriptionEdit">Description</label>
                        <textarea class="form-control" id="descriptionEdit" name="description" rows="3"></textarea>
                        <!--<input type="text" class="form-control" id="firstNameEdit" placeholder="First Name" th:field="*{description}">-->
                    </div>
                    <div class="form-group">
                        <label for="clientEditSelect">Client</label>
                        <select class="form-control mandatory" id="clientEditSelect">
                            <c:forEach var="client" items="${clientBean.all}">
                                <option id="${'editClient_' + client.id}">${client.firstName} ${client.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="machinistEditSelect">Machinist</label>
                        <select class="form-control mandatory" id="machinistEditSelect">
                            <c:forEach var="machinist" items="${machinistBean.all}">
                                <option id="${'editMachinist_' + machinist.id}">${machinist.firstName} + ' ' + ${machinist.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="startDateEdit">Start date</label>
                        <input type="date" class="form-control mandatory" id="startDateEdit" name="startDate" placeholder="Start date">
                    </div>
                    <div class="form-group">
                        <label for="endDateEdit">End date</label>
                        <input type="date" class="form-control mandatory" id="endDateEdit" name="endDate" placeholder="End date">
                    </div>
                    <div class="form-group">
                        <label for="valueCostEdit">Cost</label>
                        <input type="number" class="form-control mandatory" id="valueCostEdit" name="valueCost" placeholder="Value Cost">
                    </div>
                    <div class="form-group">
                        <label for="statusEditSelect">Status</label>
                        <select class="form-control mandatory" id="statusEditSelect">
                            <c:forEach var="status" items="${orderBean.allOrderStatuses}">
                                <option id="${'editStatus_' + status.id}" >${status.status}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div hidden>
                        <input type="number" id="editClientHiddenId" name="clientId">
                        <input type="number" id="editMachinistHiddenId" name="machinistId">
                        <input type="number" id="editStatusHiddenId" name="statusId">
                    </div>
                    <div id="editDateError" class="alert alert-danger">
                        Start date must be before End date
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
                <h5 class="modal-title" id="addModalCenterTitle">Add order</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="orders">
                <div class="modal-body">
                    <input type="number" hidden id="addId" name="id">
                    <div class="form-group">
                        <label for="descriptionAdd">Description</label>
                        <textarea class="form-control" id="descriptionAdd" name="description" rows="3"></textarea>
                        <!--<input type="text" class="form-control" id="firstNameAdd" placeholder="First Name" th:field="*{description}">-->
                    </div>
                    <div class="form-group">
                        <label for="clientAddSelect">Client</label>
                        <select class="form-control mandatory" id="clientAddSelect">
                            <c:forEach var="client" items="${clientBean.all}">
                                <option id="${'addClient_' + client.id}">${client.firstName} ${client.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="machinistAddSelect">Machinist</label>
                        <select class="form-control mandatory" id="machinistAddSelect">
                            <c:forEach var="machinist" items="${machinistBean.all}">
                                <option id="${'addMachinist_' + machinist.id}">${machinist.firstName} + ' ' + ${machinist.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="startDateAdd">Start date</label>
                        <input type="date" class="form-control mandatory" id="startDateAdd" name="startDate" placeholder="Start date">
                    </div>
                    <div class="form-group">
                        <label for="endDateAdd">End date</label>
                        <input type="date" class="form-control mandatory" id="endDateAdd" name="endDate" placeholder="End date">
                    </div>
                    <div class="form-group">
                        <label for="valueCostAdd">Cost</label>
                        <input type="number" class="form-control mandatory" id="valueCostAdd" name="valueCost" placeholder="Value Cost">
                    </div>
                    <div class="form-group">
                        <label for="statusAddSelect">Status</label>
                        <select class="form-control mandatory" id="statusAddSelect">
                            <c:forEach var="status" items="${orderBean.allOrderStatuses}">
                                <option id="${'addStatus_' + status.id}" >${status.status}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div hidden>
                        <input type="number" id="addClientHiddenId" name="clientId">
                        <input type="number" id="addMachinistHiddenId" name="machinistId">
                        <input type="number" id="addStatusHiddenId" name="statusId">
                    </div>
                    <div id="addDateError" class="alert alert-danger">
                        Start date must be before End date
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
    <form hidden action="orders" method="post">
        <input type="number" hidden id="deleteId" name="id">
        <button type="submit" id="deleteFormBtn" name="action" value="delete">D</button>
    </form>
    <table class="table table-hover mt-3" id="editOrderTable">
        <thead>
        <tr>
            <th scope="col">Description</th>
            <th scope="col">Client</th>
            <th scope="col">Machinist</th>
            <th scope="col">Start Date</th>
            <th scope="col">End Date</th>
            <th scope="col">Cost</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orderBean.all}">
            <tr class="clickable-row" id="${order.id}">
                <c:set var="client" value="${clientBean.getById(order.client)}"/>
                <c:set var="machinist" value="${machinistBean.getById(order.machinist)}"/>
                <td>${order.description}</td>
                <td id="${'client_' + client.id}">${client.firstName} ${client.lastName}</td>
                <td id="${'machinist_' + machinist.id}">${machinist.firstName} ${machinist.lastName}</td>
                <td>${order.startDate}</td>
                <td>${order.endDate}</td>
                <td>${order.cost}</td>
                <td id="${'status_' + orderBean.getOrderStatus(order).id}">${orderBean.getOrderStatus(order).status}</td>
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

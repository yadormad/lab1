
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


<c:set var="orderEditable" value="${orderBean.buildOrder()}"/>

<c:choose>
    <c:when test="${param.action.equals('add')}">
        ${orderBean.addOrder(orderBean)}
    </c:when>
    <c:when test="${param.action.equals('edit')}">
        ${orderBean.editOrder(orderBean)}
    </c:when>
    <c:when test="${param.action.equals('delete')}">
        ${orderBean.deleteOrder(orderBean.getId())}
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
            <a class="nav-item nav-link" href="index.jsp">Clients</a>
            <a class="nav-item nav-link" href="machinists.jsp">Machinists</a>
            <a class="nav-item nav-link active" href="orders.jsp">Orders<span class="sr-only">(current)</span></a>
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
            <form method="post" action="orders.jsp">
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
                            <option th:each="machinist : ${machinistList}" th:id="${'editMachinist_' + machinist.id}" th:text="${machinist.firstName} + ' ' + ${machinist.lastName}"></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="startDateEdit">Start date</label>
                        <input type="date" class="form-control mandatory" id="startDateEdit" placeholder="Start date" th:field="*{startDate}">
                    </div>
                    <div class="form-group">
                        <label for="endDateEdit">End date</label>
                        <input type="date" class="form-control mandatory" id="endDateEdit" placeholder="End date" th:field="*{endDate}">
                    </div>
                    <div class="form-group">
                        <label for="valueCostEdit">Cost</label>
                        <input type="number" class="form-control mandatory" id="valueCostEdit" placeholder="Value Cost" th:field="*{cost}">
                    </div>
                    <div class="form-group">
                        <label for="statusEditSelect">Status</label>
                        <select class="form-control mandatory" id="statusEditSelect">
                            <option th:each="status : ${statusList}" th:id="${'editStatus_' + status.id}" th:text="${status.status}"></option>
                        </select>
                    </div>
                    <div hidden>
                        <input type="number" th:id="editClientHiddenId" th:field="*{client.id}">
                        <input type="number" th:id="editMachinistHiddenId" th:field="*{machinist.id}">
                        <input type="number" th:id="editStatusHiddenId" th:field="*{status.id}">
                    </div>
                    <div id="editDateError" class="alert alert-danger">
                        Start date must be before End date
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="editSave">Save changes</button>
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
            <form action="#" method="post" th:action="@{/orders/add}" th:object="${order}">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="descriptionAdd">Description</label>
                        <textarea class="form-control" id="descriptionAdd" rows="3" th:field="*{description}"></textarea>
                        <!--<input type="text" class="form-control" id="firstNameAdd" placeholder="First Name" th:field="*{description}">-->
                    </div>
                    <div class="form-group">
                        <label for="clientAddSelect">Client</label>
                        <select class="form-control mandatory" id="clientAddSelect">
                            <option selected disabled></option>
                            <option th:each="client : ${clientsList}" th:id="${'addClient_' + client.id}" th:text="${client.firstName} +' ' + ${client.lastName}"></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="machinistAddSelect">Machinist</label>
                        <select class="form-control mandatory" id="machinistAddSelect">
                            <option selected disabled></option>
                            <option th:each="machinist : ${machinistList}" th:id="${'addMachinist_' + machinist.id}" th:text="${machinist.firstName} + ' ' + ${machinist.lastName}"></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="startDateAdd">Start date</label>
                        <input type="date" class="form-control mandatory" id="startDateAdd" placeholder="Start date" th:field="*{startDate}">
                    </div>
                    <div class="form-group">
                        <label for="endDateAdd">End date</label>
                        <input type="date" class="form-control mandatory" id="endDateAdd" placeholder="End date" th:field="*{endDate}">
                    </div>
                    <div class="form-group">
                        <label for="valueCostAdd">Cost</label>
                        <input type="number" class="form-control mandatory" id="valueCostAdd" placeholder="Value Cost" th:field="*{cost}">
                    </div>
                    <div class="form-group">
                        <label for="statusAddSelect">Status</label>
                        <select class="form-control mandatory" id="statusAddSelect">
                            <option selected disabled></option>
                            <option th:each="status : ${statusList}" th:id="${'addStatus_' + status.id}" th:text="${status.status}"></option>
                        </select>
                    </div>
                    <div hidden>
                        <input type="number" th:id="addClientHiddenId" th:field="*{client.id}">
                        <input type="number" th:id="addMachinistHiddenId" th:field="*{machinist.id}">
                        <input type="number" th:id="addStatusHiddenId" th:field="*{status.id}">
                    </div>
                    <div id="addDateError" class="alert alert-danger">
                        Start date must be before End date
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="addSave">Save changes</button>
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
    <form hidden th:action="@{/orders/delete}" th:object="${order}" method="post">
        <input type="number" hidden id="deleteId" th:field="*{id}">
        <button type="submit" id="deleteFormBtn">D</button>
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
        <tr class="clickable-row" th:each="order : ${ordersList}" th:id="${order.id}">
            <td th:text="${order.description}"></td>
            <td th:text="${order.client.firstName} + ' ' + ${order.client.lastName}" th:id="${'client_' + order.client.id}"></td>
            <td th:text="${order.machinist.firstName} + ' ' + ${order.machinist.lastName}"  th:id="${'machinist_' + order.machinist.id}"></td>
            <td th:text="${order.startDate}"></td>
            <td th:text="${order.endDate}"></td>
            <td th:text="${order.cost}"></td>
            <td th:text="${order.status.status}" th:id="${'status_' + order.status.id}"></td>
        </tr>
        </tbody>
    </table>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script th:src="@{/custom.js}"></script>
<script th:src="@{/custom-order.js}"></script>
</body>
</html>

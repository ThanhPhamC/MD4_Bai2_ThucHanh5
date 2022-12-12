<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: phamthanh
  Date: 09/12/2022
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<style>
    .pagination {
        display: inline-block;
        margin-left: 75%;
    }

    .pagination a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
    }

    .pagination a.active {
        background-color: #0d70f1;
        color: white;
        border-radius: 5px;
    }

    .pagination a:hover:not(.active) {
        background-color: #ddd;
        border-radius: 5px;
    }
</style>
<html>
<head>
    <title>Students</title>
</head>
<body>
<div class="card-header">
    <h3 class="card-title">Student List</h3>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#newStudent"> Add new Student
                </button>
            </a>
            <form class="d-flex" role="search">
                <input class="form-control me-2 fst-italic" type="search" oninput="searchByName(this)"
                       placeholder="Enter student name... " name="search"
                       aria-label="Search">
                <button class="btn btn-outline-success" type="submit">
                    Search
                </button>
            </form>
        </div>
    </nav>
</div>
<table id="example1"
       class="table table-bordered table-striped border-primary table-hover table-striped text-center">
    <thead style=" background-color: #e8bc06; vertical-align: middle;">
    <tr>
        <th>Id</th>
        <th>Full Name</th>
        <th>Brith Date</th>
        <th>Email</th>
        <th>Address</th>
        <th>Status</th>
        <th colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody id="content">
    <c:forEach items="${listStudent}" var="st">
        <tr style="color: black">
            <td>${st.studentId}</td>
            <td>${st.studentName}</td>
            <td><fmt:formatDate value="${st.birthDate}" pattern="dd/MM/yyyy"/></td>
            <td>${st.email}</td>
            <td>${st.address}</td>
            <td>${st.studentStatus? 'Hoạt Động':'Nghỉ học'}</td>
            <td>
                <button type="button" id="update" class="btn btn-outline-primary"
                        data-bs-toggle="modal" data-bs-target="#updateStudent"
                ><i class="bi bi-pencil-square"></i>
                </button>
                <input type="hidden" id="oldId" value="${st.studentId}">
                <input type="hidden" id="oldName" value="${st.studentName}">
                <input type="hidden" id="oldDate" value="${st.birthDate}">
                <input type="hidden" id="oldEmail" value="${st.email}">
                <input type="hidden" id="oldAddress" value="${st.address}">
                <input type="hidden" id="oldStatus" value="${st.studentStatus}">
            </td>
            <td>
                <button type="button" id="delete" class="btn btn-outline-danger"
                        data-bs-toggle="modal" data-bs-target="#deleteStudent"
                ><i class="bi bi-journal-x"></i>
                </button>
                <input type="hidden" id="deleteId" value="${st.studentId}">
                <input type="hidden" id="deleteName" value="${st.studentName}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">
    <a href="#">&laquo;</a>
    <c:forEach begin="1" var="i" end="${index}">
    <a href="<%=request.getContextPath()%>/HomeController/getListByIndex?currentIndex=${i}" class="${currentIndex==i?"active":""}" onclick="showListByIndex()" >${i}</a>
        <input type="hidden" value="${i}" id="currentIndex">
    </c:forEach>

    <a href="#">&raquo;</a>
</div>
<!-- Modal creat new student -->
<div class="modal fade" id="newStudent" tabindex="-1" aria-labelledby="newCatalogLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="<%=request.getContextPath()%>/HomeController/creatStudent" method="post">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #e8bc06; border: 1px">
                    <h5 class="modal-title" id="newCatalogLabel">Creat New</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="input-group mb-3">
                        <span class="btn-primary input-group-text" id="newCatalogName">Full Name</span>
                        <input type="text" name="studentName" class="form-control"
                               placeholder="Enter student name"
                               aria-label="Sizing example input"
                               aria-describedby="inputCatalogName">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="btn-primary input-group-text"
                                                  id="newBirthDate">Birth Date </span>
                        <input type="date" name="birthDate" class="form-control"
                               placeholder="enter birth date "
                               aria-label="Sizing example input"
                               aria-describedby="inputDescriptions">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="input-group-text btn-primary"
                                                  id="newEmail">Email &nbsp &nbsp &nbsp &nbsp </span>
                        <input type="text" name="email" class="form-control"
                               placeholder="Enter email "
                               aria-label="Sizing example input"
                               aria-describedby="inputDescriptions">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="input-group-text btn-primary"
                                                  id="newAddress">Address &nbsp&nbsp </span>
                        <input type="text" name="address" class="form-control"
                               placeholder="Enter address "
                               aria-label="Sizing example input"
                               aria-describedby="inputDescriptions">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                    </button>
                    <input type="submit" class="btn btn-primary"/>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- Modal update Student -->
<div class="modal fade" id="updateStudent" tabindex="-1" aria-labelledby="updateStudentLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="<%=request.getContextPath()%>/HomeController/updateStudent" method="post">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #e8bc06">
                    <h5 class="modal-title" id="updateStudentLabel">Creat New</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="input-group mb-3">
                        <span class="input-group-text btn-primary" id="updateStudentId">Student Id</span>
                        <input type="text" name="studentId" readonly id="studentId" class="form-control"
                               placeholder="Enter student name"
                               aria-label="Sizing example input"
                               aria-describedby="inputCatalogName">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text btn-primary" id="updateStudentName">Full Name&nbsp</span>
                        <input type="text" name="studentName" id="studentName" class="form-control"
                               placeholder="Enter student name"
                               aria-label="Sizing example input"
                               aria-describedby="inputCatalogName">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="input-group-text btn-primary"
                                                  id="updateBirthDate">Birth Date&nbsp </span>
                        <input type="date" name="birthDate" id="birthDate" class="form-control"
                               placeholder="enter birth date "
                               aria-label="Sizing example input"
                               aria-describedby="inputDescriptions">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="input-group-text btn-primary"
                                                  id="updateEmail">Email &nbsp &nbsp &nbsp &nbsp&nbsp </span>
                        <input type="text" name="email" id="email" class="form-control"
                               placeholder="Enter email "
                               aria-label="Sizing example input"
                               aria-describedby="inputDescriptions">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="input-group-text btn-primary"
                                                  id="updateAddress">Address &nbsp&nbsp&nbsp</span>
                        <input type="text" name="address" id="address" class="form-control"
                               placeholder="Enter address "
                               aria-label="Sizing example input"
                               aria-describedby="inputDescriptions">
                    </div>
                    <div class="input-group mb-3">
                                            <span class="input-group-text btn-primary"
                                                  id="updateStatus">Status &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  </span>
                        <select name="studentStatus" id="studentStatus" class="form-control">
                            <option value="true">Hoạt Động</option>
                            <option value="false">Nghỉ học</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                    </button>
                    <input type="submit" class="btn btn-primary"/>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- Modal delete  student -->
<div class="modal fade" id="deleteStudent" tabindex="-1" aria-labelledby="deleteStudentLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="<%=request.getContextPath()%>/HomeController/deleteStudent" method="post">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #e8bc06; border: 1px">
                    <h5 class="modal-title" id="deleteStudentLabel">Delete Student</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group mb-3">
                        <input type="hidden" name="studentId" id="idDelete">
                        <input type="text" name="studentName" id="deleteStName" class="form-control"
                               placeholder="Enter student name"
                               aria-label="Sizing example input"
                               aria-describedby="inputCatalogName" readonly>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                    </button>
                    <input type="submit" class="btn btn-primary"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
    $(document).on('click', 'table #update', function () {
        $('#studentId').val($(this).parent().find('#oldId').val());
        $('#studentName').val($(this).parent().find('#oldName').val());
        $('#birthDate').val($(this).parent().find('#oldDate').val());
        $('#email').val($(this).parent().find('#oldEmail').val());
        $('#address').val($(this).parent().find('#oldAddress').val());
        $('#studentStatus').val($(this).parent().find('#oldStatus').val());
    });
    $(document).on('click', 'table #delete', function () {
        $('#idDelete').val($(this).parent().find('#deleteId').val());
        $('#deleteStName').val("Xác nhận xoá " + $(this).parent().find('#deleteName').val());
    });
    function showListByIndex() {
        var index=$(this).parent().find('#currentIndex').val();

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/HomeController/listStudentByIndex?index=' + index,
            success: function (dataSearch) {
                var row = document.getElementById("content");
                row.innerHTML = dataSearch;
            }
        });
    };

    function searchByName(data) {
        var searchName = data.value;

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/HomeController/SearchByName?studentName=' + searchName,
            success: function (dataSearch) {
                var row = document.getElementById("content");
                row.innerHTML = dataSearch;
            }
        });
    }
</script>
</body>
</html>

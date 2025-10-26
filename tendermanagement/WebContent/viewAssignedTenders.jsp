<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,com.hit.beans.TenderStatusBean,com.hit.utility.DBUtil,java.util.List,com.hit.dao.TenderDaoImpl,com.hit.dao.TenderDao" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="shortcut icon" type="image/png" href="images/Banner_Hit.png">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Vaasan Tender</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/annimate.css">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style2.css">

<!-- 🧩 All styling inside this file -->
<style>
/* Container layout */
body {
    background-color: #ffe6cc;
    font-family: Arial, sans-serif;
}

/* Headings and table labels */
#show {
    min-width: 850px;
    background-color: white;
    color: red;
    text-align: center;
    font-size: 20px;
    padding: 10px;
    border: 2px solid #000;
    border-radius: 10px;
    margin-top: 10px;
}

/* Table wrapper for responsiveness */
.table-container {
    overflow-x: auto;
    width: 100%;
    margin-top: 20px;
}

/* Table styling */
table {
    border-collapse: collapse;
    width: 95%;
    margin: auto;
    background-color: white;
    text-align: center;
    border-radius: 10px;
    box-shadow: 0 0 8px rgba(0,0,0,0.1);
    font-size: 15.5px;
}

/* Table header */
th, td {
    padding: 10px 15px;
    border: 2px solid #000;
    word-wrap: break-word;
}

/* Header row */
tr:first-child {
    background-color: brown;
    color: white;
    font-weight: bold;
    font-size: 18px;
}

/* Hover effect */
tr:hover {
    background-color: #DEBEE1;
    color: black;
}

/* Links inside the table */
table a {
    color: blue;
    text-decoration: none;
}
table a:hover {
    text-decoration: underline;
    color: darkred;
}

/* No data row */
.no-data {
    color: red;
    text-align: center;
    font-weight: bold;
    background-color: #fff3e6;
}

/* Buttons hover (if any in future) */
button:hover {
    background-color: red;
    color: white;
}
</style>
</head>
<body>
<%
String user = (String) session.getAttribute("user");
String uname = (String) session.getAttribute("username");
String pword = (String) session.getAttribute("password");
if (user == null || !user.equalsIgnoreCase("admin") || uname.equals("") || pword.equals("")) {
    response.sendRedirect("loginFailed.jsp");
}
%>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="adminMenu.jsp"></jsp:include>
<jsp:include page="marquee.jsp"></jsp:include>

<div class="container-fluid">
    <div class="col-md-3" style="margin-left: 2%">
        <jsp:include page="notice.jsp"></jsp:include><br>
    </div>

    <div class="col-md-8">
        <div id="show">All Assigned Tenders List</div>

        <!-- 🧩 Responsive table container -->
        <div class="table-container">
            <table>
                <tr>
                    <th>Tender Id</th>
                    <th>Tender Name</th>
                    <th>Vendor Id</th>
                    <th>Vendor Name</th>
                    <th>Application Id</th>
                    <th>Status</th>
                </tr>

                <%
                TenderDao dao = new TenderDaoImpl();
                List<TenderStatusBean> statusList = dao.getAllAssignedTenders();
                if (statusList.isEmpty()) {
                %>
                    <tr><td colspan="6" class="no-data">No Tenders Assigned Yet</td></tr>
                <%
                } else {
                    for (TenderStatusBean status : statusList) {
                %>
                <tr>
                    <td><a href="viewTenderBidsForm.jsp?tid=<%=status.getTendorId()%>"><%=status.getTendorId()%></a></td>
                    <td><%=status.getTenderName()%></td>
                    <td><a href="adminViewVendorDetail.jsp?vid=<%=status.getVendorId()%>"><%=status.getVendorId()%></a></td>
                    <td><%=status.getVendorName()%></td>
                    <td><%=status.getBidderId()%></td>
                    <td><%=status.getStatus()%></td>
                </tr>
                <%
                    }
                }
                %>
            </table>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

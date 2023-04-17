<%@ page import="java.util.HashMap" %>
<%@ page import="ETU2035.framework.model.Departement" %>

<%
    HashMap<String,Departement> depte =(HashMap<String,Departement>)request.getAttribute("findAllDept");
    Departement dept = depte.get("findAllDept");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Département</title>
</head>
<body>
    <h1>Département <%= dept.getNom_departement() %></h1>
    <p>Code : <%= dept.getNbr_departement() %></p>
</body>
</html>


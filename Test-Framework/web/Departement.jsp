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

    <h2>Insertion Département</h2>
    <form action="saveDept" >
        <label for="name">Nom Département</label>
        <p><input type="text" name="nom_departement"></p>
        <br>
        <label for="name">Nombre Département</label>
        <p><input type="number" name="nbr_departement"></p>
        <br>
        <input type="hidden" name="classe" value="Departement">
        <input type="submit" value="Inserer">
    </form>
</body>
</html>


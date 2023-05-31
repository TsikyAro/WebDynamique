<%@ page import="java.util.HashMap" %>
<%@ page import="model.Departement" %>
<%
//    HashMap<String,Departement> depte =(HashMap<String,Departement>)request.getAttribute("findAllDept");
//    Departement dept = depte.get("findAllDept");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departement</title>
</head>
<body>


    <h2>Insertion Departement</h2>
    <form action="saveDept" method="post" enctype="multipart/form-data" >
        <label for="name">Nom Departement</label>
        <p><input type="text" name="nom_departement"></p>
        <br>
        <label for="name">Nombre Departement</label>
        <p><input type="number" name="nbr_departement"></p>
        <br>
        <input type="hidden" name="classe" value="Departement">
        <input type="file" name="upload" id="">
        <input type="submit" value="Inserer">
    </form>
</body>
</html>


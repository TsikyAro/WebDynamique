<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Insertion nombre Departement</h2>
        <form action="findAllDept" method="post" enctype="multipart/form-data" >
            <label for="id">Nombre Departement</label>
            <p><input type="text" name="id"></p>
            <br>
            <input type="submit" value="Inserer">
        </form>
    </body>
</html>

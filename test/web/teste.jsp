<%@page import="model.*" %>
<%
    Departement dept = (Departement)request.getAttribute("aro");
%>
<%= dept.getUpload().getNomFichier()%> 
<%= dept.getNom_departement()%>
<%= dept.getNbr_departement()%>

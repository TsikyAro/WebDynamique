<%@page import="model.*" %>
<%
    Departement dept = (Departement)request.getAttribute("aro");
%>
<%= dept.getUpload().getNomFichier()%> 

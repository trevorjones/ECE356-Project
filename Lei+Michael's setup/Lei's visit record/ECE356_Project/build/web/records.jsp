<%-- 
    Document   : records
    Created on : 24-Mar-2014, 8:37:06 PM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Patient"%>
<%@page import="ece356.Record_list"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <%! ArrayList<Record_list> recordlist;%>
    <% recordlist = (ArrayList<Record_list>) request.getAttribute("record_list");%>
    <%  String puserid =null;
        for (Record_list rl : recordlist){
        puserid = rl.getPuserid();
    }%>
    
    <body>
        <%
            if (recordlist != null) {
        %>
        <h1>Visitation Records for Patient <%=puserid%></h1>
        <form method="post" action="QueryServlet?qnum=9">
            Record Search<input type='text' name='visit_date'/></br>
            <input type ="hidden" name ="patient_id" value ="<%=puserid%>">
        <input type='submit' value='Submit Query'/>
        </form>
        <table border=1>
            <tr><th>Patient ID</th><th>Doctor ID</th><th>Visit Time</th></tr>
            <%
                for (Record_list rl : recordlist) {
            %>
            <tr>
                <td><%= rl.getPuserid()%></td>
                <td><%= rl.getDuserid()%></td>
                <td><a href="QueryServlet?qnum=8&patient_id=<%= rl.getPuserid()%>&visit_date=<%=rl.getVisit_date()%>"><%=rl.getVisit_date()%></a>
                        </td>
                
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <p>FAILED</p>
        <%
            }
        %>        
    </body>
</html>

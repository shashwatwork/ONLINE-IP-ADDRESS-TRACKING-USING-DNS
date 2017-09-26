<%@ page import="pp.subnetCalc"%>
<%@ page import="pp.subnetCalcEngine"%>
<%@ page import="pp.subnetCalcInterface"%>

<%
subnetCalc obj=new subnetCalc();
obj.main();
response.sendRedirect("AA.jsp");
%>

<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.URLConnection"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
String str;
str=request.getParameter("t1");

URL ipapi = new URL("https://ipapi.co/"+str+"/json/");

URLConnection c = ipapi.openConnection();
c.setRequestProperty("User-Agent", "java-ipapi-client");
BufferedReader reader = new BufferedReader(
  new InputStreamReader(c.getInputStream())
  );
String location = reader.readLine();
reader.close();

out.println(location);
%>
</body>
</html>
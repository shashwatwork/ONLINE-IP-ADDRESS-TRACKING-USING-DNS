<%@ page import="java.net.*"%>
<title>IP ADREESS</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/nivo-slider.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

<body bgcolor="brown">


<table border=0 color=black width=100% height=2% >
<tr>
    <td>
	    <center><font  face="Time New Roman" color="blue" size=33><i>Welcome to the My DCN  Project </i></center></font>
	</th>
	 </tr>
	 </table>
	 <br>
<table border=1 color=black width=100% height=2% >
<tr>
    <td>
	    <center><font  face="Time New Roman" color="Red" size=33><u><i>IP ADDRESS TRACING USING DSN </u></i></center></font>
	</th>
	 </tr>
	 </table>
	
	 <br>
	 <br>
	 <br>
	 <table>
<tr>
    <td>
	    <img src="images/a.jpg" alt="Smiley face" height="100" width="100">
	</th>
	 </tr>
	 </table>
	 
	  
	 <br>


<%

String str="",ip;
ip=request.getParameter("t1");

try
{
	
	InetAddress[] address=InetAddress.getAllByName(ip);
	for(int j=0;j<address.length;j++)
	str=str+address[j];
}

catch(Exception e)
{
	out.println("Error in accessing Internet :"+e);
}




%>



<h2>IP Address of <%=str %></h2>

<center>



<h3><a href="http://<%=ip%>"> Click To Show The Original Page</h3></a>
<h3><a href="BB.jsp"> Click To calculater Host Id and Net ID</h3></a>




</center>
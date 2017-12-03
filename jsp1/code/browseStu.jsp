<%@ page language="java" import="java.util.*,java.sql.*" 
         contentType="text/html; charset=utf-8"
%><%
	request.setCharacterEncoding("utf-8");
	String msg ="";
	Integer pgno = 0;
	Integer pgcnt = 4;
	String param = request.getParameter("pgno");

	if(param != null && !param.isEmpty()){
		pgno = Integer.parseInt(param);
		}
	param = request.getParameter("pgcnt");
	if(param != null && !param.isEmpty()){
		pgcnt = Integer.parseInt(param);
		}
	int pgprev = (pgno>0)?pgno-1:0;
	int pgnext = pgno+1;

	String connectString = "jdbc:mysql://localhost:53306/teaching"
					+ "?autoReconnect=true&useUnicode=true"
					+ "&characterEncoding=UTF-8"; 
        StringBuilder table=new StringBuilder("");
	try{
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con=DriverManager.getConnection(connectString, 
	                 "user", "123");
		Statement stmt=con.createStatement();
		String sql=String.format("select * from stu limit %d,%d",pgno*pgcnt,pgcnt);
	  ResultSet rs=stmt.executeQuery(sql);
	  table.append("<table><tr><th>id</th><th>ѧ��</th><th>����</th>"+"<th>-</th></tr>");
			while(rs.next()) {
			table.append(String.format(
			"<tr><td>%s</td><td>%s</td><td>%s</td><td>%s %s</td></tr>",
			rs.getString("id"),rs.getString("num"),rs.getString("name"),
			"<a href='updateStu.jsp?pid="+rs.getString("id")+"'>�޸�</a>",
			"<a href='deleteStu.jsp?pid="+rs.getString("id")+"'>ɾ��</a>"
			)
			);
			}
			table.append("</table>");
	  rs.close();
	  stmt.close();
	  con.close();
	}
	catch (Exception e){
	  msg = e.getMessage();
	}
%><!DOCTYPE HTML>
<html>
<head>
<title>���ѧ������</title>
<style>
	table{
	border-collapse: collapse;
	border: none;
	width: 500px;
	}
	td,th{
	border: solid grey 1px;
	margin: 0 0 0 0;
	padding: 5px 5px 5px 5px
	}
	a:link,a:visited{
	color:blue
	}
	.container{
	margin:0 auto;
	width:500px;
	text-align:center;
	}
	</style>
</head>
<body>
  <div class="container">
	  <h1>���ѧ������</h1>  
		<%=table%><br><br>  
		<div style="float:left">
			<a href="addStu.jsp"> ����</a>
		</div>
		<div style="float:right">
				<a href="browseStu.jsp?pgno=<%=pgprev%>&pgcnt=<%=pgcnt%>">��һҳ</a>
				<a href="browseStu.jsp?pgno=<%=pgnext%>&pgcnt=<%=pgcnt%>">��һҳ</a>
		</div>
		<%=msg%>
		<br><br>
	</div>
	
</body>
</html>

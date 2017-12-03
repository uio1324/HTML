<%@ page language="java" import="java.util.*,java.sql.*" 
         contentType="text/html; charset=utf-8"
%>
<%
	request.setCharacterEncoding("utf-8");
	String msg ="";
	String query = "";
	String sumbit = request.getParameter("submit");
	if(sumbit != null && sumbit.equals("查询")){
		query = request.getParameter("query");
	}
	String connectString = "jdbc:mysql://localhost:53306/teaching"
					+ "?autoReconnect=true&useUnicode=true"
					+ "&characterEncoding=UTF-8&&useSSL=false"; 
    String sqlQuery = "select * from stu where num like '%"+query+"%' or name like '%"+query+"%';";
	ArrayList<HashMap<String, String>> table= new ArrayList<HashMap<String, String>>();
    try{
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con=DriverManager.getConnection(connectString, 
	                 "user", "123");
	  Statement stmt=con.createStatement();
	  ResultSet rs=stmt.executeQuery(sqlQuery);
	  while(rs.next()) {
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("id", rs.getString("id"));
		temp.put("num", rs.getString("num"));
		temp.put("name", rs.getString("name"));
		table.add(temp);
	  }
      rs.close();
	  stmt.close();
	  con.close();
	}
	catch (Exception e){
	  msg = e.getMessage();
	}
%>
<!DOCTYPE HTML>
<html>
<head>
	<title>查询学生名单</title>
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
	  <h1 align="center">查询学生名单</h1>
	  <form action="queryStu.jsp" method="post">
	  	输入查询：<input type="text" name="query" value="<%=query %>"/>
	  	<input type="submit" name="submit" value="查询"/>
	  </form><br/>  
	  <table border="1">
	  	<tr>
	  		<td>id</td>
	  		<td>学号</td>
	  		<td>姓名</td>
	  		<td>-</td>
	  	</tr>

 		<%Iterator<HashMap<String, String>> it = table.iterator();
 			while(it.hasNext()){ 
 				HashMap<String, String> temp = it.next();%>
	  	<tr>
	  		<td><%= temp.get("id") %></td>
	  		<td><%= temp.get("num") %></td>
	  		<td><%= temp.get("name") %></td>
	  		<td>
	  			<a href="updateStu.jsp?id=<%=temp.get("id")%>">修改</a>
	  			<a href="deleteStu.jsp?id=<%=temp.get("id")%>">删除</a>
	  		</td>
	  	</tr>
	  	<%} %>
		  </table>
	  <br/><br/>
	  <p>
	  	<a href="addStu.jsp">新增</a>
	  	<a href="browseStu2.jsp">返回</a>
	  </p>
  </div>
</body>
</html>

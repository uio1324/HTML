<%@ page language="java" import="java.util.*,java.sql.*" 
         contentType="text/html; charset=utf-8"
%>
<%
	request.setCharacterEncoding("utf-8");
	String msg ="";
	String connectString = "jdbc:mysql://localhost:53306/teaching"
					+ "?autoReconnect=true&useUnicode=true"
					+ "&characterEncoding=UTF-8"; 
    String user = "user"; 
    String pwd = "123";
    String id = request.getParameter("id");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(connectString,user, pwd);
    Statement stmt = con.createStatement();
    try{
        int cnt = stmt.executeUpdate("delete from stu where id="+id);
        if(cnt > 0) msg = "删除成功";
        stmt.close();
        con.close();
    }catch(Exception e){
        msg = e.getMessage();
    }
%>
<!DOCTYPEHTML>
<html>
    <head>
        <title>删除学生记录</title>
        <style>a:link,a:visited{color:blue;}
            .container{
            margin:0 auto;
            width:500px;
            text-align:center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>删除学生记录</h1>
            <%=msg%>
            <a href='browseStu.jsp'>返回</a>
        </div>
    </body>
</html>
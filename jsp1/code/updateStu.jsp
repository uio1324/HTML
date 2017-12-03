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
    String num = "";
    String name = "";
    String submit1 = request.getParameter("submit1");
	String submit2 = request.getParameter("submit2");
    String id = request.getParameter("id");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(connectString,user, pwd);
    Statement stmt = con.createStatement();
    if(submit1 == null && submit2 == null){
		try{
			ResultSet rs=stmt.executeQuery("select * from stu where id="+id);
			if(rs.next()){
				num = rs.getString("num");
				name = rs.getString("name");
			}
			rs.close();
		}catch (Exception e){
		  msg += e.getMessage();
		}	
	}

    if(submit1 != null && submit1 == "修改"){
        msg = "修改成功！";
        num = request.getParameter("num");
        name = request.getParameter("name");
        try{
			stmt.executeUpdate("UPDATE stu SET num='" + num + "', name='" + name + "'  WHERE id=" + id);
		}catch (Exception e){
		  msg = e.getMessage();
		}
    }

    else if(submit2 != null && submit2.equals("清空")){
		num = "";
		name = "";
    }
    
    stmt.close();
	con.close();
%>
<!DOCTYPEHTML>
<html>
    <head>
        <title>修改学生记录</title>
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
            <h1>修改学生记录</h1>
            <form action="addStu.jsp" method="post" name="f">
                学号：<input id="num" name="num" type="text">
                <br><br>
                姓名:<input id="name"type="text"name="name">
                <br><br>
                <input type="submit" name="submit1" value="修改"/>
                <input type="submit" name="submit2" value="清空"/>
            </form>
            <%=msg%>
            <a href='browseStu.jsp'>返回</a>
        </div>
    </body>
</html>
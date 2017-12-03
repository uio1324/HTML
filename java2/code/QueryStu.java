import java.util.*;
import java.sql.*;

public class QueryStu {
    static private Connection conn;
	static Scanner in = new Scanner(System.in);
    static int sno = 1;

    public static void main(String args[]) {
        ResultSet rs = null;
		if (connect()) {
            String lineWords;
            while ((lineWords=getNextLineWords())!=null) { // 是否还有输入
                if(lineWords.equals("*"))
                    rs = executeQuery("select * from stu order by num;");
                else if (lineWords.startsWith("-"))
                    rs = executeQuery("select * from stu where id="+lineWords.substring(1,lineWords.length()));
                else
                    rs = executeQuery("select * from stu where num like \"%"+lineWords+"%\"or name like \"%"+lineWords+"%\" order by num");
			    showStudents(rs);
                
            }
			
		} else {
			System.out.println("Connect Error!");
		}
	}

    // 建立连接
	private static boolean connect() {
		String connectString = "jdbc:mysql://172.18.187.230:53306/teaching11"
				+ "?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&&useSSL=false";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectString, "user", "123");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

    //执行SQL查询语句, 返回结果集
	static private ResultSet executeQuery(String sqlSentence) {
	     Statement stat;
	     ResultSet rs = null;
	        
	     try {
		stat = conn.createStatement();       //获取执行sql语句的对象
		rs = stat.executeQuery(sqlSentence); //执行sql查询，返回结果集
	     } catch (Exception e) {
		System.out.println(e.getMessage());
	     }
	     return rs;
	}

	//显示查询结果
	private static void showStudents(ResultSet rs){
        int cnt = 0;
	    try {
            cnt = 0;
	       while(rs.next()){
	          System.out.printf(rs.getInt("id")+" "+rs.getString("num")+" "+rs.getString("name")+"\n");	
              cnt++;	   
	       }
           System.out.printf("[%d条记录]\n\n",cnt);
	    }
	    catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}

    //输入一行命令并存入字符串linewords中
    public static String getNextLineWords(){
        if (sno==1){
            System.out.println("查询学号和姓名（部分匹配），以-开头查询id，*查出所有记录，exit或空行退出.\r\n");
        }
        System.out.print(sno+"> ");			
        if (!in.hasNextLine()) { // 是否还有输入
                return null;
            }
        String line = in.nextLine(); // 读取下一行
            line = line.trim();
            if (line.equals("exit") || line.length() == 0) {
            return null;
        }
            sno++;
        return line;	
    } 
}
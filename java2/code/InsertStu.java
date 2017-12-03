import java.util.*;
import java.sql.*;

public class InsertStu{
    static private Connection conn;
	static Scanner in = new Scanner(System.in);
    static int sno = 1;

    public static void main(String args[]){
        ResultSet rs = null;
        if (connect()) {
            String lineWords[];
            while ((lineWords=getNextLineWords())!=null) { // 是否还有输入
                //rs = executeQuery("insert into stu(num,name)values('"+lineWords[0]+"','"+lineWords[1]+"')");
                executeUpdate("insert into stu(num,name)values('"+lineWords[0]+"','"+lineWords[1]+"')");
                //System.out.printf("insert into stu(num,name)values('"+lineWords[0]+"','"+lineWords[1]+"')\n");
                
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
	private static void executeUpdate(String sqlSentence) {
	     Statement stat;    
	     try {
		stat = conn.createStatement();       //获取执行sql语句的对象
		stat.executeUpdate(sqlSentence); //执行sql查询，返回结果集
        System.out.printf("[%d条记录被加入！]\n\n",1);
	     } catch (Exception e) {
		System.out.println(e.getMessage());
        System.out.printf("[%d条记录被加入！]\n\n",0);
	     }
	}

    //输入一行命令并存入字符串linewords中
    public static String[] getNextLineWords(){
        if (sno==1){
            System.out.println("输入学号和姓名（用空格间隔），exit或空行退出.\r\n");
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
        return line.split(" ");	
    } 


}
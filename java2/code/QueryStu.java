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
            while ((lineWords=getNextLineWords())!=null) { // �Ƿ�������
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

    // ��������
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

    //ִ��SQL��ѯ���, ���ؽ����
	static private ResultSet executeQuery(String sqlSentence) {
	     Statement stat;
	     ResultSet rs = null;
	        
	     try {
		stat = conn.createStatement();       //��ȡִ��sql���Ķ���
		rs = stat.executeQuery(sqlSentence); //ִ��sql��ѯ�����ؽ����
	     } catch (Exception e) {
		System.out.println(e.getMessage());
	     }
	     return rs;
	}

	//��ʾ��ѯ���
	private static void showStudents(ResultSet rs){
        int cnt = 0;
	    try {
            cnt = 0;
	       while(rs.next()){
	          System.out.printf(rs.getInt("id")+" "+rs.getString("num")+" "+rs.getString("name")+"\n");	
              cnt++;	   
	       }
           System.out.printf("[%d����¼]\n\n",cnt);
	    }
	    catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}

    //����һ����������ַ���linewords��
    public static String getNextLineWords(){
        if (sno==1){
            System.out.println("��ѯѧ�ź�����������ƥ�䣩����-��ͷ��ѯid��*������м�¼��exit������˳�.\r\n");
        }
        System.out.print(sno+"> ");			
        if (!in.hasNextLine()) { // �Ƿ�������
                return null;
            }
        String line = in.nextLine(); // ��ȡ��һ��
            line = line.trim();
            if (line.equals("exit") || line.length() == 0) {
            return null;
        }
            sno++;
        return line;	
    } 
}
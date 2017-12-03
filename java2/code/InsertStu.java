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
            while ((lineWords=getNextLineWords())!=null) { // �Ƿ�������
                //rs = executeQuery("insert into stu(num,name)values('"+lineWords[0]+"','"+lineWords[1]+"')");
                executeUpdate("insert into stu(num,name)values('"+lineWords[0]+"','"+lineWords[1]+"')");
                //System.out.printf("insert into stu(num,name)values('"+lineWords[0]+"','"+lineWords[1]+"')\n");
                
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
	private static void executeUpdate(String sqlSentence) {
	     Statement stat;    
	     try {
		stat = conn.createStatement();       //��ȡִ��sql���Ķ���
		stat.executeUpdate(sqlSentence); //ִ��sql��ѯ�����ؽ����
        System.out.printf("[%d����¼�����룡]\n\n",1);
	     } catch (Exception e) {
		System.out.println(e.getMessage());
        System.out.printf("[%d����¼�����룡]\n\n",0);
	     }
	}

    //����һ����������ַ���linewords��
    public static String[] getNextLineWords(){
        if (sno==1){
            System.out.println("����ѧ�ź��������ÿո�������exit������˳�.\r\n");
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
        return line.split(" ");	
    } 


}
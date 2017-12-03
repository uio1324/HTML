import java.sql.*;

public class ShowStudents {
	static private Connection conn;
	static int cnt = 0;

	public static void main(String args[]) {
		if (connect()) {
			ResultSet rs = executeQuery("select * from stu;");
			showStudents(rs);
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
	    try {
	       while(rs.next()){
	          System.out.println(rs.getString("name"));		   
	       }
	    }
	    catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}


}
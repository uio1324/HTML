import java.util.*;

public class InputData {
	static Scanner in = new Scanner(System.in);
        static int sno = 1;
	public static void main(String args[]) {
                String lineWords[];
 		while ((lineWords=getNextLineWords())!=null) { // �Ƿ�������
                   System.out.println(String.format("������%d���ʣ�%s",lineWords.length,Arrays.toString(lineWords))+"\n");
		} 
	}

        public static String[] getNextLineWords(){
           if (sno==1){
     	      System.out.println("ÿ���������ɴ�(ֻ����һ���ո���)��exit������˳�.\r\n");
           }
           System.out.print("��"+sno+"��> ");			
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
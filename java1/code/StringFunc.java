public class StringFunc{
    final static String s="扁担长，板凳宽，板凳没有扁担长，扁担没有板凳宽。扁担要绑在板凳上,板凳偏不让扁担绑在板凳上。";
    public static void main(String args[]){
        String str = s.substring(4,7);
        System.out.println(str);
        int a = s.indexOf("扁担");
        while(a != -1)
        {
            System.out.print(a + "\t");
            a = s.indexOf("扁担", a + 1);
        }
        System.out.println();
        String ss = "";
        long start= System.currentTimeMillis();
        for(int i = 0; i < 10000; i++)
        {
            ss += s;
        }
        long end= System.currentTimeMillis();
        System.out.printf("字符串相加的时间：%d 字符串长度： %d \n", end - start, ss.length());
        StringBuilder sb = new StringBuilder();
        start= System.currentTimeMillis();
        for(int i = 0; i < 10000; i++)
        {
            sb.append(s);
        }
        end= System.currentTimeMillis();
        System.out.printf("StringBuilder的时间：%d 字符串长度： %d \n", end - start, sb.length());

    }
}




import java.util.*;

public class Fib{
    public static void main(String args[]){
        long fib0=0,fib1=1,fib2=1;
        ArrayList <Long> fibs = new  ArrayList <Long> () ;
        fibs.add(fib0);fibs.add(fib1);fibs.add(fib2);

        while(fibs.get(fibs.size()-1) > 0)
        {
            fibs.add(fibs.get(fibs.size()-1) + fibs.get(fibs.size()-2));
        }
        System.out.printf("max fib(long) %d count: %d\n", fibs.get(fibs.size()-2), fibs.size()-1);
        double ratio[] = new double[fibs.size()-1];
        Iterator it = fibs.iterator();
        Long last = (Long) it.next();
        int index = 0;
        while(it.hasNext())
        {
            Long next = (Long) it.next();
            ratio[index++] = (double)last/next;
            last = next;
        }
        for(double r:ratio)
        {
            System.out.print(r+"\t");
        }
    }
}




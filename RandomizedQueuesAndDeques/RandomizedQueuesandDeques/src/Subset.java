
/**
 * Deque class
 * 
 * Description:  A double-ended queue or deque (pronounced "deck") 
 * is a generalization of a stack and a queue that supports inserting 
 * and removing items from either the front or the back of the data
 *  structure
 *  
 *  //echo AA BB CC DD | java -cp 
 *  ':/home/esakaiev/algs4/algs4.jar:/home/esakaiev/algs4/stdlib.jar' Subset 7
 *  
 *  @author Eduard Sakaiev
 */

public class Subset {
   public static void main(String[] args) 
   {
       int k, N = 0;
       String str;
       RandomizedQueue<String> rq = new RandomizedQueue<String>();

       if (StdIn.isEmpty()) 
           System.out.println("No input elements");
       
       if (args[0] != null)
       {
           k = Integer.parseInt(args[0]);
           //System.out.println("Your string was: " + k);
           while (!StdIn.isEmpty())
           {
               str = StdIn.readString();
               rq.enqueue(str);
           }
           N = rq.size();
           
          /* for (int i = 0; i < N; ++i)
               System.out.println(rq.dequeue());*/


          if (k > 0 && k <= N)
             for (int i = 0; i < k; ++i)
                System.out.println(rq.dequeue());
          else if (k > 0 && k > N)
             for (int i = 0; i < N; ++i)
                System.out.println(rq.dequeue());
       }
   }
}

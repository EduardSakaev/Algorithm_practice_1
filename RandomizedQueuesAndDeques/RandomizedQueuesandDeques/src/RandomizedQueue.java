
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Randomized Queue class
 * 
 * Description: A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from 
 * items in the data structure
 * 
 *  @author Eduard Sakaiev
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
    private int last  = 0;       // index of next available slot

    /**
     * Initializes an empty queue.
     */
    public RandomizedQueue() {
        // cast needed since no generic array creation in Java
        q = (Item[]) new Object[2];
    }
   /*
    * // is the deque empty?
    */
   public boolean isEmpty() {
      return N == 0;
   }

   /*
    * // return the number of items on the deque
    */
   public int size() {
       return N;
   }

   // resize the underlying array
   private void resize(int max) {
       assert max >= N;
       Item[] temp = (Item[]) new Object[max];
       for (int i = 0; i < N; i++) {
           temp[i] = q[(i) % q.length];
       }
       q = temp;
       last  = N;
   }
   
   // resize the underlying array
   private void displace(int randomnumber) {
       assert randomnumber >= N;
       q[randomnumber] = q[last-1];
       q[last-1] = null; // to avoid loitering
       last--;
   }

   /**
    * Adds the item to this queue.
    * @param item the item to add
    */
   public void enqueue(Item item) {
       // double size of array if necessary and recopy to front of array
       if (item == null) throw new java.lang.NullPointerException();
       if (N == q.length) resize(2*q.length);   // double size of array if necessary
       q[last++] = item;                        // add item
       N++;
   }
   
   /**
    * Removes and returns the item on this queue that was least recently added.
    * @return the item on this queue that was least recently added
    * @throws java.util.NoSuchElementException if this queue is empty
    */
   public Item dequeue() {
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       int randomnumber = 0;
       Item item = q[randomnumber];
       if (last > 0)
       {
           randomnumber = StdRandom.uniform(0, last); //get random between 0 and N-1
           item = q[randomnumber];
           displace(randomnumber);
       }
       N--;
       // shrink size of array if necessary
       if (N > 0 && N == q.length/4) resize(q.length/2); 
       return item;
   }

   /*
    * // return (but do not delete) a random item
    */
   public Item sample() {
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       int randomnumber = 0;
     //get random between 0 and N-1
       if (last > 0) randomnumber = StdRandom.uniform(0, last); 
       Item item = q[randomnumber];
       return item;
   }

   /*
    * // return an independent iterator over items in random order
    */
   public Iterator<Item> iterator() {
       return new ArrayIterator();
   }

// an iterator, doesn't implement remove() since it's optional
   private class ArrayIterator implements Iterator<Item> {
       private int iterlast = 0;
       private Item[] iterq;

       ArrayIterator() {
           iterq = (Item[]) new Object[N + 1];
           for (int j = 0; j < N; ++j)
              iterq[j] = q[j];
           iterq[N] = null;
           iterlast = N;
       }

       public boolean hasNext()  { return iterlast  > 0;          }
       public void remove()      { throw new UnsupportedOperationException();  }

       private void replace(int random) {
           assert random >= N;
            iterq[random] = iterq[iterlast - 1];
            iterq[iterlast - 1] = null; // to avoid loitering
            iterlast--;
       }

       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           int randomnumber = 0;
           Item item = iterq[randomnumber];
           
           if (iterlast > 0) 
           {
               //get random between 0 and N-1
               randomnumber = StdRandom.uniform(0, iterlast); 
               item = iterq[randomnumber];
               replace(randomnumber);
           }
           
           return item;
       }
   }

   /*
    * // unit testing
    */
   public static void main(String[] args) {

       RandomizedQueue<String> srq = new RandomizedQueue<String>();
       for (int i = 0; i < 5; ++i)
          srq.enqueue("asdaf " + i);
       /*for (int i = 0; i < 10; ++i)
       {
          System.out.println("Add " +i);
       }*/



       for (int i = 0; i < 5; ++i)
       {
           int random = StdRandom.uniform(5);
           if (random == 0)
           {
               srq.enqueue("Add " +i);
               System.out.println("Add " + i);
           }
           else if (random == 1)
           {
               System.out.println("Remove " + srq.dequeue());
           }
          /* else if (random == 2)
               srq.isEmpty();
           else if (random == 3)
               srq.sample();
           else if (random == 4)
              srq.size();*/
        }
       /*int N = srq.size();
       for (int i = 0; i < N; ++i)
       {
         System.out.println("Remove " + srq.dequeue());
       }*/



       int count = 0;
       for (Iterator itr = srq.iterator(); itr.hasNext();) 
       {
           count++;
           System.out.println("Iter " + itr.next());
       }
       
       System.out.println("Test finished");
       
   }
}
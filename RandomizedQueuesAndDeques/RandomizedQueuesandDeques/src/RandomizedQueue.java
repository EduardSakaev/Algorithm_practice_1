
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
    private int N;               // number of elements on dequeue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private Node<Item> current;  // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

   /*
    * // construct an empty deque
    */
   public RandomizedQueue() {
       first   = null;
       last    = null;
       current = null;
       N = 0;
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


   /*
    * // add the item
    */
   public void enqueue(Item item) {
       Node<Item> oldlast = last;
       last = new Node<Item>();
       last.item = item;
       last.next = null;
       if (isEmpty()) first = last;
       else           oldlast.next = last;
       N++;
   }
   
   /*
    * // delete and return a random item
    */
   public Item dequeue() {
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       int randomnumber = StdRandom.uniform(N); //between 0 and N-1
       int icurelepos   = randomnumber;
       getUniformItemRef(1, randomnumber);
       Item item = current.item;

       if (icurelepos == 0)
       {
          first = first.next;
       }
       else if (icurelepos == N - 1)
       {
           item = current.next.item;
           current.next = null;
           last = current;
       }
       else 
       {
           item = current.next.item;
           current.next = current.next.next;
       }
       N--;

       return item;
   }

   /*
    * // return (but do not delete) a random item
    */
   public Item sample() {
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       int randomnumber = StdRandom.uniform(N); //between 0 and N-1
       Item item;
       getUniformItemRef(0, randomnumber);
       item = current.item;

       return item;
   }

   /*
    * // return an independent iterator over items in random order
    */
   public Iterator<Item> iterator() {
       int randomnumber = StdRandom.uniform(N); //between 0 and N-1
       getUniformItemRef(0, randomnumber);
       return new ListIterator<Item>(current);
   }


   private void getUniformItemRef(int ileftindex, int randomnumber)
   {
       current = first;
       int count = randomnumber;
       while (count > ileftindex)
       {
           current = current.next;
           count--;
           }
   }

   // an iterator, doesn't implement remove() since it's optional
   private class ListIterator<Item> implements Iterator<Item> {
       private Node<Item> current;

       public ListIterator(Node<Item> first) {
           current = first;
       }

       public boolean hasNext()  { return current.next != null;                }
       public void remove()      { throw new UnsupportedOperationException();  }
       
       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next; 
           return item;
       }
   }
   
   /*
    * // unit testing
    */
   public static void main(String[] args) {
       RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

       for (int i = 0; i < 10; ++i)
       {
           rq.enqueue(i);
       }

       for (int i = 0; i < 5; ++i)
           rq.enqueue(i);

       for (int i = 0; i < 13; ++i)
           rq.dequeue();

       while (!rq.isEmpty())
       {
           StdOut.println("Smthing" + " " + rq.dequeue());
           StdOut.println("Smthing" + " " + rq.dequeue());
       }

       StdOut.println("Is Empty" + " " + rq.isEmpty());
   }
}
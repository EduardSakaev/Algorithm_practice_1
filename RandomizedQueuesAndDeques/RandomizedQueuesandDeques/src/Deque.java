import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque class
 * 
 * Description:  A double-ended queue or deque (pronounced "deck") 
 * is a generalization of a stack and a queue that supports inserting 
 * and removing items from either the front or the back of the data
 *  structure
 *  
 *  
 *  @author Eduard Sakaiev
 */

public class Deque<Item> implements Iterable<Item> {

    private int N;               // number of elements on dequeue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

   /*
    * // construct an empty deque
    */
   public Deque() {
       first = null;
       last  = null;
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
    * // insert the item at the front
    */
   public void addFirst(Item item) {
       if (item == null) throw new java.lang.NullPointerException();
       Node<Item> oldfirst = first;
       first = new Node<Item>();
       first.item = item;
       first.prev = null;
       first.next = oldfirst;
       if (isEmpty())
          last = first;
       else
          oldfirst.prev = first;
       N++;

   }

   /*
    * // insert the item at the end
    */
   public void addLast(Item item) {
       if (item == null) throw new java.lang.NullPointerException();
       Node<Item> oldlast = last;
       last = new Node<Item>();
       last.item = item;
       last.next = null;
       last.prev = oldlast;

       if (isEmpty())
          first = last;
       else  
          oldlast.next = last;
       N++;
   }

   /*
    * // delete and return the item at the front
    */
   public Item removeFirst() {
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       Item item = first.item;
       first = first.next;
       if (first != null)
          first.prev = null; 
       N--;
       if (isEmpty()) last = null;
       return item;
   }

   /*
    * // delete and return the item at the end
    */
   public Item removeLast() {
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       Item item = last.item;
       last = last.prev;
       if (last != null) last.next = null;
       N--;
       if (isEmpty()) first = null;
       return item;
   }

   /*
    * // return an iterator over items in order from front to end
    */
   public Iterator<Item> iterator() {
      return new ListIterator<Item>(first); 
   }

   // an iterator, doesn't implement remove() since it's optional
   private class ListIterator<Item> implements Iterator<Item> {
       private Node<Item> current;

       public ListIterator(Node<Item> first) {
           current = first;
       }

       public boolean hasNext() {  return current != null;                    }
       public void remove()     { throw new UnsupportedOperationException();  }
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
   public static void main(String[] args)
   {
       Deque<Integer> deq = new Deque<Integer>();
       for (int i = 0; i < 5; ++i)
       {
           System.out.println("AddLast " +i);
           deq.addLast(i);
       }

       for (int i = 0; i < 5; ++i)
       {
           int random = StdRandom.uniform(4);
           if (random == 0)
           {
               System.out.println("AddLast " +i);
               deq.addLast(i);
           }
           else if (random == 1)
           {
               System.out.println("AddFirst " +i);
               deq.addFirst(i);
           }
           else if (random == 2)
           {
               System.out.println("Remove Last" + deq.removeLast());
           }
           else
           {
               System.out.println("Remove First " + deq.removeFirst());
           }
       }
       
       int count = 0;
       for (Iterator itr = deq.iterator(); itr.hasNext();) 
       {
           count++;
           System.out.println("Iter " + itr.next());
       }
       
       System.out.println("In dequeu: " + deq.size() + " In iter: " + count);
   }
}


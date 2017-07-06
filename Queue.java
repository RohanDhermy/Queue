/**
 * Class that will create a Queue where you can add an element to the end of the 
 * queue and remove from the front 
 * @author Rohan Dhermy 
 * @version (06/09/17)
 */
public class Queue<E> {
    private int size; 
    private QueueNode<E> front; 
    private QueueNode<E> back; 
    
    /**
     * Queue constructor that creates a empty queue
     */
    public Queue(){
        this.size = 0; 
        this.front = null; 
        this.back = null; 
    }
    
    /**
     * Add method that adds an object to the end of the queue. 
     * @param element the object being added to the queue 
     */
    //@SuppressWarnings("unchecked")
    public void add(E element){
        QueueNode<E> newNode = new QueueNode(element);
        //If queue is empty, add to the front 
        if(front == null){
            front = newNode; 
            back = newNode; 
            size++;
        }
        else{
            QueueNode current = back; 
            current.next = newNode; 
            back = newNode;
            size++;
        }
    }
    
    /**
     * Method that removes the object from the front of the queue 
     * @return 
     */
    //@SuppressWarnings("unchecked")
    public E remove(){
        E element = (E)front.data; 
        front = front.next;
        size--;
        return element;
    }
    
    /**
     * Method that returns ture if queue is empty, false if not 
     * @return ture if queue is empty, false if not 
     */
    public boolean isEmpty(){
        if(front == null){
            return true; 
        }
        else{
            return false; 
        }
    }
    
    /**
     * Method that clears the queue
     */
    public void clear(){
        front = null; 
        back = null; 
        size = 0; 
    }
    
    /**
     * Method that returns the size of the queue 
     * @return 
     */
    public int size(){
        return this.size; 
    }

    /**
     * Method that returns the Object at the front of the queue
     * @return 
     */
    //@SuppressWarnings("unchecked")
    public E peek(){
        return (E)front.data; 
    }
}
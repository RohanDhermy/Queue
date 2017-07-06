/**
 * Class that creates a QueueNode object. It holds a reference to the next object 
 * and is used to create a Queue. 
 * @author Rohan Dhermy
 * @version (06/09/17)
 */
public class QueueNode<E> {
    public QueueNode<E> next; 
    public E data; 
    
    /**
     * Constructor for QueueNode that will have the passed in object as it's 
     * data field. 
     * @param element the object the Node will hold 
     */
    public QueueNode(E element){
        this.next = null; 
        this.data = element; 
    }
    /**
     * Constructor of QueueNode without any parameters 
     */
    public QueueNode(){
        this.next = null; 
        this.data = null; 
    }
}


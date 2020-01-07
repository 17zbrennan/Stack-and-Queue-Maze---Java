//CIT360 - 01, Zachary Brennan; Queue, Give in class.
public class Queue<E>{
    
    private LinkedList<E> linkedList;
    
    public Queue()
    {
        super();
        linkedList = new LinkedList();
    }

    
    public int size() {
        // TODO Auto-generated method stub
        return linkedList.size();
    }

    
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return linkedList.isEmpty();
    }

    
    public void enqueue(Object e) {
        // TODO Auto-generated method stub
    	linkedList.insertLast((E) e);
        
    }

    
    public Object peak() {
        // TODO Auto-generated method stub
        return linkedList.getFirst();
    }

    
    public Object dequeue() {
        // TODO Auto-generated method stub
        return linkedList.removeFirst();
    }
    
    
    public String toString() {
        return "SinglyLinkedStack [stack=" + linkedList + "]";
    }

}
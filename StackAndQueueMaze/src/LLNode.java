//CIT360 - 01, Zachary Brennan; Linked Node, Given in class.

public class LLNode<E>{
	private E data;
	private LLNode<E> next;
	private LLNode<E> previous;
	
	
	public LLNode(E data, LLNode<E> next) {
		super();
		this.data = data;
		this.next = next;
		this.previous = previous; 
	}
	
	public LLNode(E data) {
		super();
		this.data = data;
		this.next = null;
		this.previous = null;
	}
	
	public LLNode() {
		super();
	}
	
	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public LLNode<E> getNext() {
		return next;
	}
	
	public void setNext(LLNode<E> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "[" + data + "]";
	}
	
	

}
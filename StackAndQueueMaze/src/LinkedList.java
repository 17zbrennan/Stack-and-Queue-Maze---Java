//CIT360 - 01, Zachary Brennan; Linked List, Give in class.

public class LinkedList<E>{

	private LLNode<E> head;
	private LLNode<E> tail;
	private int size;
	
	
	public LinkedList () {
		super();
		size = 0;
		head = null;
		tail = null;
	}


	public void insertFirst(E e) {
		LLNode temp = new LLNode(e, null);
		if(isEmpty()){
			head = temp;
			tail = temp;
		}
		else{
			temp.setNext(head);
			head = temp;
		}
		size++;
		
	}


	
	public void insertLast(E e) {
		LLNode temp = new LLNode(e, null);
		if(isEmpty()){
			head = temp;
			tail = temp;
		}
		else{
			tail.setNext(temp);
			tail = temp;
		}
		size++;
	}
	
	public void insert(E e, int index){
		if (index > size  || index < 0)
			return;
		
		
		if(this.isEmpty()){
			head = tail = new LLNode(e);
		}
		
		if(index == size)
			this.insertLast(e);
		if(index == 0)
			this.insertFirst(e);
		
		LLNode<E> temp = head;
		
		for(int i = 0; i < index - 1;  i++)
			temp = temp.getNext();
				
		LLNode<E> newNode = new LLNode(e);
		newNode.setNext(temp.getNext());
		temp.setNext(newNode);
		
	}


	
	public boolean isEmpty() {
		return (size == 0);
	}


	
	public int size() {
		return size;
	}


	public E removeFirst() {
		E data;
		if(isEmpty())
			return null;
		else if(size == 1){
			data = head.getData();
			head = null;
			tail = null;
			size--;
			return data;
		}
		
		else{
			data = head.getData();
			head = head.getNext();
			size--;
			return data;
		}

	}


	
	public E removeLast() {
		E data;
		if(isEmpty())
			return null;
		else if(size == 1){
			data = head.getData();
			head = null;
			tail = null;
			size--;
			return data;
		}
		
		else{
			data = head.getData();
			
			LLNode temp = head;
			while(temp.getNext() != tail){
				temp = temp.getNext();
			}
			tail = temp;
			size--;
			return data;
		}
	}


	
	
	public E remove(int index) {
		if(index == 0){
			return removeFirst();
		}else if(index == size-1){
			return removeLast();
		}
		if(index > size || index < 0)
		{
			return null;
		}
		LLNode<E> temp = head;
	for(int i = 0; i < index -1;i++) 
			temp = temp.getNext();
	
			E data = temp.getNext().getData();
			temp.setNext((temp.getNext().getNext()));
			size--; 
			return data; 
			
		
		
	}
	
	public E getFirst() {
		return head.getData();
	}


	
	public E getLast() {
		return tail.getData();
	}
	
	public String toString(){
		String str = "";
		LLNode temp = head;
		while(temp != null){
			str += temp.toString();
			temp = temp.getNext();
		}
		return str;
	}	
}


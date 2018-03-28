package ds;

public class Queue {
	public int size;
public int[] array;
public int head;
public int tail;

public Queue () {
	size = 0;
	array = null;
	head = -1;
	tail = 0;
}

public Queue (int _size) {
	size = _size;
	array = new int[size];
	head = -1;
	tail = 0;
}

/*
 * Implement the ENQUEUE(Q, x) function
 */
public void enqueue (int x) {
	tail =x;
	
	
	
	if (tail == array.length) {
		tail =1;
	}
	else {
		tail = tail + 1;
	}
	return;
}

/*
 * Implement the DEQUEUE(Q) function
 */

public int dequeue () {
	int x = head+1;
	
	
		
	if (head ==array.length) { //this checks the array
		head =1;
	}
	else {
		head = head +1;//if statement doesnt go, then else statement
	
	}
	
	return x;
}
	

/*
 * Convert queue to string in the format of #size, head, tail, 
[#elements]
 */

public String toString () {
	String str;
	
	str = size + ", " + head + ", " + tail + ", [";
	for (int i = head; i%size < tail; i++)
		str += array[i+1] + ",";
		
	str += "]";
	return str;
}

/**
 * @param args
 */
public static void main(String[] args) {
	// TODO Auto-generated method stub
	Queue q;
	q = new Queue(10);
	for (int i = 0; i < 5; i++)
		q.enqueue(i);
	System.out.println(q.toString());
	for (int i = 0; i < 2; i++)
		q.dequeue();
	System.out.println(q.toString());
}


}

package ;
// QueueLi class
//

// CONSTRUCTION: with or without a capacity; default is 10
//
// ******************PUBLIC OPERATIONS*********************
// void enqueue( x )	  --> Insert x
// Object getFront( )	 --> Return least recently inserted item
// Object dequeue( )	  --> Return and remove least recent item
// boolean isEmpty( )	 --> Return true if empty; else false
// boolean isFull( )	  --> Return true if capacity reached
// void makeEmpty( )	  --> Remove all items
// ******************ERRORS********************************
// Overflow thrown for enqueue on full queue

/*
 * Linked List based implementation of the queue.
 * written by Evan Korth using data structures written by Mark Allen Weiss
 */

public class QueueLi
{
	/**
	 * Construct the queue.
	 */
	public QueueLi( )
	{
		makeEmpty();
	}

	/**
	 * Test if the queue is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return front == null;
	}

	/**
	 * Test if the queue is logically full.
	 * @return true if full, false otherwise.
	 */
	public boolean isFull( )
	{
		return false;
	}

 
	/**
	 * Make the queue logically empty.
	 */
	public void makeEmpty( )
	{
		front = null;
		back  = null;
	}

	/**
	 * Get the least recently inserted item in the queue.
	 * Does not alter the queue.
	 * @return the least recently inserted item in the queue, or null, if empty.
	 */
	public Object getFront( )
	{
		if( isEmpty( ) )
			return null;
		return front.element;
	}

	/**
	 * Return and remove the least recently inserted item from the queue.
	 * @return the least recently inserted item in the queue, or null, if empty.
	 */
	public Object dequeue( )
	{
		if( isEmpty( ) )
			return null;

		Object frontItem = front.element;
		front = front.next;
		if (front == null)
			back = null;
		return frontItem;
	}

 
	/**
	 * Insert a new item into the queue.
	 * @param x the item to insert.
	 * @exception Overflow if queue is full.
	 */
	public void enqueue( Object x ) 
	{
		ListNode newNode = new ListNode (x);
		if (isEmpty ())
			front = newNode;
		else
			back.next = newNode;
		back = newNode;
		
	}

	private ListNode	front;
	private ListNode	back;

	public static void main( String [ ] args )
	{
		QueueLi q = new QueueLi( );

		for( int i = 0; i < 10; i++ )
			q.enqueue( new Integer( i ) );

		while( !q.isEmpty( ) )
			System.out.println( q.dequeue( ) );

	}	
}


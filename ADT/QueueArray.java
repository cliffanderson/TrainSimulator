package ADT;
/**
 * Created by Artur Janowiec, William Lawrence, Cliff Anderson, Tom Plano.
 * Group 11 - Data Structures at 8:00 AM.
 * 2/10/2016
 */

public class QueueArray<T> implements QueueInterface<T> {

	private Object[] internalArray;
	private int frontIndex, backIndex, count;

	/** Sets up the internal components, such as the front index, back index, and the count.
	 * */
	public QueueArray() {
		internalArray = new Object[5];
		frontIndex = 0;
		backIndex = 0;
		count = 0;
	}

	/** Adds a entry to the to queue.
	 * @param newEntry the entry to be added to the queue.
	 * @return true if added successfully.
	 * */
	public void enqueue (Object newEntry) {

		if(isFull())
		{
			expandArray();
		}

		internalArray[backIndex] = newEntry;
		backIndex = (backIndex + 1) % internalArray.length;

		count++;
	}

	/* Expands the array.
	 * Orders them back into the format of 0 being the frontIndex to X as the backIndex.
	 * It specifically has to handle the fact that the front index might no longer be at 0, and so it has to depend o
	 * */
	private void expandArray() {

		Object[] newArray = new Object[(count*2)];

		int tempIndex = 0;
		count = 0;

		for (int i = 0; i < internalArray.length; i++) {
			if (frontIndex < internalArray.length) {
				newArray[i] = internalArray[frontIndex];
				frontIndex++;
				count++;
			}
			else if (frontIndex >= internalArray.length) {
				newArray[i] = internalArray[tempIndex];
				tempIndex++;
				count++;
			}
		}

		backIndex = count;
		frontIndex = 0;
		internalArray = newArray;
	}

	/** Removes a entry from the front of the queue.
	 * @return the item that was removed.
	 * */
	public T dequeue() throws EmptyQueueException {

		if(isEmpty())
		{
			throw new EmptyQueueException();
		}

		Object obj = internalArray[frontIndex];
		internalArray[frontIndex]=null;

		frontIndex = (frontIndex + 1) % internalArray.length;

		count--;

		return (T)obj;
	}

	/** Gets the entry at the front of the queue.
	 * @return entry at the front of the queue.
	 * */
	public T getFront() throws EmptyQueueException {

		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		return (T) internalArray[frontIndex];
	}

	/** Checks if empty.
	 * @return true if empty.
	 * */
	public boolean isEmpty()
	{
		return (count <= 0);
	}

	/** Checks if full.
	 * @return true if full.
	 * */
	public boolean isFull()
	{
		return (count == internalArray.length);
	}

	/** Checks the size of the queue.
	 * @return returns count.
	 * */
	public int sizeOfQueue() {
		return count;
	}

	/** Clears the queue.
	 * */
	public void clear()
	{
		internalArray =null;
		frontIndex=0;
		backIndex=0;
		count=0;
	}
}
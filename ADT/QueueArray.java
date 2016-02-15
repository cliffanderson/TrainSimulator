package ADT;

public class QueueArray<T> implements QueueInterface<T>{

	Object[] que=new Object[50];
	int frontIndex=0,backIndex=0,count=0;

	public QueueArray() {

	}

	public void enqueue(Object newEntry) {
		if(isFull())
		{
			return;
		}
		que[backIndex]=newEntry;
		backIndex++;
		if(backIndex>que.length)
		{
			backIndex=0;
		}
		count++;
	}

	public T dequeue() {
		if(count<=0)
		{
			throw new EmptyQueueException();
		}

		Object obj = que[frontIndex];
		que[frontIndex]=null;
		frontIndex++;
		if(frontIndex>que.length)
		{
			frontIndex=0;
		}
		count--;
		return (T)obj;
	}


	public T getFront() {
		return (T)que[frontIndex];
	}

	public boolean isEmpty()
	{
		return (count==0);
	}
	public boolean isFull()
	{
		return (count==que.length);
	}
	public void clear()
	{
		que=null;
		frontIndex=0;
		backIndex=0;
		count=0;
	}
}
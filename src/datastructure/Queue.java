package datastructure;

import exception.DataSafetyException;

/**
 * 队列
 * @author Wenfei Wang
 * 下标从1开始
 */
public class Queue <T> implements DataStructure {
    int head;
    int rear;
    Object[] buffer;
    int maxSize;

    public Queue(int size) {
        buffer = new Object[size+1];
        maxSize = size+1;
        head = rear = 0;
    }

    public boolean ifEmpty() {
        return head == 0 && rear == 0;
    }

    public T enQueue(T data) throws DataSafetyException {
        if (this.ifEmpty()) {
            head = 1;
            rear = 1;
            buffer[1] = data;
            return data;
        }
        if (rear == maxSize) {
            throw new DataSafetyException("队列已满");
        }
        buffer[++rear] = data;
        return (T) buffer[rear];
    }

    public T deQueue() throws DataSafetyException {
        if (this.ifEmpty()) {
            throw new DataSafetyException("队列为空");
        }
        T temp = (T) buffer[head];
        rear--;
        for(int i=1;i<=rear;i++){
            buffer[i]=buffer[i+1];
        }
        return temp;
    }

    @Override
    public void printAll() {
        System.out.println("head = " + head);
        System.out.println("rear = " + rear);
        for (int i = head; i <= rear; i++) {
            System.out.println(buffer[i]);
        }
    }
}

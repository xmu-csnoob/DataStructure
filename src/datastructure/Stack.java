package datastructure;

import exception.DataSafetyException;
import tests.DataStructureTest;

/**
 * 栈
 * @author Wenfei Wang
 * 下标从1开始
 */
public class Stack<T> implements DataStructure{
    Object[] buffer;
    int top;
    int maxSize;
    public Stack(int size){
        buffer=new Object[size+1];
        maxSize=size;
        top=0;
    }
    public T push(T data) throws DataSafetyException {
        if(top==maxSize){
            throw new DataSafetyException("栈已满");
        }
        top++;
        buffer[top]=data;
        return (T)buffer[top];
    }
    public T pop() throws DataSafetyException {
        if(top==0){
            throw new DataSafetyException("栈为空");
        }
        T temp=(T)buffer[top];
        top--;
        return (T)temp;
    }
    public T top(){
        return (T)buffer[top];
    }
    @Override
    public void printAll(){
        if(top==0){
            System.out.println("栈为空");
            return;
        }
        for(int i=top;i>=1;i--){
            System.out.println(buffer[i].toString());
        }
    }
}

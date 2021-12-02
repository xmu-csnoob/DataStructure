package datastructure;

/**
 * 线性表
 * @author Wenfei Wang
 * 下标从1开始
 */
public class LinearList<T> implements DataStructure{
    public int length;
    private Object[] list;
    public LinearList(T[] arr){
        length=arr.length;
        list=arr;
    }
    public LinearList(int maxLength){
        list=new Object[maxLength+1];
        length=maxLength;
    }
    public T getEleAt(int pos)throws ClassCastException{
        if(pos<=0){
            throw new ArrayIndexOutOfBoundsException("线性表下标不得小于等于0");
        }else if(pos>length){
            throw new ArrayIndexOutOfBoundsException("线性表下标不得超出数据最大长度");
        }
        return (T)list[pos];
    }
    public T setEleAt(int pos,T var)throws ClassCastException{
        if(pos<=0){
            throw new ArrayIndexOutOfBoundsException("线性表下标不得小于等于0");
        }else if(pos>length){
            throw new ArrayIndexOutOfBoundsException("线性表下标不得超出数据最大长度");
        }
        list[pos]=var;
        return (T)list[pos];
    }
    public int length(){
        return length;
    }
    public int searchValue(T value){
        for(int i=1;i<=list.length;i++){
            if(list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public void printAll() {
        for (int i = 1; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("");
    }
}

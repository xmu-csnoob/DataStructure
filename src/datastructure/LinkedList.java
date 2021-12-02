package datastructure;

import exception.DataSafetyException;

/**
 * 链表
 * @author Wenfei Wang
 * 下标从1开始
 */
public class LinkedList<T> {
    Node root;
    int length;
    Node end;
    public LinkedList(T data){
        root =new Node(data);
        end=root;
        length=1;
    }
    public T getRootData(){
        return this.root.data;
    }
    public int length(){
        return length;
    }
    public Node get(int pos){
        Node temp=root;
        if(pos<=0){
            throw new ArrayIndexOutOfBoundsException("链表下标不得小于等于0");
        }else if(pos>length){
            throw new ArrayIndexOutOfBoundsException("链表下标不得大于数组长度");
        }
        for(int i=1;i<pos;i++){
            temp=temp.next;
        }
        return temp;
    }
    public Node insert(int pos,T data) throws DataSafetyException {
        length++;
        Node node=new Node(this.get(pos),data);
        end=node;
        return node;
    }
    public Node delete(int pos) throws DataSafetyException {
        Node deleted=this.get(pos);
        if(length==1){
            throw new DataSafetyException("链表中只剩一个节点，不允许删除");
        }else if(pos==1){
            Node temp=this.get(pos+1);
            temp.front=null;
            root=temp;
        }else if(pos==length){
            Node temp=this.get(pos-1);
            temp.next=null;
            end=temp;
        }else{
            Node front=this.get(pos-1);
            Node next=this.get(pos+1);
            front.next=next;
            next.front=front;
        }
        return deleted;
    }
    public Node append(T data) throws DataSafetyException {
        Node node =new Node(end,data);
        end=node;
        return node;
    }
    public void printAll(){
        Node temp=root;
        while(temp!=null){
            System.out.println(temp.data.toString());
            temp=temp.next;
        }
    }
    private class Node{
        T data;
        Node next;
        Node front;
        protected Node(T data){
            this.data=data;
            next=null;
            front=null;
        }
        protected Node(Node frontNode,T data) throws DataSafetyException {
            if(frontNode==null){
                throw new NullPointerException("参数frontNode不能为null");
            } else if(frontNode.next!=null){
                throw new DataSafetyException("该节点节点引用不为null");
            }
            this.data=data;
            frontNode.next=this;
            front=frontNode;
        }
        protected Node(T data,Node backNode){
            if(backNode==null){
                throw new NullPointerException("参数backNode不能为null");
            }
            this.next=backNode;
            backNode.front=this;
        }
        protected Node(Node frontNode,T data,Node backNode) throws DataSafetyException {
            if(frontNode==null){
                throw new NullPointerException("参数frontNode不能为null");
            } else if(frontNode.next!=null){
                throw new DataSafetyException("该节点节点引用不为null");
            }
            frontNode.next=this;
            this.front=frontNode;
            if(backNode==null){
                throw new NullPointerException("参数backNode不能为null");
            }
            this.next=backNode;
            backNode.front=this;
        }
    }
}

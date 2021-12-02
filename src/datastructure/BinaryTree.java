package datastructure;

import utils.Generator;

/**
 * @author Wenfei Wang
 */
public class BinaryTree<T> {
    private Node root;
    private int depth;
    private int dfsRecord;
    public BinaryTree(T data){
        root=new Node(data);
    }
    public T getRootData(){
        return root.getData();
    }
    public int getDepth(Node node){
        if(node==null) {
            return 0;
        }
        return Math.max(getDepth(node.leftChild),getDepth(node.rightChild))+1;
    }
    public Node getRoot(){
        return root;
    }
    public void DFS(Node node,int dfsRecord){
        if(node!=null){
            System.out.println(dfsRecord+":"+node.data);
            DFS(node.leftChild,dfsRecord+1);
            DFS(node.rightChild,dfsRecord+1);
        }
    }
    public void generateExample(T data){
        root.setLeftChild(new Node(data));
        root.setRightChild(new Node(data));
        root.getLeftChild().setLeftChild(new Node(data));
    }
    private class Node{
        T data;
        Node leftChild;
        Node rightChild;
        protected Node(T data){
            this.data=data;
        }
        protected void setLeftChild(Node leftChild){
            this.leftChild=leftChild;
        }
        protected void setRightChild(Node rightChild){
            this.rightChild=rightChild;
        }
        protected Node getLeftChild(){
            return leftChild;
        }
        protected Node getRightChild(){
            return rightChild;
        }
        protected void setData(T data){
            this.data=data;
        }
        protected T getData(){
            return data;
        }

    }
}

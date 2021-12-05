package datastructure;

import exception.DataSafetyException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Wenfei Wang
 */
public class UndirectedGraph<T> {
    List<Node> nodes;
    List<Route> routes;
    int[][] matrix;
    boolean[] visited;
    public UndirectedGraph(T data){
        nodes=new ArrayList<>();
        routes=new ArrayList<>();
        Node node=new Node(data,1);
        nodes.add(node);
    }
    public boolean ifNodeExists(T data){
        for(Node node:nodes){
            if(node.value==data) {
                return true;
            }
        }
        return false;
    }
    public void generateMatrix(){
        int nodeNum=nodes.toArray().length;
        matrix=new int[nodeNum][nodeNum];
        for(Route route:routes){
            matrix[route.firstNode.id-1][route.secondNode.id-1]=route.distance;
        }
    }
    public void showMatrix(){
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
    public Node findNode(T data){
        for(Node node:nodes){
            if(node.value==data){
                return node;
            }
        }
        return null;
    }
    public Node addNode(T data) throws DataSafetyException {
        Node node=new Node(data,nodes.toArray().length+1);
        if(!ifNodeExists(data)){
            nodes.add(node);
        }else {
            throw new DataSafetyException("不允许插入与已有节点值相同的节点");
        }
        generateMatrix();
        return node;
    }
    public List<Node> getNeighbors(Node node){
        List<Node> list=new ArrayList<>();
        for(Route route:routes){
            if(route.firstNode.id==node.id){
                list.add(route.secondNode);
            }
            if(route.secondNode.id==node.id){
                list.add(route.firstNode);
            }
        }
        return list;
    }
    public Node addNode(T inserted,T target,int distance){
        Node node=new Node(inserted,nodes.toArray().length+1);
        Node targetNode=this.findNode(target);
        Route route=new Route(node,targetNode,distance);
        nodes.add(node);
        routes.add(route);
        targetNode.degree++;
        node.degree++;
        generateMatrix();
        return node;
    }
    public Node getNodeById(int id){
        for(Node node:nodes){
            if(node.id==id){
                return node;
            }
        }
        return null;
    }
    public Route linkNodes(T var1,T var2,int distance) throws DataSafetyException {
        if(var1==var2){
            throw new DataSafetyException("要连接的两节点值不应该相同");
        }
        Node node1=this.findNode(var1);
        Node node2=this.findNode(var2);
        if(node1==null||node2==null){
            throw new DataSafetyException("图中不存在值为所给值的节点");
        }
        Route route=new Route(node1,node2,distance);
        routes.add(route);
        node1.degree++;
        node2.degree++;
        generateMatrix();
        return route;
    }
    public boolean isAllVisited(){
        for(boolean bool:visited){
            if(!bool) {
                return false;
            }
        }
        return true;
    }
    public void dfs(int id){
        visited=new boolean[nodes.toArray().length];
        Stack<Node> stack=new Stack<>();
        stack.push(getNodeById(1));
        visited[0]=true;
        while(!stack.empty()){
            Node temp=stack.pop();
            System.out.println(temp.id);
            visited[temp.id-1]=true;
            List<Node> list=this.getNeighbors(temp);
            for(Node n:list){
                if(!visited[n.id-1]){
                    stack.push(n);
                }
            }
        }
    }
    public void bfs(int id){
        visited=new boolean[nodes.toArray().length];
        Queue<Node> queue=new LinkedList<>();
        queue.offer(getNodeById(id));
        visited[0]=true;
        while(!queue.isEmpty()){
            Node temp=queue.poll();
            System.out.println(temp.id);
            visited[temp.id-1]=true;
            List<Node> list=this.getNeighbors(temp);
            for(Node n:list){
                if(!visited[n.id-1]){
                    queue.offer(n);
                }
            }
        }
    }
    private class Node{
        T value;
        int degree;
        int id;
        public Node(T data,int id){
            this.value=data;
            degree=0;
            this.id=id;
        }
    }
    private class Route{
        Node firstNode;
        Node secondNode;
        int distance;
        public Route(Node firstNode,Node secondNode,int distance){
            this.firstNode=firstNode;
            this.secondNode=secondNode;
            this.distance=distance;
        }
    }
}

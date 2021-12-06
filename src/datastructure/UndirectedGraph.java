package datastructure;

import exception.DataSafetyException;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Wenfei Wang
 */
public class UndirectedGraph<T> implements DataStructure{
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
    public UndirectedGraph(){
        nodes=new ArrayList<>();
        routes=new ArrayList<>();
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
    private Node addNode(Node node){
        this.nodes.add(node);
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
    public boolean isAllConnected(){
        visited=new boolean[nodes.toArray().length];
        dfs(1);
        for(int i=0;i<nodes.toArray().length;i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
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
    public Route linkNodes(int id1,int id2,int distance) throws DataSafetyException {
        if(id1==id2){
            throw new DataSafetyException("要连接的两节点id不应该相同");
        }
        Node node1=this.getNodeById(id1);
        Node node2=this.getNodeById(id2);
        if(node1==null||node2==null){
            throw new DataSafetyException("图中不存在id为所给值的节点");
        }
        Route route=new Route(node1,node2,distance);
        routes.add(route);
        node1.degree++;
        node2.degree++;
        generateMatrix();
        return route;
    }
    private boolean isAllVisited(){
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
        while(!stack.empty()){
            Node temp=stack.pop();
            if(visited[temp.id-1]){
                continue;
            }
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
    public boolean isConnected(int id1,int id2) throws DataSafetyException {
        Node node=this.getNodeById(id1);
        visited=new boolean[nodes.toArray().length];
        Stack<Node> stack=new Stack<Node>();
        stack.push(node);
        while(!stack.empty()){
            Node temp=stack.pop();
            if(visited[temp.id-1]){
                continue;
            }
            visited[temp.id-1]=true;

            if(temp.id==id2){
                return true;
            }
            List<Node> list=this.getNeighbors(temp);
            for(Node n:list){
                if(!visited[n.id-1]){
                    stack.push(n);
                }
            }
        }
        return false;
    }
    public UndirectedGraph<T> getSpanningTree() throws DataSafetyException {
        Collections.sort(routes);
        UndirectedGraph<T> spanningTree=new UndirectedGraph<T>();
        if(!this.isAllConnected()){
            throw new DataSafetyException("不是连通图！");
        }
        for(Node node:nodes){
            spanningTree.addNode(node);
        }
        System.out.println("开始加边");
        for(Route route:routes){
            Node first=route.firstNode;
            Node second=route.secondNode;
            if(!spanningTree.isConnected(first.id,second.id)){
                spanningTree.linkNodes(first.id,second.id,route.distance);
            }
        }
        return spanningTree;
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
    @Override
    public void printAll() {
        System.out.println("共有" + nodes.toArray().length + "个节点");
        for(Node node:nodes){
            System.out.println(node.toString());
        }
        System.out.println("共有" + routes.toArray().length + "条边");
        for(Route route:routes){
            System.out.println(route.toString());
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
        public int getDegree(){
            return degree;
        }
        @Override
        public String toString(){
            return this.id + ":" + this.value+","+"该节点的度为"+ this.degree;
        }
    }
    private class Route implements Comparable<Route>{
        Node firstNode;
        Node secondNode;
        int distance;
        public Route(Node firstNode,Node secondNode,int distance){
            this.firstNode=firstNode;
            this.secondNode=secondNode;
            this.distance=distance;
        }
        @Override
        public int compareTo(Route route) {
            if(this.distance>route.distance){
                return 1;
            }else if(this.distance==route.distance){
                return 0;
            }
            return -1;
        }
        @Override
        public String toString(){
            return "第一个节点:"+firstNode.toString()+","+"第二个节点:"+secondNode.toString()+",权为"+distance;
        }
    }
}

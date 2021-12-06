package tests;

import datastructure.*;
import examples.Person;
import exception.DataSafetyException;
import utils.Generator;

/**
 * @author Wenfei Wang
 */
public class DataStructureTest {
    public DataStructureTest(){
    }
    public void linearListTest(){
        Generator generator=new Generator();
        LinearList<Person> list=new LinearList<Person>(10);
        for(int i=1;i<=10;i++){
            list.setEleAt(i,generator.getRandomPerson());
        }
        list.printAll();
    }
    public void linkedListTest() throws DataSafetyException {
        Generator generator=new Generator();
        LinkedList<Person> list=new LinkedList<Person>(generator.getRandomPerson());
        list.insert(1,generator.getRandomPerson());
        list.insert(2,generator.getRandomPerson());
        list.delete(1);
        list.append(generator.getRandomPerson());
        list.printAll();
        System.out.println("ROOT = " + list.getRootData());
        System.out.println("LENGTH = " + list.length());
    }
    public void stackTest() throws DataSafetyException {
        Generator generator=new Generator();
        Stack<Person> stack=new Stack<Person>(10);
        for(int i=0;i<10;i++){
            stack.push(generator.getRandomPerson());
        }
        stack.printAll();
        System.out.println("POPPED = " + stack.pop());
        stack.printAll();
        System.out.println("TOP = " + stack.top());
    }
    public void queueTest() throws DataSafetyException {
        Generator generator=new Generator();
        Queue<Person> queue=new Queue<Person>(10);
        for(int i=0;i<5;i++){
            queue.enQueue(generator.getRandomPerson());
        }
        queue.printAll();
        System.out.println("DEQUEUE = " + queue.deQueue());
        queue.printAll();
    }
    public void binaryTreeTest(){
        Generator generator=new Generator();
        BinaryTree<Person> binaryTree=new BinaryTree<Person>(generator.getRandomPerson());
        binaryTree.generateExample(generator.getRandomPerson());
        System.out.println(binaryTree.getDepth(binaryTree.getRoot()));
        System.out.println("-------------DFS-------------");
        binaryTree.dfs(binaryTree.getRoot());
        System.out.println("-------------BFS-------------");
        binaryTree.bfs(binaryTree.getRoot());
    }
    public void undirectedGraghTest() throws DataSafetyException {
        Generator generator=new Generator();
        Person[] people=new Person[10];
        for(int i=0;i<10;i++){
            people[i]=generator.getRandomPerson();
        }
        UndirectedGraph<Person> graph=new UndirectedGraph<>(people[0]);
        for(int i=1;i<6;i++) {
            graph.addNode(people[i]);
        }
        graph.linkNodes(1,2,1);
        graph.linkNodes(1,3,2);
        graph.linkNodes(2,3,6);
        graph.linkNodes(2,4,11);
        graph.linkNodes(3,4,9);
        graph.linkNodes(3,5,13);
        graph.linkNodes(4,5,7);
        graph.linkNodes(4,6,3);
        graph.linkNodes(5,6,4);
        graph.showMatrix();
        System.out.println("-------------DFS-------------");
        graph.dfs(1);
        System.out.println("-------------BFS-------------");
        graph.bfs(1);
        graph.printAll();
        System.out.println("-------------SPANNING TREE-------------");
        UndirectedGraph<Person> spanningTree=graph.getSpanningTree();
        spanningTree.printAll();
    }
}

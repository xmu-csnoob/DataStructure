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
        binaryTree.DFS(binaryTree.getRoot(),1);
    }
}
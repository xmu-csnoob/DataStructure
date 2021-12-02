package tests;

import datastructure.DataStructure;
import datastructure.LinearList;
import datastructure.LinkedList;
import datastructure.Stack;
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
}
package tests;

import datastructure.LinearList;
import datastructure.LinkedList;
import examples.Person;
import exception.DataSafetyException;
import utils.Generator;

/**
 * @author Wenfei Wang
 */
public class DataStructureTest {
    public void linearListTest(){
        LinearList<Person> list=new LinearList<Person>(10);
        Person person=new Person("Jane",10);
        System.out.println(list.setEleAt(1, person).getName());
        System.out.println(list.getEleAt(1).getName());
        System.out.println(list.searchValue(person));
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
}
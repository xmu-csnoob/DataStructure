package utils;

import examples.Person;

import java.util.Random;

/**
 * @author Wenfei Wang
 */
public class Generator {
    public Person getRandomPerson(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length=5;
        Random random=new Random();
        StringBuilder name=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(52);
            name.append(str.charAt(number));
        }
        int age= random.nextInt(100);
        return new Person(name.toString(),age);
    }
}

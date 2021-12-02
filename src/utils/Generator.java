package utils;

import examples.Person;

import java.util.Random;

/**
 * @author Wenfei Wang
 */
public class Generator {
    public Person getRandomPerson(){
        String str="薄雾浓云愁永昼瑞脑消金兽" +
                "佳节又重阳玉枕纱厨半夜凉初透" +
                "东篱把酒黄昏后有暗香盈袖" +
                "莫道不销魂帘卷西风人比黄花瘦";
        int length=2;
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

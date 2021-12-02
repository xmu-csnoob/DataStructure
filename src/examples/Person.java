package examples;

/**
 * @author Wenfei Wang
 */
public class Person{
    String name;
    int age;
    public Person(String name,int age){
        this.name=name;
        this.age=age;
    }
    public String getName(){
        return name;
    }
    public boolean equals(Person person){
        if(person==null){
            throw new NullPointerException("传入的参数不得为NULL");
        }
        return (person.name.equals(this.name))&&(person.age==this.age);
    }
    @Override
    public String toString(){
        return name+" "+age;
    }
}

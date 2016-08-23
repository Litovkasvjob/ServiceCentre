package ServiceCentreMVC.model;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 11:17
 */
public class Human {

    private String name;
    private int age;
    private double cash;

    public Human(String name, int age, double cash) {
        this.name = name;
        this.age = age;
        this.cash = cash;
    }

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return " {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cash=" + cash +
                '}';
    }
}

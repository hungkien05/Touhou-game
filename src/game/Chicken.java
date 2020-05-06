package game;

public class Chicken {
    // thuoc tinh
    double weight;
    String color;
    int age;
//    static int count;

    public  Chicken() {
//        count++;
        weight = 1;
        color = "yellow";
        age = 2;
    }

    public Chicken(double weight, String color, int age) {
        this.weight = weight;
        this.color = color;
        this.age = age;
    }
    // phuong thuc
     public void eat() {
        System.out.println("Eating..");
    }

    public void run() {
        System.out.println("Running...");
    }

    public void print() {
        System.out.println(this.color);
    }
}

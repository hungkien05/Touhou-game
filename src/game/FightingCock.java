package game;

public class FightingCock extends Chicken {
    //weight
    //age
    //color
    int damage;
    public FightingCock(double weight, String color, int age, int damage) {
        this.weight= weight;
        this.color = color;
        this.age = age;
        this.damage = damage;
    }

    // eat
    @Override
    public void eat() {
        super.eat(); //game.Chicken.eat()
        System.out.println("2x Eating...");
    }
    // run
}

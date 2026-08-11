package package4;

import java.util.ArrayList;
import java.util.List;

public class Package4Demo {
    public static void main(String[] args) {
        List<Animal> list = new ArrayList<>();
        list.add(new Cat());
        list.add(new Dog());
        list.add(new Cow());
        list.add(new Pig());

        for (Animal animal : list) {
            animal.makeSound();
        }
    }
}

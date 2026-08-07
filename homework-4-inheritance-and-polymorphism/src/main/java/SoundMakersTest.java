import packageSoundMakers.Bee;
import packageSoundMakers.Cat;
import packageSoundMakers.Cow;
import packageSoundMakers.Cricket;
import packageSoundMakers.Dog;
import packageSoundMakers.Frog;
import packageSoundMakers.Pig;
import packageSoundMakers.SoundMaker;

import java.util.ArrayList;

public class SoundMakersTest {
    public static void main(String[] args) {
        ArrayList<SoundMaker> list = new ArrayList<>();
        list.add(new Cat());
        list.add(new Dog());
        list.add(new Cow());
        list.add(new Pig());
        list.add(new Bee());
        list.add(new Cricket());
        list.add(new Frog());

        for (SoundMaker maker : list) {
            maker.makeSound();
        }
    }
}

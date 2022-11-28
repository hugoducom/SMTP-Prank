package ch.heigvd.dai.mailrobot.model.mail;

import java.util.ArrayList;
import java.util.Collections;

public class Group {
    private final ArrayList<Person> members = new ArrayList<>();

    public void addMember(Person person) {
        members.add(person);
    }

    public ArrayList<Person> getMembers() {
        return new ArrayList<>(members);
    }

    public void suffleMembers() {
        Collections.shuffle(members);
    }
}

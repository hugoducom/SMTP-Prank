package ch.heigvd.dai.mailrobot.model.mail;

import java.util.ArrayList;

public class Group {

    private final ArrayList<Person> members = new ArrayList<>();

    public void addMember(Person person) {
        members.add(person);
    }

    public ArrayList<Person> getMembers() {
        return new ArrayList<>(members);
    }
}

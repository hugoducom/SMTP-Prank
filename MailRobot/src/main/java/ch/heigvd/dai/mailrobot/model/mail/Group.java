package ch.heigvd.dai.mailrobot.model.mail;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe qui représente un groupe de personnes qui va recevoir un mail de spam
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class Group {
    private final ArrayList<Person> members = new ArrayList<>();

    /**
     * Ajoute un membre au groupe
     * @param person
     */
    public void addMember(Person person) {
        members.add(person);
    }

    /**
     * Getter de la liste des membres du groupe
     * @return
     */
    public ArrayList<Person> getMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Mélange les membres dans un ordre aléatoire
     */
    public void suffleMembers() {
        Collections.shuffle(members);
    }
}

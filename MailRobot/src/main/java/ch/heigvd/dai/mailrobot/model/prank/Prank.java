package ch.heigvd.dai.mailrobot.model.prank;

import ch.heigvd.dai.mailrobot.model.mail.*;
import java.util.ArrayList;

public class Prank {

    private Person victimSender;
    private final ArrayList<Person> victimRecipients = new ArrayList<>();
    private final ArrayList<Person> witnessRecipients = new ArrayList<>();
    private String message;

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public ArrayList<Person> getVictimRecipients() {
        return new ArrayList(victimRecipients);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Person> getWitnessRecipients() {
        return new ArrayList(witnessRecipients);
    }

    public String getMessage() {
        return message;
    }

    public void addVictimRecipients(ArrayList<Person> victims) {
        victimRecipients.addAll(victims);
    }

    public void addWitnessRecipients(ArrayList<Person> witness) {
        victimRecipients.addAll(witness);
    }

    // TODO ; FINIR DE CREER LE PRANK
}

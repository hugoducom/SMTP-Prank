package ch.heigvd.dai.mailrobot.model.prank;

import ch.heigvd.dai.mailrobot.model.mail.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Prank {

    private Person victimSender;
    private final ArrayList<Person> victimRecipients = new ArrayList<>();
    private String message;

    public Prank(Group group, String s) {
        this.victimSender = group.getMembers().get(0);
        for(int i = 1; i < group.getMembers().size(); ++i){
            victimRecipients.add(group.getMembers().get(i));
        }
        this.message = s;
    }

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

    public String getMessage() {
        return message;
    }

    public void addVictimRecipients(ArrayList<Person> victims) {
        victimRecipients.addAll(victims);
    }

    public void addWitnessRecipients(ArrayList<Person> witness) {
        victimRecipients.addAll(witness);
    }

    public Message generateMailMessage() {
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" + victimSender.getFirstname());

        String[] to = victimRecipients.stream().map(p -> p.getAddress()).collect(Collectors.toList()).toArray(new String[]{});
        msg.setTo(to);

        msg.setFrom(victimSender.getAddress());
        return msg;
    }
}

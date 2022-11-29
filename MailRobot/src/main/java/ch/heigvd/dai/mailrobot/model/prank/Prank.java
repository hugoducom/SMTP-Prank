package ch.heigvd.dai.mailrobot.model.prank;

import ch.heigvd.dai.mailrobot.model.mail.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Prank {

    private final Person victimSender;
    private final ArrayList<Person> victimRecipients = new ArrayList<>();
    private final Message message;

    public Prank(Group group, Message m) {
        this.victimSender = group.getMembers().get(0);
        for(int i = 1; i < group.getMembers().size(); ++i){
            victimRecipients.add(group.getMembers().get(i));
        }
        this.message = m;
    }

    public Message generateMailMessage() {

        message.setBody(message.getBody() + "\r\n" + victimSender.getFirstname() + " " + victimSender.getName());

        String[] to = victimRecipients.stream().map(p -> p.getAddress()).collect(Collectors.toList()).toArray(new String[]{});
        message.setTo(to);

        message.setFrom(victimSender.getAddress());
        return message;
    }
}

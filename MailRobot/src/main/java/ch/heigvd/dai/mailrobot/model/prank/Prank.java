package ch.heigvd.dai.mailrobot.model.prank;

import ch.heigvd.dai.mailrobot.model.mail.*;
import java.util.stream.Collectors;

/**
 * Classe représentant un prank contenant un mail de spam (texte), un groupe à qui envoyer
 * et un victimSender qui sera l'expéditeur du mail.
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class Prank {
    // Expéditeur du mail (une victime)
    private final Person victimSender;
    // Destinataires du mail de prank
    private final Group victimRecipients;
    private final Message message;

    /**
     * Constructeur
     * @param group Groupe auquel on envoie le prank
     * @param message Mail à envoyer
     */
    public Prank(Group group, Message message) {
        victimRecipients = new Group();
        this.victimSender = group.getMembers().get(0);
        for(int i = 1; i < group.getMembers().size(); ++i){
            victimRecipients.addMember(group.getMembers().get(i));
        }
        this.message = message;
    }

    /**
     * Popule les attributs 'to', 'body' et 'from' du message envoyé.
     * @return Message final à envoyer
     */
    public Message generateMailMessage() {

        message.setBody(message.getBody() + "\r\n" + victimSender.getFirstname() + " " + victimSender.getName());

        String[] to = victimRecipients.getMembers().stream().map(p -> p.getAddress()).collect(Collectors.toList()).toArray(new String[]{});
        message.setTo(to);

        message.setFrom(victimSender.getAddress());
        return message;
    }
}

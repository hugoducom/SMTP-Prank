package ch.heigvd.dai.mailrobot.model.mail;

/**
 * Classe représentant un message à envoyer (texte du spam)
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class Message {

    private String from;
    private String[] to = new String[0];
    private final String subject;
    private String body;

    /**
     * Constructeur
     * @param subject Sujet d'un message
     * @param body Corps du message
     */
    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Getter de l'adresse 'From'
     * @return Adresse de l'expéditeur
     */
    public String getFrom() {
        return from;
    }

    /**
     * Setter de l'adresse 'From'
     * @param from Adresse de l'expéditeur
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Getter de l'adresse 'To'
     * @return Adresses des destinataires
     */
    public String[] getTo() {
        return to;
    }

    /**
     * Setter de l'adresse 'To'
     * @param to Adresses des destinataires
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * Getter du sujet
     * @return Sujet du mail
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter du texte body
     * @return Corps du mail
     */
    public String getBody() {
        return body;
    }

    /**
     * Setter du texte body
     * @param body Corps du mail
     */
    public void setBody(String body) {
        this.body = body;
    }
}

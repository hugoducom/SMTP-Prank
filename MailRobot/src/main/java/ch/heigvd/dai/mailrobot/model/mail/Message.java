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
     * @param subject
     * @param body
     */
    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Getter de l'adresse 'From'
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     * Setter de l'adresse 'From'
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Getter de l'adresse 'To'
     * @return
     */
    public String[] getTo() {
        return to;
    }

    /**
     * Setter de l'adresse 'To'
     * @param to
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * Getter du sujet
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter du texte body
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * Setter du texte body
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }
}

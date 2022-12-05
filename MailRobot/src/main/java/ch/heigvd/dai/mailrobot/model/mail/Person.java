package ch.heigvd.dai.mailrobot.model.mail;

/**
 * Classe représentant une personne qui va se faire pranker
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class Person {

    private final String mail;

    /**
     * Constructeur
     * @param mail Mail sous la forme
     */
    public Person(String mail) {
        this.mail = mail;
    }

    /**
     * Getter de l'adresse mail
     * @return Adresse mail
     */
    public String getAddress() {
        return mail;
    }
}

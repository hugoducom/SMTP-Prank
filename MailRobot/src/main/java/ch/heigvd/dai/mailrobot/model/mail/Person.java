package ch.heigvd.dai.mailrobot.model.mail;

/**
 * Classe représentant une personne qui va se faire pranker
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class Person {

    private final String mail;
    private final String firstname;
    private final String name;

    /**
     * Constructeur
     * @param mail Mail sous la forme (prenom.nom@domain.tld)
     */
    public Person(String mail) {

        this.mail = mail;

        String[] splitNameDomain = mail.split("@");
        String[] splitNameFirstname = splitNameDomain[0].split("\\.");

        this.firstname = splitNameFirstname[0];
        this.name = splitNameFirstname[1];
    }

    /**
     * Getter du prénom
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Getter du nom
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter du mail
     * @return
     */
    public String getAddress() {
        return mail;
    }
}

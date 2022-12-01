package ch.heigvd.dai.mailrobot.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe de vérification des adresses mails
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class MailChecker {

    // Regex représentant notre adresse
    private static final String EMAIL_PATTERN = "^([A-Za-z0-9]+)\\.([A-Za-z0-9]+)@([A-Za-z0-9]+)\\.([A-Za-z0-9]+)$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Vérifie la validité d'un string de mail
     * @param mail Adresse mail que l'on souhaite vérifier
     * @return Validité du mail (boolean)
     */
    public boolean checkMail(String mail) {
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
}

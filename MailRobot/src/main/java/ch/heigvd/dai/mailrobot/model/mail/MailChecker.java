package ch.heigvd.dai.mailrobot.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailChecker {

    private static final String EMAIL_PATTERN = "^([A-Za-z0-9]+)\\.([A-Za-z0-9]+)@([A-Za-z0-9]+)\\.([A-Za-z0-9]{2,6})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean checkMail(String mail) {
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
}

package ch.heigvd.dai.mailrobot.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private  String mail;
    private String firstname;
    private String name;

    public Person(String mail, String firstname, String name) {
        this.mail = mail;
        this.firstname = firstname;
        this.name = name;
    }

    public Person(String mail) {

        if(!isValid(mail)) {
            throw new RuntimeException("Invalid email");
        }

        this.mail = mail;

        String[] splitNameDomain = mail.split("@");
        String[] splitNameFirstname = splitNameDomain[0].split("\\.");

        this.firstname = splitNameFirstname[0];
        this.name = splitNameFirstname[1];
    }

    public String getFirstname() {
        return firstname;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return mail;
    }

    public static boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

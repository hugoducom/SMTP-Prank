package ch.heigvd.dai.mailrobot.model.mail;

public class Person {

    private  String mail;
    private String firstname;
    private String name;

    public Person(String mail, String firstname, String name) {
        this.mail = mail;
        this.firstname = firstname;
        this.name = name;
    }

    public Person(String mail) {

        this.mail = mail;

        String[] splitNameDomain = mail.split("@");
        String[] splitNameFirstname = splitNameDomain[0].split(".");

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
}

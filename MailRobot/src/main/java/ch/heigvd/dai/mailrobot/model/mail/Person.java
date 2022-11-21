package ch.heigvd.dai.mailrobot.model.mail;

public class Person {

    private  String mail;

    public Person(String mail) {
        if (mail != null)
            this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}

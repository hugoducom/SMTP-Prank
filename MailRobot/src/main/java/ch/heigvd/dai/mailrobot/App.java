package ch.heigvd.dai.mailrobot;

import ch.heigvd.dai.mailrobot.model.mail.Message;
import ch.heigvd.dai.mailrobot.smtp.SmtpClient;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SmtpClient client = new SmtpClient("localhost", 25000);
    }
}

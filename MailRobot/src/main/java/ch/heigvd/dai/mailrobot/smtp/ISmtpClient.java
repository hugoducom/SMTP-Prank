package ch.heigvd.dai.mailrobot.smtp;

import ch.heigvd.dai.mailrobot.model.mail.Message;

import java.io.IOException;

/**
 * Interface d'un client SMTP
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public interface ISmtpClient {
    void sendMessage(Message message) throws IOException;
}

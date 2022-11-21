package ch.heigvd.dai.mailrobot.smtp;

import ch.heigvd.dai.mailrobot.model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {
    void sendMessage(Message message) throws IOException;
}

package ch.heigvd.dai.mailrobot.smtp;

import ch.heigvd.dai.mailrobot.model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String smtpServerAddress;
    private final int smtpServerPort;

    public SmtpClient(String smtpServerAddress, int port) {
        this.smtpServerAddress = smtpServerAddress;
        smtpServerPort = port;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        LOG.info("Sending message via SMTP");
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String line = reader.readLine();
        LOG.info(line);
        writer.printf("EHLO localhost\r\n");
        line = reader.readLine();
        LOG.info(line);
        if (!line.startsWith("250")) {
            throw new IOException("SMTP error: " + line);
        }
        while (line.startsWith("250-")) {
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("MAIL FROM:");
        writer.write(message.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        for (String to : message.getTo()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for (String cc : message.getCc()) {
            writer.write("RCPT TO:");
            writer.write(cc);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for (String bcc : message.getBcc()) {
            writer.write("RCPT TO:");
            writer.write(bcc);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");
        writer.write("From: " + message.getFrom() + "\r\n");

        writer.write("To: " + message.getTo()[0]);
        for (int i = 1; i < message.getTo().length; ++i) {
            writer.write(", " + message.getTo()[i]);
        }
        writer.write("\r\n");

        writer.flush();
        //message.setBody("Subject: toto\r\n\r\nHello world.");
        LOG.info(message.getBody());
        writer.write(message.getBody());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("QUIT\r\n");
        writer.flush();
        writer.close();
        reader.close();
        socket.close();
    }
}

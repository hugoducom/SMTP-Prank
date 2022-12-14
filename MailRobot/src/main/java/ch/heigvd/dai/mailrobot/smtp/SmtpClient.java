package ch.heigvd.dai.mailrobot.smtp;

import ch.heigvd.dai.mailrobot.model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Client SMTP
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class SmtpClient implements ISmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private PrintWriter writer;
    private BufferedReader reader;
    private final String smtpServerAddress;
    private final int smtpServerPort;

    /**
     * Constructeur du client SMTP
     * @param smtpServerAddress Adresse du serveur SMTP à contacter
     * @param port Port du serveur SMTP
     */
    public SmtpClient(String smtpServerAddress, int port) {
        this.smtpServerAddress = smtpServerAddress;
        smtpServerPort = port;
    }

    /**
     * Lit les headers reçus par le serveur et les affiche en console
     * @throws IOException Si une erreur d'entrée/sortie survient
     */
    private void readHeaderFromServer() throws IOException
    {
        String line = reader.readLine();
        if (!line.startsWith("250")) {
            throw new IOException("SMTP error: " + line);
        }
        while (line.startsWith("250-")) {
            line = reader.readLine();
            LOG.info(line);
        }
    }

    /**
     * Lit une ligne reçue par le serveur et l'affiche en console.
     * @throws IOException Si la ligne reçue ne commence pas par 250
     */
    private void readFromServer() throws IOException {
        String line = reader.readLine();
        LOG.info(line);
    }

    /**
     * Ecrit une ligne au serveur
     * @param line Ligne à écrire au serveur
     * @throws IOException Si une erreur d'entrée/sortie survient
     */
    private void writeToServer(String line) throws IOException {
        writer.write(line + "\r\n");
        writer.flush();
    }

    /**
     * Envoie un mail de type Message au serveur.
     * Le serveur utilise les attributs 'To' et 'From' du message pour savoir à qui adresser le mail.
     * Le mail est encodé en UTF-8.
     * @param message Message à envoyer
     * @throws IOException Si une erreur survient lors de l'envoi du mail
     */
    @Override
    public void sendMessage(Message message) throws IOException {

        LOG.info("Sending message via SMTP");
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String line;

        readFromServer();

        // EHLO
        writeToServer("EHLO localhost");

        readHeaderFromServer();

        // MAIL FROM
        writeToServer("MAIL FROM:"+message.getFrom());

        readFromServer();

        // RCPT TO
        for (String to : message.getTo()) {
            writeToServer("RCPT TO:" + to);
            line = reader.readLine();
            LOG.info(line);
        }

        // START DATA
        writeToServer("DATA");

        readFromServer();

        writeToServer("Content-Type: text/plain; charset=utf-8");
        writeToServer("From: " + message.getFrom());

        writer.write("To: " + message.getTo()[0]);
        for (int i = 1; i < message.getTo().length; ++i) {
            writer.write(", " + message.getTo()[i]);
        }
        writer.write("\r\n");
        writer.flush();

        LOG.info("Subject: " + message.getBody());
        writeToServer("Subject: =?utf8?B?" + Base64.getEncoder().encodeToString(message.getSubject().getBytes(StandardCharsets.UTF_8)) + "?=");

        writer.write("\r\n");

        LOG.info(message.getBody());

        writeToServer(message.getBody());
        writeToServer(".");

        // END DATA

        readFromServer();

        writeToServer("QUIT");

        writer.close();
        reader.close();
        socket.close();
    }
}

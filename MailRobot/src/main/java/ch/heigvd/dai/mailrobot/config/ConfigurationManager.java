package ch.heigvd.dai.mailrobot.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ch.heigvd.dai.mailrobot.model.mail.MailChecker;
import ch.heigvd.dai.mailrobot.model.mail.Message;
import ch.heigvd.dai.mailrobot.model.mail.Person;

/**
 * Classe qui gère l'interface entre le programme et les fichiers de config
 * situés dans /config/ à la racine du projet
 *
 * @author Hugo Ducommun
 * @author Alexis Martins
 */
public class ConfigurationManager {
    // Personne minimum par groupe
    public static final int MIN_SIZE_PER_GROUP = 3;
    // Séparateur entre les mails dans le fichier messages.utf8
    private static final String SEPARATOR = "---";
    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<Message> messages;
    private int numberOfGroups;

    /**
     * Constructeur par défaut
     * @throws Exception
     */
    public ConfigurationManager() throws Exception {
        this.victims = loadAddresses("./MailRobot/config/victims.utf8");
        this.messages = loadMessages("./MailRobot/config/messages.utf8");
        loadProperties("./MailRobot/config/config.properties");
    }

    /**
     * Getter des victimes
     * @return
     */
    public List<Person> getVictims() {
        return victims;
    }

    /**
     * Getter des messages
     * @return
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Getter de l'adresse du serveur SMTP
     * @return
     */
    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    /**
     * Getter du port du serveur SMTP
     * @return
     */
    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    /**
     * Getter du nombre de groupe
     * @return
     */
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Charge les propriétés stockées dans le fichier config.properties
     * (smtpServerAddress, smtpServerPort, numberOfGroups)
     * @param file
     * @throws IOException
     */
    // https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html
    private void loadProperties(String file) throws IOException {
        FileInputStream fs = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fs, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        Properties prop = new Properties();
        prop.load(reader);
        smtpServerAddress = prop.getProperty("smtpServerAddress");
        smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
    }

    /**
     * Charge les messages depuis le fichier messages.utf8
     * @param file
     * @return
     * @throws IOException
     */
    private List<Message> loadMessages(String file) throws IOException {
        List<Message> res = new ArrayList<>();
        String line = null;
        String subject = null;
        StringBuilder body = new StringBuilder();

        try (FileInputStream fs = new FileInputStream(file)) {
            InputStreamReader is = new InputStreamReader(fs, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(is)) {

                /*
                 * Lecture du contenu du fichier messages ligne par ligne.
                 * On commence par lire Subject qui est la première ligne d'un mail
                 * Ensuite on saute la ligne vide qui sépare sujet et body
                 * Finalement on peut lire le corps du message jusqu'à tomber sur le séparateur.
                 */
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Subject:")) {
                        // 9 étant la case du string à laquelle commence le message
                        subject = line.substring(9);
                    } else if (line.equals("")) {
                        continue;
                    } else if(!line.equals(SEPARATOR)) {
                        body.append(line).append("\n");
                    }
                    else {
                        res.add(new Message(subject, body.toString()));
                        // Clear du StringBuilder
                        body.setLength(0);
                    }
                }
            }
        }
        checkNumberOfMessages(res);
        return res;
    }

    /**
     * Charge les adresses mail des victimes depuis victims.utf8
     * @param file
     * @return
     * @throws IOException
     */
    private List<Person> loadAddresses(String file) throws IOException {
        List<Person> res = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream(file)) {
            InputStreamReader is = new InputStreamReader(fs, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(is)) {
                String address = reader.readLine();
                while (address != null) {
                    addressCheck(address);
                    res.add(new Person(address));
                    address = reader.readLine();

                }
            }
        }
        return res;
    }

    /**
     * Vérifie la validité des adresses mails
     * @param address
     */
    private void addressCheck(String address) {

        /*
         * Utilisation du mailChecker pour vérifier que les adresses correspondent
         * bien aux adresses mails que notre programme prend en paramètres
         */
        MailChecker mc = new MailChecker();
        if (!mc.checkMail(address)) {
            throw new RuntimeException("Invalid email");
        }
    }

    /**
     * Vérifie que des messages soient chargés
     * @param m
     */
    private void checkNumberOfMessages(List<Message> m) {
        if(m.size() == 0) {
            throw new RuntimeException("Add messages to your config");
        }
    }

}

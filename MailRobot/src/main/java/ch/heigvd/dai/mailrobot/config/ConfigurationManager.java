package ch.heigvd.dai.mailrobot.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ch.heigvd.dai.mailrobot.model.mail.Person;

public class ConfigurationManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOfGroups;
    private final List<Person> witnessesToCC;

    public ConfigurationManager() throws Exception {
        this.victims = loadAddresses("./config/victims.utf8");
        this.messages = loadMessages("./config/messages.utf8");
        this.witnessesToCC = new ArrayList<>();
        loadProperties("./config/config.properties");
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public List<Person> getWitnessesToCC() {
        return witnessesToCC;
    }

    // https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html
    private void loadProperties(String file) throws IOException {
        FileInputStream fs = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fs);
        BufferedReader reader = new BufferedReader(isr);
        Properties prop = new Properties();
        prop.load(reader);
        smtpServerAddress = prop.getProperty("smtpServerAddress");
        smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
        String witnesses = prop.getProperty("witnessToCC");
        for (String addr : witnesses.split(",")) {
            witnessesToCC.add(new Person(addr));
        }
    }

    private List<String> loadMessages(String file) throws IOException {
        List<String> res = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream(file)) {
            InputStreamReader is = new InputStreamReader(fs, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(is)) {
                String line = reader.readLine();
                while (line != null) {
                    // Create the mail
                    StringBuilder msg = new StringBuilder();
                    while (line != null && (!line.equals("---"))) {
                        msg.append(line);
                        msg.append("\r\n");
                        line = reader.readLine();
                    }
                    // Add the mail to the result
                    res.add(msg.toString());
                    line = reader.readLine();
                }

            }
        }
        return res;
    }

    private List<Person> loadAddresses(String file) throws IOException {
        List<Person> res = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream(file)) {
            InputStreamReader is = new InputStreamReader(fs, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(is)) {
                String address = reader.readLine();
                while (address != null) {
                    res.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return res;
    }


}

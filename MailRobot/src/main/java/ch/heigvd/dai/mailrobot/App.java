package ch.heigvd.dai.mailrobot;

import ch.heigvd.dai.mailrobot.config.ConfigurationManager;
import ch.heigvd.dai.mailrobot.model.prank.Prank;
import ch.heigvd.dai.mailrobot.model.prank.PrankGenerator;
import ch.heigvd.dai.mailrobot.smtp.SmtpClient;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        try {
            LOG.log(Level.INFO, "Beginning of the program.");
            final ConfigurationManager cm = new ConfigurationManager();
            final SmtpClient client = new SmtpClient(cm.getSmtpServerAddress(), cm.getSmtpServerPort());
            final PrankGenerator prankGenerator = new PrankGenerator(cm);
            final List<Prank> pranks = prankGenerator.generatePranks();
            LOG.log(Level.INFO, "Sending mails pranks");
            for (Prank p : pranks) {
                client.sendMessage(p.generateMailMessage());
            }
            LOG.log(Level.INFO, "End of the program.");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }
}

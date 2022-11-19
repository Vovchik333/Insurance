package loggers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.*;

public class InsureLog {
    private static final String username = PasswordAndEmail.getEmail();
    private static final String password = PasswordAndEmail.getPassword();

    public static void configureLogger(Logger logger){
        try {
            logger.setUseParentHandlers(false);
            Handler handlerFile = new FileHandler("./insure_logs");
            Handler handlerMail = InsureLog.HandlerForMail();
            handlerFile.setLevel(Level.ALL);
            handlerMail.setLevel(Level.SEVERE);
            Formatter formatter = new SimpleFormatter();
            handlerFile.setFormatter(formatter);
            handlerMail.setFormatter(formatter);
            logger.addHandler(handlerFile);
            logger.addHandler(handlerMail);
            logger.info("logger successfully configured.");
        }catch (IOException e){
            logger.warning(e.getMessage());
        }
    }

    private static Handler HandlerForMail(){
        return new Handler() {
            @Override
            public void publish(LogRecord record) {
                if(record.getLevel() == Level.SEVERE)
                    sendMail(record.getMessage());
            }

            @Override
            public void flush() {}

            @Override
            public void close() throws SecurityException {

            }
        };
    }

    public static void sendMail(String logMessage) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("volodia432@ukr.net")
            );
            message.setSubject("Error");
            message.setText(logMessage);
            Transport.send(message);
        } catch (MessagingException e) {
            Logger.getGlobal().warning("Failed to send email.");
        }
    }
}


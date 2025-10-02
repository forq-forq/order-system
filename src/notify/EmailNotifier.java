package notify;

public class EmailNotifier implements Notifier {
    @Override
    public void notify(String recipient, String subject, String message) {
        System.out.println("[EMAIL -> " + recipient + "] " + subject + "\n" + message);
    }
}

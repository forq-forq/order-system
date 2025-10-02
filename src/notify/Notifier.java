package notify;

public interface Notifier {
    void notify(String recipient, String subject, String message); // send notification
}

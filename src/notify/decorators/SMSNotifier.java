package notify.decorators;

import notify.Notifier;

public class SMSNotifier extends NotifierDecorator {
    public SMSNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override 
    public void notify(String recipient, String subject, String message) {
        super.notify(recipient, subject, message);
        System.out.println("[SMS -> " + recipient + "] " + subject + "\n" + message);
    }
}

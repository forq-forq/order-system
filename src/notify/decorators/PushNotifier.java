package notify.decorators;

import notify.Notifier;

public class PushNotifier extends NotifierDecorator {
    public PushNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override 
    public void notify(String recipient, String subject, String message) {
        super.notify(recipient, subject, message);
        System.out.println("[PUSH -> " + recipient + "] " + subject + "\n" + message);
    }
}

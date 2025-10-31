package notify.decorators;

import notify.Notifier;

public class LoggingNotifier extends NotifierDecorator {
    public LoggingNotifier(Notifier wrappee) { 
        super(wrappee); 
    }

    @Override 
    public void notify(String recipient, String subject, String message) {
        System.out.println("[LOG] Notifying '" + recipient + "' about '" + subject + "'");
        super.notify(recipient, subject, message);
    }
}


package notify.decorators;

import notify.Notifier;

public abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void notify(String recipient, String subject, String message) {
        wrappee.notify(recipient, subject, message);
    }
}

package Services.BuilderPattern;

import java.time.LocalDate;

import NotifySystem.Notification;
import UserSystem.User;

public class NotificationBuilder {

    private Notification notification;

    private NotificationBuilder() {
        notification = new Notification();
    }

    public static NotificationBuilder create() {
        return new NotificationBuilder();
    }

    public NotificationBuilder message(String msg) {
        notification.setMesaj(msg);
        return this;
    }

    public NotificationBuilder sender(String sender) {
        notification.setSender(sender);
        return this;
    }

    public NotificationBuilder receiver(User user) {
        notification.setReceiever(user);
        return this;
    }

    public NotificationBuilder now() {
        notification.setTarih(LocalDate.now());
        return this;
    }

    public Notification build() {
        return notification;
    }
}

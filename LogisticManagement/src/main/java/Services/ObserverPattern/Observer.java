package Services.ObserverPattern;

import NotifySystem.Notification;
import UserSystem.User;

public interface Observer {
    void update(Notification notification);
    User getUser();
    long getId();
}

package notification.functional;

import notification.model.Notification;

@FunctionalInterface
public interface NotificationSender {

    void send(Notification notification);
}

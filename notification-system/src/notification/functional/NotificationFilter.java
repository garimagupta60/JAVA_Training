package notification.functional;

import notification.model.Notification;

@FunctionalInterface
public interface NotificationFilter {

    boolean shouldProcess(Notification notification);
}

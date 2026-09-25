package notification.functional;

import notification.model.Notification;

@FunctionalInterface
public interface NotificationTransformer {

    Notification transform(Notification notification);
}

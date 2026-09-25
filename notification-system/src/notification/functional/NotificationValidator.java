package notification.functional;

import notification.model.Notification;

@FunctionalInterface
public interface NotificationValidator {

    boolean validate(Notification notification);

    default NotificationValidator and(NotificationValidator other) {

        return notification ->
                this.validate(notification)
                        && other.validate(notification);
    }
}

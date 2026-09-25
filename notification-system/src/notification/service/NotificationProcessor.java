package notification.service;

import notification.functional.NotificationSender;
import notification.functional.NotificationTransformer;
import notification.functional.NotificationValidator;
import notification.model.Notification;

public class NotificationProcessor {

    private NotificationValidator validator;
    private NotificationTransformer transformer;
    private NotificationSender sender;

    public NotificationProcessor(
            NotificationValidator validator,
            NotificationTransformer transformer,
            NotificationSender sender) {

        this.validator = validator;
        this.transformer = transformer;
        this.sender = sender;
    }

    public void process(Notification notification) {

        boolean isValid = validator.validate(notification);

        if (!isValid) {
            System.out.println("Notification is invalid.");
            return;
        }

        Notification transformedNotification =
                transformer.transform(notification);

        sender.send(transformedNotification);
    }
}

package notification.service;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import notification.model.Notification;

public class NotificationProcessor {

    private Predicate<Notification> validator;
    private UnaryOperator<Notification> transformer;
    private Consumer<Notification> sender;

    public NotificationProcessor(
            Predicate<Notification> validator,
            UnaryOperator<Notification> transformer,
            Consumer<Notification> sender) {

        this.validator = validator;
        this.transformer = transformer;
        this.sender = sender;
    }

    public void process(Notification notification) {

        boolean isValid = validator.test(notification);

        if (!isValid) {
            System.out.println("Notification is invalid.");
            return;
        }

        Notification transformedNotification =
                transformer.apply(notification);

        sender.accept(transformedNotification);
    }
}

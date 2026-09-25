package notification;

import java.util.HashMap;
import java.util.Map;
import notification.functional.Converter;
import notification.functional.NotificationSender;
import notification.functional.NotificationTransformer;
import notification.functional.NotificationValidator;
import notification.model.Notification;
import notification.model.NotificationType;
import notification.service.NotificationProcessor;

public class Main {

        private static final String IMPORTANT_PREFIX = "IMPORTANT: ";

        public static void main(String[] args) {

                Notification notification = new Notification(
                                "U101",
                                "user@example.com",
                                null,
                                "Your order has been shipped.",
                                NotificationType.EMAIL,
                                3);

                System.out.println(notification);

                NotificationValidator validMessage = value -> value.getMessage() != null
                                && !value.getMessage().isBlank();

                NotificationValidator validPriority = value -> value.getPriority() >= 1
                                && value.getPriority() <= 5;

                NotificationValidator validEmail = value -> {

                        if (value.getType() != NotificationType.EMAIL) {
                                return true;
                        }

                        if (value.getEmail() == null
                                        || value.getEmail().isBlank()) {
                                return false;
                        }

                        return true;
                };

                NotificationValidator validPhone = value -> {

                        if (value.getType() != NotificationType.SMS) {
                                return true;
                        }

                        if (value.getPhone() == null
                                        || value.getPhone().isBlank()) {
                                return false;
                        }

                        return true;
                };

                NotificationValidator validUserId = value -> value.getUserId() != null
                                && !value.getUserId().isBlank();

                NotificationValidator allValidations = validMessage
                                .and(validPriority)
                                .and(validEmail)
                                .and(validPhone)
                                .and(validUserId);

                System.out.println(
                                "Valid message: "
                                                + validMessage.validate(notification));

                System.out.println(
                                "Valid priority: "
                                                + validPriority.validate(notification));

                System.out.println(
                                "Valid email: "
                                                + validEmail.validate(notification));

                System.out.println(
                                "Valid phone: "
                                                + validPhone.validate(notification));

                System.out.println(
                                "Valid userId: "
                                                + validUserId.validate(notification));

                NotificationTransformer trimMessage = value -> {
                        String message = value.getMessage();
                        if (message != null) {
                                String trimmedMessage = message.trim();
                                value.setMessage(trimmedMessage);
                        }
                        return value;
                };

                NotificationTransformer uppercaseMessage = value -> {
                        String message = value.getMessage();
                        if (message != null) {
                                String uppercase = message.toUpperCase();
                                value.setMessage(uppercase);
                        }
                        return value;
                };

                NotificationTransformer importantMessage = value -> {
                        if (value.getPriority() == 5) {
                                String message = value.getMessage();
                                String important = IMPORTANT_PREFIX + message;
                                value.setMessage(important);
                        }
                        return value;
                };

                NotificationTransformer maskEmail = value -> {
                        String email = value.getEmail();
                        if (email != null && email.contains("@")) {
                                int atIndex = email.indexOf("@");
                                String username = email.substring(0, atIndex);
                                String domain = email.substring(atIndex);

                                if (username.length() > 1) {
                                        String maskedPart = "*".repeat(username.length() - 1);
                                        String maskedEmail = username.charAt(0) + maskedPart + domain;
                                        value.setEmail(maskedEmail);
                                }
                        }
                        return value;
                };

                System.out.println("\n--- Testing Transformations ---");

                Notification trimTest = new Notification(
                                "U102",
                                "pranav@gmail.com",
                                null,
                                "   Hello World   ",
                                NotificationType.EMAIL,
                                3);

                System.out.println("Before Trim: " + trimTest.getMessage());

                trimTest = trimMessage.transform(trimTest);

                System.out.println("After Trim: " + trimTest.getMessage());

                Notification uppercaseTest = new Notification(
                                "U103",
                                "garima@gmail.com",
                                null,
                                "Hello World",
                                NotificationType.EMAIL,
                                3);

                System.out.println("\nBefore Uppercase: " + uppercaseTest.getMessage());

                uppercaseTest = uppercaseMessage.transform(uppercaseTest);

                System.out.println("After Uppercase: " + uppercaseTest.getMessage());

                Notification importantTest = new Notification(
                                "U104",
                                "user@gmail.com",
                                null,
                                "Server is down",
                                NotificationType.EMAIL,
                                5);

                System.out.println("\nBefore Important: " + importantTest.getMessage());

                importantTest = importantMessage.transform(importantTest);

                System.out.println("After Important: " + importantTest.getMessage());

                Notification maskEmailTest = new Notification(
                                "U105",
                                "pranav@gmail.com",
                                null,
                                "Hello",
                                NotificationType.EMAIL,
                                3);

                System.out.println("\nBefore Mask Email: " + maskEmailTest.getEmail());

                maskEmailTest = maskEmail.transform(maskEmailTest);

                System.out.println("After Mask Email: " + maskEmailTest.getEmail());

                NotificationSender emailSender = value -> {
                        System.out.println("EMAIL sent to " + value.getEmail());
                        System.out.println("Message: " + value.getMessage());
                };

                NotificationSender smsSender = value -> {
                        System.out.println("SMS sent to " + value.getPhone());
                        System.out.println("Message: " + value.getMessage());
                };

                NotificationSender pushSender = value -> {
                        System.out.println("PUSH notification sent to user " + value.getUserId());
                        System.out.println("Message: " + value.getMessage());
                };

                System.out.println("\n--- Testing Senders ---");
                Notification emailNotification = new Notification(
                                "U106",
                                "user@example.com",
                                null,
                                "Your order has been shipped.",
                                NotificationType.EMAIL,
                                3);

                emailSender.send(emailNotification);

                Notification smsNotification = new Notification(
                                "U107",
                                null,
                                "9876543210",
                                "Your OTP is 1234.",
                                NotificationType.SMS,
                                4);

                smsSender.send(smsNotification);

                Notification pushNotification = new Notification(
                                "U108",
                                null,
                                null,
                                "You have a new notification.",
                                NotificationType.PUSH,
                                2);

                pushSender.send(pushNotification);

                Map<NotificationType, NotificationSender> senderMap = new HashMap<>();

                senderMap.put(NotificationType.EMAIL, emailSender);
                senderMap.put(NotificationType.SMS, smsSender);
                senderMap.put(NotificationType.PUSH, pushSender);

                System.out.println("\n--- Testing Routing ---");

                NotificationSender emailRoutedSender = senderMap.get(emailNotification.getType());
                if (emailRoutedSender != null) {
                        emailRoutedSender.send(emailNotification);
                }

                System.out.println();

                NotificationSender smsRoutedSender = senderMap.get(smsNotification.getType());
                if (smsRoutedSender != null) {
                        smsRoutedSender.send(smsNotification);
                }

                System.out.println();

                NotificationSender pushRoutedSender = senderMap.get(pushNotification.getType());
                if (pushRoutedSender != null) {
                        pushRoutedSender.send(pushNotification);
                }

                System.out.println("\n--- Testing Notification Processor ---");

                System.out.println("Processing valid notification:");
                NotificationProcessor processor = new NotificationProcessor(
                                validMessage,
                                trimMessage,
                                emailSender);

                processor.process(emailNotification);

                System.out.println("\nProcessing invalid notification:");
                Notification invalidNotification = new Notification(
                                "U109",
                                "user@example.com",
                                null,
                                "   ",
                                NotificationType.EMAIL,
                                3);

                processor.process(invalidNotification);

                System.out.println("\nProcessing with different behavior (Uppercase):");
                NotificationProcessor anotherProcessor = new NotificationProcessor(
                                validMessage,
                                uppercaseMessage,
                                emailSender);

                anotherProcessor.process(emailNotification);

                System.out.println("\n--- Testing Validator Composition (.and()) ---");

                boolean isAllValid = allValidations.validate(emailNotification);
                System.out.println("All validations passed: " + isAllValid);

                NotificationProcessor combinedProcessor = new NotificationProcessor(
                                allValidations,
                                trimMessage,
                                emailSender);

                System.out.println("\nProcessing with combined allValidations:");
                combinedProcessor.process(emailNotification);

                System.out.println("\n--- Testing Generic Converters ---");

                Converter<Notification, String> notificationToString = value -> {
                    return value.getUserId()
                            + " - "
                            + value.getType()
                            + " - Priority: "
                            + value.getPriority();
                };

                String notificationText = notificationToString.convert(notification);

                System.out.println("\nNotification to String:");
                System.out.println(notificationText);

                Converter<String, Integer> stringToInteger = value -> {
                    return Integer.parseInt(value);
                };

                Integer number = stringToInteger.convert("123");

                System.out.println("\nString to Integer:");
                System.out.println(number);

                Converter<Integer, String> integerToString = value -> {
                    return String.valueOf(value);
                };

                String numberText = integerToString.convert(456);

                System.out.println("\nInteger to String:");
                System.out.println(numberText);

                Converter<Integer, String> priorityToLabel = value -> {

                    if (value >= 1 && value <= 2) {
                        return "LOW";
                    }

                    if (value == 3) {
                        return "MEDIUM";
                    }

                    if (value >= 4 && value <= 5) {
                        return "HIGH";
                    }

                    return "INVALID";
                };

                System.out.println("\nPriority to Label:");

                System.out.println("Priority 1: " + priorityToLabel.convert(1));
                System.out.println("Priority 2: " + priorityToLabel.convert(2));
                System.out.println("Priority 3: " + priorityToLabel.convert(3));
                System.out.println("Priority 4: " + priorityToLabel.convert(4));
                System.out.println("Priority 5: " + priorityToLabel.convert(5));
        }
}

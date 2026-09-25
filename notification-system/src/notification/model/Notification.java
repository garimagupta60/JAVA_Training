package notification.model;

public class Notification {

    private String userId;
    private String email;
    private String phone;
    private String message;
    private NotificationType type;
    private int priority;

    public Notification(
            String userId,
            String email,
            String phone,
            String message,
            NotificationType type,
            int priority) {

        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.type = type;
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
        
    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }
}

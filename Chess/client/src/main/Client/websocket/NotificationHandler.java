package Client.websocket;

import webSocketMessages.Notification;

public interface NotificationHandler {
    void notify(Notification notification);
}
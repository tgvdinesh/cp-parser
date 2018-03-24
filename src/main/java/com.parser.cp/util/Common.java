package com.parser.cp.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Optional;

public class Common {

    public static Optional<Elements> getElement(Document document, List<String> filterList) {
        for (String filter : filterList) {
            Elements element = document.select(filter);
            return Optional.of(element);
        }
        return Optional.empty();
    }

    public static void sendMessage(String message, NotificationType notificationType) {
        Notifications.Bus.notify(new Notification("CPParser", "CP Parser", message, notificationType));
    }
}

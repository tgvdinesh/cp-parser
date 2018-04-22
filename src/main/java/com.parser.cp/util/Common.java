package com.parser.cp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

public class Common {

    private static Optional<Elements> getElement(Document document, List<String> filterList) {
        for (String filter : filterList) {
            Elements element = document.select(filter);
            if (element.size() != 0)
                return Optional.of(element);
        }
        return Optional.empty();
    }

    public static void sendMessage(String message, NotificationType notificationType) {
        Notifications.Bus.notify(new Notification("CPParser", "Mind Storm", message, notificationType));
    }


    /**
     * Deserialize json and map it to POJO.
     *
     * @param jsonAsString   Pass the json which is to be converted into POJO in String format
     * @param preferredClass Preferred class in which response is required
     * @param <T>            Generic type. Should be passed as PreferredClassName.class
     * @return Returns an Optional object with preferred class converstion
     * @throws IOException Throws exception if binding error happens. If this is the case then test case should fail as well
     */
    public static <T> Optional<T> deSerialize(String jsonAsString, Class<T> preferredClass) throws IOException {
        return Optional.of(new ObjectMapper().readValue(jsonAsString, preferredClass));
    }

    public static String stackToString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

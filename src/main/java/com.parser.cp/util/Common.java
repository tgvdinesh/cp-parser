package com.parser.cp.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.parser.cp.exception.ImpartialException;
import com.parser.cp.model.Question;
import com.parser.cp.model.Task;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
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

    @NotNull
    public static Task getTask(String domAsString, List<String> inputSelectors, List<String> outputSelectors) throws ImpartialException {
        Task task = new Task();
        Question question = new Question();
        Document document = Jsoup.parse(domAsString);

        Optional<Elements> inputElement = Common.getElement(document, inputSelectors);
        if (inputElement.isPresent()) {
            question.setInput(inputElement.get().text());
        } else {
            throw new ImpartialException("Sample input was unidentified.");
        }

        Optional<Elements> outputElement = Common.getElement(document, outputSelectors);
        if (outputElement.isPresent()) {
            question.setOutput(outputElement.get().text());
        } else {
            throw new ImpartialException("Sample output was unidentified.");
        }
        task.addQuestions(question);
        return task;
    }
}

package com.parser.cp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
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
        Notifications.Bus.notify(new Notification("CPParser", "CP Parser", message, notificationType));
    }

    /**
     * Gets the task required to save in input & output
     *
     * @param domAsString     HTML tag as string
     * @param inputSelectors  Should contain list of CSS Selectors for input <p>{@code Ex: #contest-id > input-tag}</p>
     * @param outputSelectors Should contain list of CSS Selectors for input <p>{@code Ex: #contest-id > output-tag}</p>
     * @return Returns a Task filled with input & output data
     * @throws ImpartialException Throws exception even if either of input or output is not present
     */
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
}

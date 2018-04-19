package com.parser.cp.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.cp.model.payload.Task;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class JacksonUtility {
    public static <T> Optional<T> jsonPtrExpr(String hackerRankSample, String jsonPtrExpr, Class<T> className) throws IOException {
        Optional<JsonNode> optionalJsonNode = Common.deSerialize(hackerRankSample, JsonNode.class);
        if (optionalJsonNode.isPresent()) {
            JsonNode jsonNode = optionalJsonNode.get();
            JsonNode payLoadAsString = jsonNode.at(jsonPtrExpr);
            return Common.deSerialize(payLoadAsString.toString(), className);
        } else return Optional.empty();

    }

    public static String writeValueAsString(Task task) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(task);
    }
}
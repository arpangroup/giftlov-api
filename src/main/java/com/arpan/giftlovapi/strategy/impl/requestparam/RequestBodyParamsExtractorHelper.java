package com.arpan.giftlovapi.strategy.impl.requestparam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RequestBodyParamsExtractorHelper {
    public List<String> getAllKeysInJsonUsingJsonNodeFieldNames(String json, ObjectMapper mapper) throws JsonMappingException, JsonProcessingException {
        List<String> values = new ArrayList<>();
        JsonNode jsonNode = mapper.readTree(json);
        getAllKeysUsingJsonNodeFields(jsonNode, values);
        return values;
    }

    private void getAllKeysUsingJsonNodeFields(JsonNode jsonNode, List<String> values) {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                String fieldName = field.getKey();
                System.out.println(field);
                String fieldValue = field.getValue().asText();
                //keys.add(field.getKey());
                if(!fieldValue.isBlank()){
                    values.add(fieldValue);
                }
                getAllKeysUsingJsonNodeFields((JsonNode) field.getValue(), values);
            });
        } else if (jsonNode.isArray()) {
            ArrayNode arrayField = (ArrayNode) jsonNode;
            arrayField.forEach(node -> {
                getAllKeysUsingJsonNodeFields(node, values);
            });
        }
    }
}

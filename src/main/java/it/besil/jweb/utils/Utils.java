package it.besil.jweb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

/**
 * Created by besil on 09/08/2016.
 */
public class Utils {

    public static JsonSchema generateSchema(Class clazz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // configure mapper, if necessary, then create schema generator
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
        JsonSchema schema = schemaGen.generateSchema(clazz);
        return schema;
//        String schemaString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
//        return schemaString;
    }

}

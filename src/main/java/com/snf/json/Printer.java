package com.snf.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.snf.models.Facility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ngadre on 6/2/16.
 */
public class Printer {
    public static void writeToFile(String fileName, Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(object);
            Path file = Paths.get(fileName);
            Files.write(file, json.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static String getFaciltiesJSON(List<Facility> facilties) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = "{}";

        try {
            Map<String,Object> faciltiesObject = new HashMap<>();
            faciltiesObject.put("facilities",facilties);
            json = ow.writeValueAsString(faciltiesObject);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return json;
    }
}

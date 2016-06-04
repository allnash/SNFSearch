package com.snf.service;

import com.snf.json.Printer;
import com.snf.models.Facility;
import spark.QueryParamsMap;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by ngadre on 6/4/16.
 */
public class Server {


    /**
     *
     *   SEND HTTP (GET) REQUEST TO PORT 8888
     *   QUERY PARAMS          : lat, lon, radius
     *   SERVER SNF EXAMPLE    : http://localhost:8888/snf?lat=42.358506&lon=-71.060142&radius=4.5
     *   SERVER STATUS EXAMPLE : http://localhost:8888/
     *
     * */
    public static void start(){

        System.out.println("Starting Server");

        // Start Local Server
        port(8888);

        System.out.println("Listening on HTTP PORT 8888");
             /*
             *
             * Set up HTTP Route handlers For SNF Finder
             *
             * */

        get("/", (request, response) -> {
            response.header("Content-Type", "application/json");
            response.body("{\"status\":\"ok\"}");
            return response.body();
        });

        get("/snf", (request, response) -> {
            response.header("Content-Type", "application/json");
            double lat = 0.0;
            double lon = 0.0;
            double radius = 0.0;

            QueryParamsMap queryMap = request.queryMap();

            String latParam = queryMap.value("lat");
            String lonParam = queryMap.value("lon");
            String radiusParam = queryMap.value("radius");

            try {
                lat = Float.valueOf(latParam);
                lon = Float.valueOf(lonParam);
                radius = Float.valueOf(radiusParam);
            } catch (NullPointerException | java.lang.NumberFormatException e) {
                System.out.println("Error -  Could not parse -lat and -lon, please correct your input");
                lat = 0.0;
                lon = 0.0;
                radius = 0.0;
            }

            List<Facility> closeFacilities = Finder.getFacilitiesFor(lat, lon, radius);
            Finder.sort(closeFacilities);
            response.body(Printer.getFaciltiesJSON(closeFacilities));
            return response.body();
        });
    }
}

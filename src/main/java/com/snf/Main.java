package com.snf;

import com.snf.console.CommandLine;
import com.snf.console.Option;
import com.snf.json.Printer;
import com.snf.models.Facility;
import com.snf.models.ZipCode;

import java.util.*;

public class Main {

    // Parse args

    public static List<String> argsList = new ArrayList<String>();
    public static Map<String,Option> optsList = new HashMap<>();
    public static List<String> doubleOptsList = new ArrayList<String>();

    public static void main(String[] args) {

        try{

            CommandLine.processArgs(args);

        } catch (Exception e){
            System.out.println("Error - " + e.getLocalizedMessage());
            System.out.println("Quitting...");
            System.exit(0);
        }

        // etc
        System.out.println("Processing...");

        double lat = 0;
        double lon = 0;
        // Location of boston
        try {
            lat = Float.valueOf(optsList.get("-lat").getOpt());
            lon = Float.valueOf(optsList.get("-lon").getOpt());
        } catch (NullPointerException | java.lang.NumberFormatException e) {
            System.out.println("Error -  Could not parse -lat and -lon, please correct your input");
            CommandLine.printHelp();
            System.exit(0);
        }

        System.out.println("Total Number of Facilities to Search from : " + Facility.getFacilities().size());
        System.out.println("Total Number of Zip Codes to Search from : " + ZipCode.getCodes().size());

        List<Facility> closeFacilities = new ArrayList<>();

        for(Facility f : Facility.getFacilities()){
            double distance = f.getDistance_miles(lat,lon);
            // check if Distance is less than 10 miles
            Facility cf  = f;
            cf.setDistance(distance);
            int retval = Double.compare(distance, 10.0);
            if(retval < 1)
            {
                closeFacilities.add(cf);
            }
        }

        sort(closeFacilities);

        System.out.println("Total Number of Close Facilities to ( " + lat + " , " + lon + " )  : " + closeFacilities.size());
        System.out.println("Writing JSON to output.json");
        Printer.writeToFile("output.json",closeFacilities);
    }

    /**
     *
     *  Sort By Distance Up To Two Decimal places In FLOAT VALUE;
     *  If two facilities are equidistant, sort by comparing overall-rating by One decimal place.
    * */
    private static void sort(List<Facility> facilities){
        Collections.sort(facilities, new Comparator<Facility>() {
            @Override
            public int compare(final Facility object1, final Facility object2) {
                // Ascending order
                int diffValue = (int) (object1.getDistance()* 100 - object2.getDistance() * 100);
                if(diffValue == 0)
                {
                    // Decending order
                    diffValue = (int)(Float.valueOf(object2.getOverall_rating()) * 10 - Float.valueOf(object1.getOverall_rating()) * 10);
                }
                return diffValue;
            }
        });
    }
}

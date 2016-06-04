package com.snf.console;


import com.snf.Main;
import com.snf.json.Printer;
import com.snf.models.Facility;
import com.snf.models.ZipCode;
import com.snf.service.Finder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ngadre on 6/2/16.
 */
public class CommandLine {

    public static void printHelp(){
        System.out.println("usage: snf [-lat double] [-lon double] [-format JSON] [-out [File..]]\n" +
                "\t[--help] [-zipcode file] [-facilities file] [-sample]");

    }

    public static void processArgs(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            switch (args[i].charAt(0)) {
                case '-':
                    if (args[i].length() < 2)
                        throw new IllegalArgumentException("Not a valid argument: "+args[i]);
                    if (args[i].charAt(1) == '-') {
                        if (args[i].length() < 3)
                            throw new IllegalArgumentException("Not a valid argument: "+args[i]);
                        // --opt
                        Main.doubleOptsList.add(args[i].substring(2, args[i].length()));

                        if(args[i].equals("--help")){
                            Main.optsList.put(args[i],new Option(args[i],null));
                            CommandLine.printHelp();
                            System.exit(0);
                        }

                        if(args[i].equals("--sample")){
                            Main.optsList.put(args[i],new Option(args[i], null));
                            CommandLine.printSample();
                            System.exit(0);
                        }

                        if(args[i].equals("--server")){
                            Main.optsList.put(args[i],new Option(args[i], null));
                        }

                    } else {
                        if (args.length-1 == i)
                            throw new IllegalArgumentException("Expected arg after: "+args[i]);
                        // -opt
                        Main.optsList.put(args[i],new Option(args[i], args[i+1]));
                        i++;
                    }
                    break;
                default:
                    // arg
                    Main.argsList.add(args[i]);
                    break;
            }
        }
    }

    public static void printSample(){
        // etc
        System.out.println("THIS IS A SAMPLE");
        System.out.println("Processing...");

        // Location of boston
        double lat = 42.358506;
        double lon = -71.060142;


        System.out.println("Total Number of Facilities to Search from : " + Facility.getFacilities().size());
        System.out.println("Total Number of Zip Codes to Search from : " + ZipCode.getCodes().size());

        List<Facility> closeFacilities = new ArrayList<>();

        for(Facility f : Facility.getFacilities()){
            double distance = f.getDistance_miles(lat,lon);
            // check if Distance is less than 10 miles
            int retval = Double.compare(distance, 10.0);
            if(retval < 1)
            {
                closeFacilities.add(f);
            }
        }

        System.out.println("Total Number of Close Facilities to (42.358506, -71.060142) Boston :" + closeFacilities.size());

    }

    public static void runCommand(){

        double lat = 0;
        double lon = 0;
        // Location of boston
        try {
            lat = Float.valueOf(Main.optsList.get("-lat").getOpt());
            lon = Float.valueOf(Main.optsList.get("-lon").getOpt());
        } catch (NullPointerException | java.lang.NumberFormatException e) {
            System.out.println("Error -  Could not parse -lat and -lon, please correct your input");
            CommandLine.printHelp();
            System.exit(0);
        }

        double radius = 10.0;
        try {
            radius = Float.valueOf(Main.optsList.get("-radius").getOpt());
            System.out.println("Search Radius : " + radius + " miles");

        } catch (NullPointerException | java.lang.NumberFormatException e) {
            System.out.println("Invalid radius, setting to: " + radius + " miles");
        }

        List<Facility> closeFacilities = Finder.getFacilitiesFor(lat,lon,radius);
        Finder.sort(closeFacilities);

        System.out.println("Total Number of Close Facilities to ( " + lat + " , " + lon + " )  : " + closeFacilities.size());
        System.out.println("Writing JSON to output.json");
        Printer.writeToFile("output.json",closeFacilities);
    }
}

package com.snf;

import com.snf.console.CommandLine;
import com.snf.console.Option;
import com.snf.models.Facility;
import com.snf.models.ZipCode;
import com.snf.service.Server;

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
        // !IMPORTANT : INITIALIZATION OF DATA
        System.out.println("Processing...");
        System.out.println("Total Number of Facilities to Search from : " + Facility.getFacilities().size());
        System.out.println("Total Number of Zip Codes to Search from : " + ZipCode.getCodes().size());

        Boolean server = false;

        try {
            server = optsList.containsKey("--server");
        } catch (NullPointerException | java.lang.NumberFormatException e) {
            System.out.println("Invalid Server Option");
        }

        if(server) {
            Server.start();
        } else {
           CommandLine.runCommand();
        }
    }
}

package com.snf.csvreader;

import com.snf.models.ZipCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ngadre on 6/1/16.
 *
 * SNF CSV Reader
 */
public class ZipCodeCSVReader {

    public static Map<Integer,ZipCode> convertCsvToJava(String fileName) {
        BufferedReader br = null;
        String line = "";
        String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        Map<Integer,ZipCode> codes = new HashMap<>();
        String[] zipCode;
        try {

            int i = 0;
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {

                if(line.isEmpty())
                    break;

                if( i > 0 ) {

                    // split on comma(',')
                    zipCode = line.split(splitBy);

                    // create Zip code object to store values
                    ZipCode zipCodeObject = new ZipCode();

                    // add values from csv to car object
                    zipCodeObject.setNumber(Integer.valueOf(zipCode[0]));
                    zipCodeObject.setLat(Float.valueOf(zipCode[1]));
                    zipCodeObject.setLon(Float.valueOf(zipCode[2]));

                    // adding car objects to a list
                    codes.put(Integer.valueOf(zipCode[0]),zipCodeObject);
                }

                i++;

            }
            // print values stored in carList
            //printCarList(facilityList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return codes;
    }
}
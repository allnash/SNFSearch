package com.snf.models;

import com.snf.csvreader.ZipCodeCSVReader;

import java.util.Map;

/**
 * Created by ngadre on 6/1/16.
 *
 * Zip Code info
 *
 */
public class ZipCode {

    private static Map<Integer,ZipCode> codes;

    /**
     * Zip code number
     */
    public Integer number;

    /**
     * Latitude of Zip code
     */

    public double lat;
    /**
     * Longitude of Zip code
     */
    public double lon;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return codes
     * Map of ZipCodes
     */
    public static Map<Integer,ZipCode> getCodes(){
        if(codes == null){
            codes = ZipCodeCSVReader.convertCsvToJava("zip_code_centroids.csv");
        }

        return codes;
    }

    /**
     * @param integer
     * Integer value of Zip Code
     * @return zipcode
     * ZipCode for a Zip Number
     */
    public static ZipCode findByNumber(Integer integer) {

        if(codes == null){
            codes = ZipCodeCSVReader.convertCsvToJava("zip_code_centroids.csv");
        }

        Boolean b = codes.containsKey(integer);
        if(b)
            return codes.get(integer);
        else
            return null;
    }

    /**
     * @param integer
     * Integer value of Zip Code
     * @return present
     * Boolean value TRUE/FALSE if we know that we have a ZipCode Centroid
     */
    public static boolean hasZipCodeWithNumber(Integer integer) {
        Boolean b = codes.containsKey(integer);
        return b;
    }
}


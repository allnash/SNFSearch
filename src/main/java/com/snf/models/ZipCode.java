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
     * @param number
     * Zip code number
     */
    public Integer number;

    /**
     * @param lat
     * Latitude of Zip code
     */

    public double lat;
    /**
     * @param lon
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

    public static Map<Integer,ZipCode> getCodes(){
        if(codes == null){
            codes = ZipCodeCSVReader.convertCsvToJava("zip_code_centroids.csv");
        }

        return codes;
    }

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

    public static boolean hasZipCodeWithNumber(Integer integer) {
        Boolean b = codes.containsKey(integer);
        return b;
    }
}


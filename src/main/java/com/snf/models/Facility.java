package com.snf.models;

import com.snf.csvreader.FacilityCSVReader;
import com.snf.formula.Haversine;

import java.util.List;
import java.util.Map;

/**
 * Created by ngadre on 6/1/16.
 *
 * SNF Facility Model
 */
public class Facility {


    private static List<Facility> facilities;

    /**
     * SNF provider_number from the “ProviderInfo_Download.csv” file.
     */
    public String provider_number;

    /**
     * SNF name from the “ProviderInfo_Download.csv” file.
     */
    public String name;

    /**
     * SNF address from the “ProviderInfo_Download.csv” file.
     */
    public String address;

    /**
     * SNF city from the “ProviderInfo_Download.csv” file.
     */
    public String city;

    /**
     * SNF state from the “ProviderInfo_Download.csv” file.
     */
    public String state;

    /**
     * SNF zip code from the “ProviderInfo_Download.csv” file.
     */
    public String zip_code;

    /**
     * SNF phone number from the “ProviderInfo_Download.csv” file.
     */
    public String phone;

    /**
     * SNF rating from the “ProviderInfo_Download.csv” file.
     */
    public String overall_rating;


    /**
     * @return score
     * the SNF score analytic, as described in the following “Score
     * Analytic” section.
     */
    public String score() {
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOverall_rating() {
        return overall_rating;
    }

    public void setOverall_rating(String overall_rating) {
        this.overall_rating = overall_rating;
    }

    /**
     * @return lat
     * Latitude of Zip code
     */
    public double getLat() {
        ZipCode z = ZipCode.getCodes().get(Integer.valueOf(this.getZip_code()));

        if (z == null){
            System.out.println("Centroid Unknown for zip code- " + this.getZip_code());
            return 0.0;
        }

        return z.getLat();
    }

    /**
     * @return lon
     * Longitude of Zip code
     */
    public double getLon() {
        ZipCode z = ZipCode.getCodes().get(Integer.valueOf(this.getZip_code()));

        if (z == null){
            System.out.println("Centroid Unknown for zip code- " + this.getZip_code());
            return 0.0;
        }

        return z.getLon();
    }

    /**
     * @return facilities
     * List of Facilities
     */
    public static List<Facility> getFacilities(){
        if(facilities == null){
            facilities = FacilityCSVReader.convertCsvToJava("ProviderInfo_Download.csv");
        }
        return facilities;
    }

    /**
     * @param lat
     * latitude of the Centroid
     * @param lon
     * longitude of the Centroid
     * @return distance_miles
     * distance in miles from the centroid of the search zip code centroid
     * to the SNF zip code centroid
     */
    public double getDistance_miles(double lat, double lon) {
        Integer zipCode = Integer.valueOf(this.getZip_code());
        if(ZipCode.hasZipCodeWithNumber(zipCode)){
            ZipCode z = ZipCode.findByNumber(zipCode);
            return Haversine.distanceInMiles(z.getLat(),z.getLon(),lat,lon);
        } else
            return 9999;
    }

    public String getProvider_number() {
        return provider_number;
    }

    public void setProvider_number(String provider_number) {
        this.provider_number = provider_number;
    }
}


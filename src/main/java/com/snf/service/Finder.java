package com.snf.service;

import com.snf.models.Facility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ngadre on 6/4/16.
 */
public class Finder {


    /**
     * @param latitude
     * Latitude of the Centroid
     * @param longitude
     * Longitude of the Centroid
     * @param searchRadius
     * Search radius in miles from latitude,longitude
     * @return closeFacilities
     * facilities list UNSORTED within the radius specified from the centeriod lat, lon
     */
    public static List<Facility> getFacilitiesFor(double latitude, double longitude, double searchRadius){
        List<Facility> closeFacilities = new ArrayList<>();

        for(Facility f : Facility.getFacilities()){
            double distance = f.getDistance_miles(latitude,longitude);
            // check if Distance is less than 10 miles
            Facility cf  = f;
            cf.setDistance(distance);
            int retval = Double.compare(distance, searchRadius);
            if(retval < 1)
            {
                closeFacilities.add(cf);
            }
        }

        return closeFacilities;
    }

    /**
     *
     *  Sort By Distance Up To Two Decimal places In FLOAT VALUE;
     *  If two facilities are equidistant, sort by comparing overall-rating by One decimal place.
     * */
    public static void sort(List<Facility> facilities){
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

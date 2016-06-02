package com.snf.csvreader;

import com.snf.models.Facility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ngadre on 6/1/16.
 *
 * SNF CSV Reader
 */
public class FacilityCSVReader{

    public static List<Facility> convertCsvToJava(String fileName) {
        BufferedReader br = null;
        String line = "";
        String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        List facilityList = new ArrayList();
        String[] facility;

        try {

            int i = 0;
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {

                if(line.isEmpty())
                    break;

                if( i > 0 ) {
                    // split on comma(',')
                    facility = line.split(splitBy);

                    // create car object to store values
                    Facility facilityObject = new Facility();

                    // add values from csv to car object
                    // "provnum","PROVNAME","ADDRESS","CITY","STATE","ZIP","PHONE","COUNTY_SSA","COUNTY_NAME","OWNERSHIP","BEDCERT","RESTOT","CERTIFICATION","INHOSP","LBN","PARTICIPATION_DATE","CCRC_FACIL","SFF","OLDSURVEY","CHOW_LAST_12MOS","resfamcouncil","sprinkler_status","overall_rating","overall_rating_fn","survey_rating","survey_rating_fn","quality_rating","quality_rating_fn","staffing_rating","staffing_rating_fn","RN_staffing_rating","rn_staffing_rating_fn","STAFFING_FLAG","PT_STAFFING_FLAG","AIDHRD","VOCHRD","RNHRD","TOTLICHRD","TOTHRD","PTHRD","exp_aide","exp_lpn","exp_rn","exp_total","adj_aide","adj_lpn","adj_rn","adj_total","cycle_1_defs","cycle_1_nfromdefs","cycle_1_nfromcomp","cycle_1_defs_score","CYCLE_1_SURVEY_DATE","CYCLE_1_NUMREVIS","CYCLE_1_REVISIT_SCORE","CYCLE_1_TOTAL_SCORE","cycle_2_defs","cycle_2_nfromdefs","cycle_2_nfromcomp","cycle_2_defs_score","CYCLE_2_SURVEY_DATE","CYCLE_2_NUMREVIS","CYCLE_2_REVISIT_SCORE","CYCLE_2_TOTAL_SCORE","cycle_3_defs","cycle_3_nfromdefs","cycle_3_nfromcomp","cycle_3_defs_score","CYCLE_3_SURVEY_DATE","CYCLE_3_NUMREVIS","CYCLE_3_REVISIT_SCORE","CYCLE_3_TOTAL_SCORE","WEIGHTED_ALL_CYCLES_SCORE","incident_cnt","cmplnt_cnt","FINE_CNT","FINE_TOT","PAYDEN_CNT","TOT_PENLTY_CNT","FILEDATE"
                    facilityObject.setProvider_number(FacilityCSVReader.deleteAll(facility[0],"\""));
                    facilityObject.setName(FacilityCSVReader.deleteAll(facility[1],"\""));
                    facilityObject.setAddress(FacilityCSVReader.deleteAll(facility[2],"\""));
                    facilityObject.setCity(FacilityCSVReader.deleteAll(facility[3],"\""));
                    facilityObject.setState(FacilityCSVReader.deleteAll(facility[4],"\""));
                    facilityObject.setZip_code(FacilityCSVReader.deleteAll(facility[5],"\""));
                    facilityObject.setPhone(FacilityCSVReader.deleteAll(facility[6],"\""));
                    facilityObject.setOverall_rating(FacilityCSVReader.deleteAll(facility[22],"\""));

                    // adding car objects to a list
                    facilityList.add(facilityObject);

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

        return facilityList;
    }

    private static String deleteAll(String strValue, String charToRemove) {
        return strValue.replaceAll(charToRemove, "");

    }

}
package com.kewldevs.sathish.nie.Fragments;

import java.util.Calendar;

/**
 * Created by Gokul on 24-Mar-17.
 */

public class FormDataStore {

    public static final Calendar now = Calendar.getInstance();


    public static int NUMBER_OF_SECTIONS = 14;

    public static boolean isValidated[] = new boolean[NUMBER_OF_SECTIONS];

    public static String admissionDate = "";
    public static String deptName = ""; //can be int
    public static String regNo = ""; //can be long
    public static String patientName = "";
    public static double age = 0;
    public static String gender = ""; //can be int
    public static int countryCode = 91;
    public static long mobNo = 0;


    //TODO: District, Taluk, etc.

    public static String[] address = new String[FragsAddress.NUMBER_OF_CHECKS];

    public static boolean[] symptomsObserved = new boolean[FragsSymptoms.NUMBER_OF_CHECKS];

    //TODO: Additional Symptoms

    public static boolean samplesTaken[] = new boolean[FragsSample.NUMBER_OF_CHECKS];

    //TODO:
    public static String[] testTakenDate = new String[FragsSample.NUMBER_OF_CHECKS];
    public static String[] testResultDate = new String[FragsSample.NUMBER_OF_CHECKS];


    public static boolean[] environmentSamplesTaken = new boolean[FragsEnvSample.NUMBER_OF_CHECKS];



}

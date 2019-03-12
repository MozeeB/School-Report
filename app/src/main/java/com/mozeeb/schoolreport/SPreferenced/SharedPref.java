package com.mozeeb.schoolreport.SPreferenced;

public class SharedPref {
    private static String ID = "id";
    private static String NAMA = "nama";
    private static String USERNAME = "username";
    private static String NO_TELP = "no_telp";
    private static String ALAMAT = "alamat";
    private static String EMAIL = "email";
    private static String JENIS_KELAMIN = "jenis_kelamin";
    private static String PASSWORD = "password";


    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        SharedPref.ID = ID;
    }

    public static String getNAMA() {
        return NAMA;
    }

    public static void setNAMA(String NAMA) {
        SharedPref.NAMA = NAMA;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        SharedPref.USERNAME = USERNAME;
    }

    public static String getNoTelp() {
        return NO_TELP;
    }

    public static void setNoTelp(String noTelp) {
        NO_TELP = noTelp;
    }

    public static String getALAMAT() {
        return ALAMAT;
    }

    public static void setALAMAT(String ALAMAT) {
        SharedPref.ALAMAT = ALAMAT;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        SharedPref.EMAIL = EMAIL;
    }

    public static String getJenisKelamin() {
        return JENIS_KELAMIN;
    }

    public static void setJenisKelamin(String jenisKelamin) {
        JENIS_KELAMIN = jenisKelamin;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        SharedPref.PASSWORD = PASSWORD;
    }
}

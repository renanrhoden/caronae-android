package br.ufrj.caronae;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.caronae.models.User;
import br.ufrj.caronae.models.modelsforjson.PlacesForJson;
import br.ufrj.caronae.models.modelsforjson.RideForJson;

public class SharedPref {

    private static final String INSTITUTION_KEY                      = "institution";
    private static final String GOING_LABEL_KEY                      = "goingLabel";
    private static final String LEAVING_LABEL_KEY                    = "leavingLabel";
    private static final String  IS_GOING_PREF_KEY                   = "going";
    private static final String USER_PREF_KEY                        = "user";
    private static final String LAST_RIDE_OFFER_GOING_PREF_KEY       = "lastRideOfferGoing";
    private static final String LAST_RIDE_OFFER_NOT_GOING_PREF_KEY   = "lastRideOfferNotGoing";
    private static final String LAST_RIDE_SEARCH_FILTERS_PREF_KEY    = "lastRideSearchFilters";
    private static final String LAST_RIDE_FILTERS_PREF_KEY           = "lastRideFilters";
    private static final String USER_PIC_SAVED_KEY                   = "SavedImage";
    private static final String TOKEN_PREF_KEY                       = "token";
    private static final String IDUFRJ_PREF_KEY                      = "idUfrj";
    private static final String RIDESTAKEN_PREF_KEY                  = "taken";
    private static final String RIDESOFFERED_PREF_KEY                = "ridesOffered";
    private static final String NOTIFICATIONS_ON_PREF_KEY            = "notifOn";
    private static final String RM_RIDE_LIST                         = "removeRideFromList";
    public static boolean OPEN_ALL_RIDES                             = false;
    public static boolean OPEN_MY_RIDES                              = false;
    public static final String RIDE_FILTER_PREF_KEY                  = "filter";
    public static final String DIALOG_CAMPUS_SEARCH_KEY              = "campus";
    public static final String DIALOG_CENTER_SEARCH_KEY              = "centro";
    public static final String DIALOG_DISMISS_KEY                    = "dismiss_key";
    public static final String MY_RIDES_DELETE_TUTORIAL              = "my_rides_delete_tut";
    public static final String MISSING_PREF                          = "missing";
    public static final String TOPIC_GERAL                           = "general";
    public static final String MY_RIDE_LIST_KEY                      = "myRides";
    public static final String PLACE_KEY                            = "preloadedplaces";
    public static String NAV_INDICATOR                               = "AllRides";
    public static String CHAT_ACT_STATUS                             = "chatStatus";
    public static String FRAGMENT_INDICATOR                          = "";
    public static List<RideForJson> MY_RIDES                         = null;
    public static ArrayList<RideForJson> ALL_RIDES_GOING             = null;
    public static ArrayList<RideForJson> ALL_RIDES_LEAVING           = null;

    private static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInst());
    }

    private static SharedPreferences.Editor getSharedPrefEditor() {
        return getSharedPreferences().edit();
    }

    public static void putPref(String key, String value) {
        getSharedPrefEditor().putString(key, value).apply();
    }

    public static void putBooleanPref(String key, boolean value) {
        getSharedPrefEditor().putBoolean(key, value).apply();
    }

    public static String getPref(String key) {
        return getSharedPreferences().getString(key, MISSING_PREF);
    }

    public static boolean getBooleanPref(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    private static void removePref(String key) {
        getSharedPrefEditor().remove(key).apply();
    }

    public static boolean checkExistence(String key)
    {
        return getSharedPreferences().contains(key);
    }

    public static void removeAllPrefButGcm() {
        removePref(USER_PREF_KEY);
        removePref(LAST_RIDE_OFFER_GOING_PREF_KEY);
        removePref(LAST_RIDE_OFFER_NOT_GOING_PREF_KEY);
        removePref(LAST_RIDE_SEARCH_FILTERS_PREF_KEY);
        removePref(TOKEN_PREF_KEY);
        removePref(NOTIFICATIONS_ON_PREF_KEY);
        removePref(USER_PIC_SAVED_KEY);
        removePref(RM_RIDE_LIST);
        removePref(IS_GOING_PREF_KEY);
        removePref(INSTITUTION_KEY);
        removePref(GOING_LABEL_KEY);
        removePref(LEAVING_LABEL_KEY);
        NAV_INDICATOR = "AllRides";
    }

    public static void setChatActIsForeground(boolean isForeground){
        putBooleanPref(CHAT_ACT_STATUS, isForeground);
    }

    public static boolean getChatActIsForeground(){
        return getBooleanPref(CHAT_ACT_STATUS);
    }

    public static void setRidesTaken(String ridesTaken)
    {
        putPref(RIDESTAKEN_PREF_KEY, ridesTaken);
    }

    public static void setRidesOffered(String ridesOffered)
    {
        putPref(RIDESOFFERED_PREF_KEY, ridesOffered);
    }

    public static String getRidesTaken()
    {
        return getPref(RIDESTAKEN_PREF_KEY);
    }

    public static String getRidesOffered()
    {
        return getPref(RIDESOFFERED_PREF_KEY);
    }

    public static void setSavedPic(boolean open)
    {
        putBooleanPref(USER_PIC_SAVED_KEY, open);
    }

    public static boolean getSavedPic()
    {
        return getBooleanPref(USER_PIC_SAVED_KEY);
    }

    public static void setIsGoingPref(String isGoing)
    {
        putPref(IS_GOING_PREF_KEY, isGoing);
    }

    public static String getIsGoingPref()
    {
        return getPref(IS_GOING_PREF_KEY);
    }

    public static void setPlace(PlacesForJson place)
    {
        Gson gson = new Gson();
        String placeToJson = gson.toJson(place);
        putPref(PLACE_KEY, placeToJson);
    }

    public static PlacesForJson getPlace()
    {
        Gson gson = new Gson();
        String placeToObject = getPref(PLACE_KEY);
        return gson.fromJson(placeToObject, PlacesForJson.class);
    }

    public static String getInstitution()
    {
        if(getSharedPreferences().contains(PLACE_KEY))
            return getPlace().getInstitutions().getName();
        else
            return null;
    }

    public static String getGoingLabel()
    {
        if(getSharedPreferences().contains(PLACE_KEY))
            return getPlace().getInstitutions().getGoing_label();
        else
            return null;
    }

    public static String getLeavingLabel()
    {
        if(getSharedPreferences().contains(PLACE_KEY))
            return getPlace().getInstitutions().getLeaving_label();
        else
            return null;
    }

    public static void saveNotifPref(String value) {
        putPref(NOTIFICATIONS_ON_PREF_KEY, value);
    }

    public static String getNotifPref() {
        return getPref(NOTIFICATIONS_ON_PREF_KEY);
    }

    public static void saveLastRideGoingPref(String lastRideOffer) {
        putPref(LAST_RIDE_OFFER_GOING_PREF_KEY, lastRideOffer);
    }

    public static void saveDialogSearchPref(String key, String search) {
        putPref(key, search);
    }

    public static String getDialogSearchPref(String key) {
        return getPref(key);
    }

    public static void saveDialogTypePref(String key, String type) {
        putPref(key, type);
    }

    public static String getDialogTypePref(String Type) {
        return getPref(Type);
    }

    public static String getLastRideGoingPref() {
        return getPref(LAST_RIDE_OFFER_GOING_PREF_KEY);
    }

    public static void saveLastRideNotGoingPref(String lastRideOffer) {
        putPref(LAST_RIDE_OFFER_NOT_GOING_PREF_KEY, lastRideOffer);
    }

    public static String getLastRideNotGoingPref() {
        return getPref(LAST_RIDE_OFFER_NOT_GOING_PREF_KEY);
    }

    public static String getUserPref() {
        return getPref(USER_PREF_KEY);
    }

    public static String getLastRideSearchFiltersPref() {
        return getPref(LAST_RIDE_SEARCH_FILTERS_PREF_KEY);
    }

    public static String getRideFiltersPref() {
        return getPref(LAST_RIDE_FILTERS_PREF_KEY);
    }

    public static void saveLastFiltersPref(String lastFilters){
        putPref(LAST_RIDE_FILTERS_PREF_KEY, lastFilters);
    }

    public static void saveFilterPref(String filters){
        putPref(RIDE_FILTER_PREF_KEY, filters);
    }

    public static String getFiltersPref() {
        return getPref(RIDE_FILTER_PREF_KEY);
    }

    public static void saveLastRideSearchFiltersPref(String lastRideSearchFilters) {
        putPref(LAST_RIDE_SEARCH_FILTERS_PREF_KEY, lastRideSearchFilters);
    }

    public static void saveUser(User user) {
        putPref(USER_PREF_KEY, new Gson().toJson(user));
    }

    public static String getUserToken() {
        return getPref(TOKEN_PREF_KEY);
    }

    public static String getUserIdUfrj() {
        return getPref(IDUFRJ_PREF_KEY);
    }

    public static void saveUserToken(String token) {
        putPref(TOKEN_PREF_KEY, token.toUpperCase());
    }

    public static void saveUserIdUfrj(String idUfrj) {
        putPref(IDUFRJ_PREF_KEY, idUfrj);
    }

    public static void saveRemoveRideFromList(String profilePicUrl) {
        putPref(RM_RIDE_LIST, profilePicUrl);
    }

    public static String getRemoveRideFromList() {
        return getPref(RM_RIDE_LIST);
    }

    public static void removeRemoveRideFromList() {
        removePref(RM_RIDE_LIST);
    }
}

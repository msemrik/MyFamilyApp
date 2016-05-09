package com.example.msem.myfamilyapp.util;


public class Constants {

    public final static String PERSON_DETAIL_PARAMETER_NAME = "NAME";
    public final static String PERSON_DETAIL_PARAMETER_CITY = "CITY";
    public final static String PERSON_DETAIL_PARAMETER_LAT = "LAT";
    public final static String PERSON_DETAIL_PARAMETER_LANG = "LANG";

    public final static String FETCH_WEATHER_JSON_LIST = "list";
    public final static String FETCH_WEATHER_JSON_WEATHER = "weather";
    public final static String FETCH_WEATHER_JSON_TEMPERATURE = "temp";
    public final static String FETCH_WEATHER_JSON_MAX = "max";
    public final static String FETCH_WEATHER_JSON_MIN = "min";
    public final static String FETCH_WEATHER_JSON_DESCRIPTION = "main";

    public final static String FETCH_WEATHER_REQUEST_FORMAT = "json";
    public final static String FETCH_WEATHER_REQUEST_UNITS = "metric";
    public final static int FETCH_WEATHER_REQUEST_AMOUNT = 15;

    public final static String FETCH_WEATHER_REQUEST_PARAMETER_BASEURL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    public final static String FETCH_WEATHER_REQUEST_PARAMETER_QUERY = "q";
    public final static String FETCH_WEATHER_REQUEST_PARAMETER_FORMAT = "mode";
    public final static String FETCH_WEATHER_REQUEST_PARAMETER_UNITS = "units";
    public final static String FETCH_WEATHER_REQUEST_PARAMETER_DAYS = "cnt";
    public final static String FETCH_WEATHER_REQUEST_PARAMETER_APPID = "APPID";


    public final static String FETCH_WEATHER_REQUEST_APPID = "3e22d21e8847a9c23f95467e7472660a";


    public final static String FETCH_TIME_REQUEST_FORMAT = "true";
    public final static String FETCH_TIME_REQUEST_USERNAME = "demo";
    public final static String FETCH_TIME_REQUEST_STYLE = "full";


    public final static String FETCH_TIME_REQUEST_PARAMETER_BASEURL = "http://api.geonames.org/timezoneJSON?";
    public final static String FETCH_TIME_REQUEST_PARAMETER_FORMATTED = "formatted";
    public final static String FETCH_TIME_REQUEST_PARAMETER_LAT = "lat";
    public final static String FETCH_TIME_REQUEST_PARAMETER_LANG = "lng";
    public final static String FETCH_TIME_REQUEST_PARAMETER_USERNAME = "username";
    public final static String FETCH_TIME_REQUEST_PARAMETER_STYLE = "style";

    public final static String FETCH_TIME_RESPONSE_OFFSET = "dstOffset";

    public final static String FETCH_TIME_CENTRAL_TIMEZONE = "UTC";
    public final static String FETCH_TIME_DATEFORMAT = "dd/MM/yyyy HH:mm";



}

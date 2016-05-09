package com.example.msem.myfamilyapp.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.text.format.Time;
import android.widget.ArrayAdapter;

import com.example.msem.myfamilyapp.util.Constants;
import com.example.msem.myfamilyapp.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;


public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

    private ArrayAdapter<String> mForecastAdapter;

    public FetchWeatherTask(ArrayAdapter<String> mForecastAdapter) {
        this.mForecastAdapter = mForecastAdapter;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        mForecastAdapter.clear();
        for (String string : strings) {
            mForecastAdapter.add(string);
        }
        mForecastAdapter.notifyDataSetChanged();
    }

    @Override
    protected String[] doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {

            Uri builtUri = Uri.parse(Constants.FETCH_WEATHER_REQUEST_PARAMETER_BASEURL).buildUpon()
                    .appendQueryParameter(Constants.FETCH_WEATHER_REQUEST_PARAMETER_QUERY, params[0])
                    .appendQueryParameter(Constants.FETCH_WEATHER_REQUEST_PARAMETER_FORMAT, Constants.FETCH_WEATHER_REQUEST_FORMAT)
                    .appendQueryParameter(Constants.FETCH_WEATHER_REQUEST_PARAMETER_UNITS, params[1])
                    .appendQueryParameter(Constants.FETCH_WEATHER_REQUEST_PARAMETER_DAYS, Integer.toString(Constants.FETCH_WEATHER_REQUEST_AMOUNT))
                    .appendQueryParameter(Constants.FETCH_WEATHER_REQUEST_PARAMETER_APPID, Constants.FETCH_WEATHER_REQUEST_APPID)
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            String forecastJsonStr = JSONUtil.getStringFromInputStream(inputStream);
            return getWeatherDataFromJson(forecastJsonStr, Constants.FETCH_WEATHER_REQUEST_AMOUNT);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
            throws JSONException {

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray(Constants.FETCH_WEATHER_JSON_LIST);

        Time dayTime = new Time();
        dayTime.setToNow();

        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
        dayTime = new Time();

        String[] resultStrs = new String[numDays];
        for (int i = 0; i < weatherArray.length(); i++) {
            String day;
            String description;
            String highAndLow;

            JSONObject dayForecast = weatherArray.getJSONObject(i);
            long dateTime;
            dateTime = dayTime.setJulianDay(julianStartDay + i);
            day = getReadableDateString(dateTime);

            JSONObject weatherObject = dayForecast.getJSONArray(Constants.FETCH_WEATHER_JSON_WEATHER).getJSONObject(0);
            description = weatherObject.getString(Constants.FETCH_WEATHER_JSON_DESCRIPTION);

            JSONObject temperatureObject = dayForecast.getJSONObject(Constants.FETCH_WEATHER_JSON_TEMPERATURE);
            double high = temperatureObject.getDouble(Constants.FETCH_WEATHER_JSON_MAX);
            double low = temperatureObject.getDouble(Constants.FETCH_WEATHER_JSON_MIN);

            highAndLow = formatHighLows(high, low);
            resultStrs[i] = day + " - " + description + " - " + highAndLow;
        }

        return resultStrs;

    }

    private String getReadableDateString(long time) {
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }

    private String formatHighLows(double high, double low) {
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }



}


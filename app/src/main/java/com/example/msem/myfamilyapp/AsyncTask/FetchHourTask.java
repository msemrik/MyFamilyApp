package com.example.msem.myfamilyapp.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import com.example.msem.myfamilyapp.util.Constants;
import com.example.msem.myfamilyapp.util.JSONUtil;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class FetchHourTask extends AsyncTask<String, Void, String[]> {

    private FragmentActivity fragmentActivity;

    public FetchHourTask(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }


    @Override
    protected void onPostExecute(String[] result) {
        fragmentActivity.setTitle(result[0] + "  -  " + result[1]);
    }


    @Override
    protected String[] doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {

            Uri builtUri = Uri.parse(Constants.FETCH_TIME_REQUEST_PARAMETER_BASEURL).buildUpon()
                    .appendQueryParameter(Constants.FETCH_TIME_REQUEST_PARAMETER_FORMATTED, Constants.FETCH_TIME_REQUEST_FORMAT)
                    .appendQueryParameter(Constants.FETCH_TIME_REQUEST_PARAMETER_LAT, params[0])
                    .appendQueryParameter(Constants.FETCH_TIME_REQUEST_PARAMETER_LANG, params[1])
                    .appendQueryParameter(Constants.FETCH_TIME_REQUEST_PARAMETER_USERNAME, Constants.FETCH_TIME_REQUEST_USERNAME)
                    .appendQueryParameter(Constants.FETCH_TIME_REQUEST_PARAMETER_STYLE, Constants.FETCH_TIME_REQUEST_STYLE)
                    .build();

            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            String hourJsonStr = JSONUtil.getStringFromInputStream(inputStream);

            String[] resultStrs = new String[2];
            resultStrs[0] = params[2];
            resultStrs[1] = getTimeFromJson(hourJsonStr);

            return resultStrs;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        return null;
    }

    private String getTimeFromJson(String hourJsonStr) throws JSONException {

        JSONObject hourJson = new JSONObject(hourJsonStr);
        String hour = hourJson.getString(Constants.FETCH_TIME_RESPONSE_OFFSET);

        TimeZone timeZone = TimeZone.getTimeZone(Constants.FETCH_TIME_CENTRAL_TIMEZONE);
        Calendar cal = Calendar.getInstance(timeZone);
        cal.add(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));

        final SimpleDateFormat sdf = new SimpleDateFormat(Constants.FETCH_TIME_DATEFORMAT);
        sdf.setTimeZone(timeZone);
        String utcTime = sdf.format(cal.getTime());

        return utcTime;

    }

}
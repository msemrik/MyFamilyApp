package com.example.msem.myfamilyapp.util;

import com.example.msem.myfamilyapp.business.Location;
import com.example.msem.myfamilyapp.business.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.msem.myfamilyapp.R.raw.data;

/**
 * Created by semri on 5/7/2016.
 */
public class JSONUtil {

    public static List<Person> getPersonsFromJSON(InputStream inputStream) throws IOException, JSONException {

        String jsonString = getStringFromInputStream(inputStream);
        JSONArray jsonArray = new JSONArray(jsonString);

        List<Person> persons = new ArrayList<>();

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject personJson = (JSONObject) jsonArray.get(i);

            JSONObject locationJson = (JSONObject) personJson.getJSONObject("location");

            String city = locationJson.getString("city");
            String province = locationJson.getString("province");
            String country = locationJson.getString("country");
            String latitude = locationJson.getString("lat");
            String longitug = locationJson.getString("lng");
            Location location = new Location(city, province, country, latitude, longitug);

            String name = personJson.getString("name");
            String photo = personJson.getString("photo");

            persons.add(new Person(name, photo, location));
        }

        return persons;
    }

    public static String getStringFromInputStream(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder str = new StringBuilder();
        String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                str.append(line);
            }
        return str.toString();
    }

}

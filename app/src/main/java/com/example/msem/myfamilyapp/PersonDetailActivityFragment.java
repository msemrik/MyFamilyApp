/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.msem.myfamilyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.msem.myfamilyapp.AsyncTask.FetchHourTask;
import com.example.msem.myfamilyapp.AsyncTask.FetchWeatherTask;
import com.example.msem.myfamilyapp.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class PersonDetailActivityFragment extends Fragment {

    private ArrayAdapter<String> mForecastAdapter;

    public PersonDetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void updateWeather(String city) {
        FetchWeatherTask weatherTask = new FetchWeatherTask(mForecastAdapter);
        weatherTask.execute(city, Constants.FETCH_WEATHER_REQUEST_UNITS);
    }

    private void updateTime(String lat, String lang, String name) {
        FetchHourTask fetchHour = new FetchHourTask(getActivity());
        fetchHour.execute(lat, lang, name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.detail_fragment, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        mForecastAdapter =new ArrayAdapter<String>(getActivity(),R.layout.detail_list_item,R.id.list_item_forecast_textview);
        listView.setAdapter(mForecastAdapter);

        Intent intent = getActivity().getIntent();

        final String name = intent.getStringExtra(Constants.PERSON_DETAIL_PARAMETER_NAME);
        getActivity().setTitle(name);

        String city = intent.getStringExtra(Constants.PERSON_DETAIL_PARAMETER_CITY);
        updateWeather(city);

        final String lat = intent.getStringExtra(Constants.PERSON_DETAIL_PARAMETER_LAT);
        final String lang = intent.getStringExtra(Constants.PERSON_DETAIL_PARAMETER_LANG);
        updateTime(lat, lang, name);


        TextView textView= (TextView) rootView. findViewById(R.id.detail_fragment_goto_map);
        textView.setOnClickListener(new TextView.OnClickListener() {


            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:0,0?q=" + lat + "," + lang + "("+ name +")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);}
            }
        });


        return rootView;
    }

}

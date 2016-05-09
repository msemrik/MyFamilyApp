package com.example.msem.myfamilyapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.msem.myfamilyapp.business.Person;
import com.example.msem.myfamilyapp.util.Constants;
import com.example.msem.myfamilyapp.util.JSONUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import static com.example.msem.myfamilyapp.R.raw.data;

public class MainActivityFragment extends Fragment {

    DisplayMetrics metrics;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        List<Person> personList = null;
        try {
            personList = JSONUtil.getPersonsFromJSON(getResources().openRawResource(data));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (metrics == null) {
            metrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
        }


        GridView gridview = (GridView) rootView.findViewById(R.id.grid_view);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridview.setNumColumns(2);
        } else {
            gridview.setNumColumns(3);
        }


        Integer[] integers = new Integer[personList.size()];

        for (int i = 0; i < personList.size(); i++) {
            integers[i] = getResources().getIdentifier(personList.get(i).getPhotoResource(), "drawable", getActivity().getPackageName());
        }

        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), metrics);
        imageAdapter.setmThumbIds(integers);
        gridview.setAdapter(imageAdapter);


        final List<Person> tempPersons = personList;
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Person person = tempPersons.get(position);

                Intent personDetailActivity = new Intent(getActivity(), PersonDetailActivity.class);

                String city = person.getLocation().getCity() + ", " + person.getLocation().getProvice();

                personDetailActivity.putExtra(Constants.PERSON_DETAIL_PARAMETER_NAME, person.getName());
                personDetailActivity.putExtra(Constants.PERSON_DETAIL_PARAMETER_CITY, city);
                personDetailActivity.putExtra(Constants.PERSON_DETAIL_PARAMETER_LAT, person.getLocation().getLatitude());
                personDetailActivity.putExtra(Constants.PERSON_DETAIL_PARAMETER_LANG, person.getLocation().getLongitud());
                startActivity(personDetailActivity);
            }
        });

        return rootView;
    }
}

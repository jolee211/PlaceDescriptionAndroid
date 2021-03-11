package edu.asu.bsse.dlee129.placedescription;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// Copyright 2021 David Lee
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author David Lee    mailto:dlee129@asu.edu
 * @version February 2021
 */
public class MainActivity extends AppCompatActivity {
    private TextInputLayout textInitJson;
    private TextView textPlaceDescriptionName;
    private TextView textPlaceDescriptionDescription;
    private TextView textPlaceDescriptionCategory;
    private TextView textPlaceDescriptionAddressTitle;
    private TextView textPlaceDescriptionAddressStreet;
    private TextView textPlaceDescriptionElevation;
    private TextView textPlaceDescriptionLatitude;
    private TextView textPlaceDescriptionLongitude;
    private TextView textPlaceDescriptionToJsonString;

    private PlaceLibrary placeLibrary;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlaceLibrary();
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        for (PlaceDescription pd :
                placeLibrary.getPlaceDescriptions()) {
            exampleList.add(new ExampleItem(R.drawable.ic_android, pd.getName(), pd.getDescription()));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private boolean validateInitJson() {
        EditText editableTextInitJson = textInitJson.getEditText();
        if (editableTextInitJson != null) {
            String initJson = editableTextInitJson.getText().toString().trim();

            if (initJson.isEmpty()) {
                textInitJson.setError("Field can't be empty");
                return false;
            } else {
                textInitJson.setError(null);
                return true;
            }
        }
        return false;
    }

    public void initPlaceDescription(View v) {
        if (!validateInitJson()) {
            return;
        }

        String jsonStr = textInitJson.getEditText().getText().toString();
        Log.i("initPlaceDescription", "JSON String : " + jsonStr);

        PlaceDescription placeDescription = new PlaceDescription(jsonStr);
        textPlaceDescriptionName.setText(String.format(getResources().getString(R.string.placedescription_name), placeDescription.getName()));
        textPlaceDescriptionDescription.setText(String.format(getResources().getString(R.string.placedescription_description), placeDescription.getDescription()));
        textPlaceDescriptionCategory.setText(String.format(getResources().getString(R.string.placedescription_category), placeDescription.getCategory()));
        textPlaceDescriptionAddressTitle.setText(String.format(getResources().getString(R.string.placedescription_address_title), placeDescription.getAddressTitle()));
        textPlaceDescriptionAddressStreet.setText(String.format(getResources().getString(R.string.placedescription_address_street), placeDescription.getAddressStreet()));
        textPlaceDescriptionElevation.setText(String.format(getResources().getString(R.string.placedescription_elevation), placeDescription.getElevation()));
        textPlaceDescriptionLatitude.setText(String.format(getResources().getString(R.string.placedescription_latitude), placeDescription.getLatitude()));
        textPlaceDescriptionLongitude.setText(String.format(getResources().getString(R.string.placedescription_longitude), placeDescription.getLongitude()));
        textPlaceDescriptionToJsonString.setText(String.format(getResources().getString(R.string.placedescription_name), placeDescription.getName()));
        Log.i("initPlaceDescription", "PlaceDescription.toJsonString() : " + placeDescription.toJsonString());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initPlaceLibrary() {
        AssetManager assetManager = getResources().getAssets();
        String json = null;
        try (InputStream is = assetManager.open("places.json")) {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);

            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        placeLibrary = new PlaceLibrary(json);
    }

}
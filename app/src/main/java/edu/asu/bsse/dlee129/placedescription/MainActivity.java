package edu.asu.bsse.dlee129.placedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

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
    private String jsonStr;
    private PlaceDescription placeDescription;
    private TextView textPlaceDescriptionName;
    private TextView textPlaceDescriptionDescription;
    private TextView textPlaceDescriptionCategory;
    private TextView textPlaceDescriptionAddressTitle;
    private TextView textPlaceDescriptionAddressStreet;
    private TextView textPlaceDescriptionElevation;
    private TextView textPlaceDescriptionLatitude;
    private TextView textPlaceDescriptionLongitude;
    private TextView textPlaceDescriptionToJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInitJson = findViewById(R.id.initJson);
        textPlaceDescriptionName = findViewById(R.id.placeDescriptionName);
        textPlaceDescriptionDescription = findViewById(R.id.placeDescriptionDescription);
        textPlaceDescriptionCategory = findViewById(R.id.placeDescriptionCategory);
        textPlaceDescriptionAddressTitle = findViewById(R.id.placeDescriptionAddressTitle);
        textPlaceDescriptionAddressStreet = findViewById(R.id.placeDescriptionAddressStreet);
        textPlaceDescriptionElevation = findViewById(R.id.placeDescriptionElevation);
        textPlaceDescriptionLatitude = findViewById(R.id.placeDescriptionLatitude);
        textPlaceDescriptionLongitude = findViewById(R.id.placeDescriptionLongitude);
        textPlaceDescriptionToJsonString = findViewById(R.id.placeDescriptionToJsonString);
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

        jsonStr = textInitJson.getEditText().getText().toString();
        Log.i("initPlaceDescription", "JSON String : " + jsonStr);

        placeDescription = new PlaceDescription(jsonStr);
        textPlaceDescriptionName.setText("Name : " + placeDescription.getName());
        textPlaceDescriptionDescription.setText("Description : " + placeDescription.getDescription());
        textPlaceDescriptionCategory.setText("Category : " + placeDescription.getCategory());
        textPlaceDescriptionAddressTitle.setText("Address Title : " + placeDescription.getAddressTitle());
        textPlaceDescriptionAddressStreet.setText("Address Street : " + placeDescription.getAddressStreet());
        textPlaceDescriptionElevation.setText("Elevation : " + placeDescription.getElevation());
        textPlaceDescriptionLatitude.setText("Latitude : " + placeDescription.getLatitude());
        textPlaceDescriptionLongitude.setText("Longitude : " + placeDescription.getLongitude());
        textPlaceDescriptionToJsonString.setText("PlaceDescription.toJsonString() : " + placeDescription.toJsonString());
        Log.i("initPlaceDescription", "PlaceDescription.toJsonString() : " + placeDescription.toJsonString());
    }

}
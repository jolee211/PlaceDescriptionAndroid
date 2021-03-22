package edu.asu.bsse.dlee129.placedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class AddActivity extends AppCompatActivity {
    private PlaceDescription pd;
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextCategory;
    private EditText editTextAddressTitle;
    private EditText editTextAddressStreet;
    private EditText editTextElevation;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        editTextAddressTitle = (EditText) findViewById(R.id.editTextAddressTitle);
        editTextAddressStreet = (EditText) findViewById(R.id.editTextAddressStreet);
        editTextElevation = (EditText) findViewById(R.id.editTextElevation);
        editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
        editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new PlaceDescription();
                pd.setName(editTextName.getText().toString());
                pd.setDescription(editTextDescription.getText().toString());
                pd.setCategory(editTextCategory.getText().toString());
                pd.setAddressTitle(editTextAddressTitle.getText().toString());
                pd.setAddressStreet(editTextAddressStreet.getText().toString());

                String elevationString = editTextElevation.getText().toString();
                String latitudeString = editTextLatitude.getText().toString();
                String longitudeString = editTextLongitude.getText().toString();
                pd.setElevation(elevationString != null && !elevationString.isEmpty() ? Double.parseDouble(elevationString) : 0);
                pd.setLatitude(latitudeString != null && !latitudeString.isEmpty() ? Double.parseDouble(latitudeString) : 0);
                pd.setLongitude(longitudeString != null && !longitudeString.isEmpty() ? Double.parseDouble(longitudeString) : 0);
                MainActivity.addPlaceDescription(pd);
                finish();
            }
        });
    }
}
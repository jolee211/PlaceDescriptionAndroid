package edu.asu.bsse.dlee129.placedescription;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
 * @version January 2021
 */
public class DistanceActivity extends AppCompatActivity {
    private PlaceDescriptionAdapter pd1;
    private PlaceDescriptionAdapter pd2;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        Spinner place1Spinner = (Spinner) findViewById(R.id.spinnerPlace1);
        ArrayAdapter<PlaceDescriptionAdapter> place1SpinnerAdapter = createSpinnerAdapter();
        place1Spinner.setAdapter(place1SpinnerAdapter);
        place1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pd1 = (PlaceDescriptionAdapter) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner place2Spinner = (Spinner) findViewById(R.id.spinnerPlace2);
        ArrayAdapter<PlaceDescriptionAdapter> place2SpinnerAdapter = createSpinnerAdapter();
        place2Spinner.setAdapter(place2SpinnerAdapter);
        place2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pd2 = (PlaceDescriptionAdapter) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button greatCircleDistanceButton = (Button) findViewById(R.id.buttonCalcGreatCircleDistance);
        greatCircleDistanceButton.setOnClickListener(v -> {
            TextView textDistance = (TextView) findViewById(R.id.textDistance);
            textDistance.setText(String.valueOf(GreatCircleDistanceCalculator.distance(pd1.getPlaceDescription().getLatitude(),
                    pd1.getPlaceDescription().getLongitude(), pd2.getPlaceDescription().getLatitude(),
                    pd2.getPlaceDescription().getLongitude())));
        });

        Button backToMainButton = (Button) findViewById(R.id.buttonBackToMain);
        backToMainButton.setOnClickListener(v -> {
            finish();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private ArrayAdapter<PlaceDescriptionAdapter> createSpinnerAdapter() {
        ArrayAdapter<PlaceDescriptionAdapter> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.addAll(MainActivity.getPlaceDescriptionAdapters());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
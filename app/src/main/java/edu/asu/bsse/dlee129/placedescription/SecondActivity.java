package edu.asu.bsse.dlee129.placedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {
    private TextView textPlaceDescriptionName;
    private TextView textPlaceDescriptionDescription;
    private TextView textPlaceDescriptionCategory;
    private TextView textPlaceDescriptionAddressTitle;
    private TextView textPlaceDescriptionAddressStreet;
    private TextView textPlaceDescriptionElevation;
    private TextView textPlaceDescriptionLatitude;
    private TextView textPlaceDescriptionLongitude;
    private Button buttonOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textPlaceDescriptionName = findViewById(R.id.placeDescriptionName);
        textPlaceDescriptionDescription = findViewById(R.id.placeDescriptionDescription);
        textPlaceDescriptionCategory = findViewById(R.id.placeDescriptionCategory);
        textPlaceDescriptionAddressTitle = findViewById(R.id.placeDescriptionAddressTitle);
        textPlaceDescriptionAddressStreet = findViewById(R.id.placeDescriptionAddressStreet);
        textPlaceDescriptionElevation = findViewById(R.id.placeDescriptionElevation);
        textPlaceDescriptionLatitude = findViewById(R.id.placeDescriptionLatitude);
        textPlaceDescriptionLongitude = findViewById(R.id.placeDescriptionLongitude);
        String name = getIntent().getStringExtra("placeDescription.name");
        String description = getIntent().getStringExtra("placeDescription.description");
        String category = getIntent().getStringExtra("placeDescription.category");
        String addressTitle = getIntent().getStringExtra("placeDescription.addressTitle");
        String addressStreet = getIntent().getStringExtra("placeDescription.addressStreet");
        Double elevation = (Double) getIntent().getSerializableExtra("placeDescription.elevation");
        Double latitude = (Double) getIntent().getSerializableExtra("placeDescription.latitude");
        Double longitude = (Double) getIntent().getSerializableExtra("placeDescription.longitude");
        textPlaceDescriptionName.setText(getString(R.string.placedescription_name, name));
        textPlaceDescriptionDescription.setText(getString(R.string.placedescription_description, description));
        textPlaceDescriptionCategory.setText(getString(R.string.placedescription_category, category));
        textPlaceDescriptionAddressTitle.setText(getString(R.string.placedescription_address_title, addressTitle));
        textPlaceDescriptionAddressStreet.setText(getString(R.string.placedescription_address_street, addressStreet));
        textPlaceDescriptionElevation.setText("Elevation: " + elevation);
        textPlaceDescriptionLatitude.setText(getString(R.string.placedescription_latitude, latitude));
        textPlaceDescriptionLongitude.setText(getString(R.string.placedescription_longitude, longitude));

        buttonOK = (Button) findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
package edu.asu.bsse.dlee129.placedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

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
    }

    private boolean validateInitJson() {
        String initJson = textInitJson.getEditText().getText().toString().trim();

        if (initJson.isEmpty()) {
            textInitJson.setError("Field can't be empty");
            return false;
        } else {
            textInitJson.setError(null);
            return true;
        }
    }

    public void initPlaceDescription(View v) {
        if (!validateInitJson()) {
            return;
        }

        jsonStr = textInitJson.getEditText().getText().toString();
        Toast.makeText(this, jsonStr, Toast.LENGTH_SHORT).show();

        placeDescription = new PlaceDescription(jsonStr);
        textPlaceDescriptionName.setText("Name : " + placeDescription.getName());
    }

}
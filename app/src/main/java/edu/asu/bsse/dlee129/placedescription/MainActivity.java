package edu.asu.bsse.dlee129.placedescription;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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
    private static PlaceLibrary placeLibrary;
    private PlaceDescriptionListAdapter mAdapter;
    private EditText editTextRemove;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlaceLibrary();
        buildRecyclerView();

        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonRemove = findViewById(R.id.button_remove);
        editTextRemove = findViewById(R.id.edittext_remove);
        buttonAdd.setOnClickListener(v -> addItem());
        buttonRemove.setOnClickListener(v -> {
            int position = Integer.parseInt(editTextRemove.getText().toString());
            removeItem(position);
        });

        Button buttonDistance = findViewById(R.id.button_distance);
        buttonDistance.setOnClickListener(v -> openDistanceActivity());
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

    public void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PlaceDescriptionListAdapter(placeLibrary.getPlaceDescriptions());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(position -> showInputBox(placeLibrary.get(position), position));
    }

    public void addItem() {
        int position = placeLibrary.size();
        openAddActivity();
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if (placeLibrary.remove(position) != null) {
            mAdapter.notifyItemRemoved(position);
        }
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("textMessage", getString(R.string.text_add));
        startActivity(intent);
    }

    public void openDistanceActivity() {
        Intent intent = new Intent(this, DistanceActivity.class);
        startActivity(intent);
    }

    public static void addPlaceDescription(PlaceDescription pd) {
        if (placeLibrary != null) {
            placeLibrary.add(pd);
        }
    }

    public void showInputBox(PlaceDescription oldItem, int position) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("View/Modify Place");
        dialog.setContentView(R.layout.activity_add);

        TextView textMessage = (TextView) dialog.findViewById(R.id.textMessage);
        textMessage.setText(getString(R.string.text_view_or_update));

        EditText editTextName = (EditText) dialog.findViewById(R.id.editTextName);
        editTextName.setText(oldItem.getName());

        EditText editTextDescription = (EditText) dialog.findViewById(R.id.editTextDescription);
        editTextDescription.setText(oldItem.getDescription());

        EditText editTextCategory = (EditText) dialog.findViewById(R.id.editTextCategory);
        editTextCategory.setText(oldItem.getCategory());

        EditText editTextAddressTitle = (EditText) dialog.findViewById(R.id.editTextAddressTitle);
        editTextAddressTitle.setText(oldItem.getAddressTitle());

        EditText editTextAddressStreet = (EditText) dialog.findViewById(R.id.editTextAddressStreet);
        editTextAddressStreet.setText(oldItem.getAddressStreet());

        EditText editTextElevation = (EditText) dialog.findViewById(R.id.editTextElevation);
        editTextElevation.setText(oldItem.getElevation().toString());

        EditText editTextLatitude = (EditText) dialog.findViewById(R.id.editTextLatitude);
        editTextLatitude.setText(oldItem.getLatitude().toString());
        editTextLatitude.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);

        EditText editTextLongitude = (EditText) dialog.findViewById(R.id.editTextLongitude);
        editTextLongitude.setText(oldItem.getLongitude().toString());

        Button buttonSubmit = (Button) dialog.findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(v -> {
            oldItem.setName(editTextName.getText().toString());
            oldItem.setDescription(editTextDescription.getText().toString());
            oldItem.setCategory(editTextCategory.getText().toString());
            oldItem.setAddressTitle(editTextAddressTitle.getText().toString());
            oldItem.setAddressStreet(editTextAddressStreet.getText().toString());

            String elevationString = editTextElevation.getText().toString();
            String latitudeString = editTextLatitude.getText().toString();
            String longitudeString = editTextLongitude.getText().toString();
            oldItem.setElevation(elevationString != null && !elevationString.isEmpty() ? Double.parseDouble(elevationString) : 0);
            oldItem.setLatitude(latitudeString != null && !latitudeString.isEmpty() ? Double.parseDouble(latitudeString) : 0);
            oldItem.setLongitude(longitudeString != null && !longitudeString.isEmpty() ? Double.parseDouble(longitudeString) : 0);
            placeLibrary.set(position, oldItem);
            mAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<PlaceDescriptionAdapter> getPlaceDescriptionAdapters() {
        return placeLibrary.getPlaceDescriptions().stream()
                .map(PlaceDescriptionAdapter::new).collect(Collectors.toList());
    }
}
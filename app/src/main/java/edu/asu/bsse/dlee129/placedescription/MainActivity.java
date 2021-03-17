package edu.asu.bsse.dlee129.placedescription;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
    private PlaceLibrary placeLibrary;
    private PlaceDescriptionAdapter mAdapter;
    private Button buttonAdd;
    private Button buttonRemove;
    private EditText editTextRemove;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlaceLibrary();
        buildRecyclerView();

        buttonAdd = findViewById(R.id.button_add);
        buttonRemove = findViewById(R.id.button_remove);
        editTextRemove = findViewById(R.id.edittext_remove);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
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

    public void clickItem(int position) {
        PlaceDescription placeDescription = placeLibrary.getPlaceDescriptions().get(position);
        mAdapter.notifyItemChanged(position);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("placeDescription.name", placeDescription.getName());
        intent.putExtra("placeDescription.description", placeDescription.getDescription());
        intent.putExtra("placeDescription.category", placeDescription.getCategory());
        intent.putExtra("placeDescription.addressTitle", placeDescription.getAddressTitle());
        intent.putExtra("placeDescription.addressStreet", placeDescription.getAddressStreet());
        intent.putExtra("placeDescription.elevation", placeDescription.getElevation());
        intent.putExtra("placeDescription.latitude", placeDescription.getLatitude());
        intent.putExtra("placeDescription.longitude", placeDescription.getLongitude());
        startActivity(intent);
    }

    public void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PlaceDescriptionAdapter(placeLibrary.getPlaceDescriptions());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this::clickItem);
    }

    public void addItem() {
        int position = placeLibrary.size();
        if (placeLibrary.add(new PlaceDescription("New Item at Position " + position, "This is a description"))) {
            mAdapter.notifyItemInserted(position);
        }
    }

    public void removeItem(int position) {
        if (placeLibrary.remove(position) != null) {
            mAdapter.notifyItemRemoved(position);
        }
    }
}
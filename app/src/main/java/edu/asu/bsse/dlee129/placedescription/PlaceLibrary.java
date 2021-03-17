package edu.asu.bsse.dlee129.placedescription;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class PlaceLibrary {
    private final ArrayList<PlaceDescription> placeDescriptions = new ArrayList<>();

    public PlaceLibrary(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("places");
            for (int i = 0; i < arr.length(); i++) {
                placeDescriptions.add(new PlaceDescription(arr.getJSONObject(i).toString()));
            }
        } catch (JSONException e) {
            Log.w(getClass().getSimpleName(), "Error converting to/from JSON");
        }
    }

    public ArrayList<PlaceDescription> getPlaceDescriptions() {
        return placeDescriptions;
    }

    public void add(PlaceDescription placeDescription) {
        placeDescriptions.add(placeDescription);
    }

    public void remove(int position) {
        placeDescriptions.remove(position);
    }
}

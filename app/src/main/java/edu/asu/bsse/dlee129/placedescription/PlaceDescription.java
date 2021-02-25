package edu.asu.bsse.dlee129.placedescription;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
 * @version January 2021
 */
public class PlaceDescription {
    private String name = "";
    private String description = "";
    private String category = "";
    private String addressTitle = "";
    private String addressStreet = "";
    private Double elevation = 0.0;
    private Double latitude = 0.0;
    private Double longitude = 0.0;

    public PlaceDescription(String jsonStr) {
        try {
            JSONObject jo = new JSONObject(jsonStr);
            name = jo.getString("name");
            description = jo.getString("description");
            category = jo.getString("category");
            addressTitle = jo.getString("address-title");
            addressStreet = jo.getString("address-street");
            elevation = jo.getDouble("elevation");
            latitude = jo.getDouble("latitude");
            longitude = jo.getDouble("longitude");
        } catch (JSONException e) {
            Log.w(getClass().getSimpleName(), "Error converting to/from JSON");
        }
    }

    public String toJsonString() {
        String ret = "";
        try {
            JSONObject jo = new JSONObject();
            jo.put("name", name);
            jo.put("description", description);
            jo.put("category", category);
            jo.put("address-title", addressTitle);
            jo.put("address-street", addressStreet);
            jo.put("elevation", elevation);
            jo.put("latitude", latitude);
            jo.put("longitude", longitude);
            ret = jo.toString();
        } catch (JSONException e) {
            Log.w(getClass().getSimpleName(), "Error converting to/from JSON");
        }

        return ret;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public Double getElevation() {
        return elevation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}

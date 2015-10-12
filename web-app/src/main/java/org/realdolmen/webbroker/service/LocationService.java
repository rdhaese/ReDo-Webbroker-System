package org.realdolmen.webbroker.service;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.realdolmen.webbroker.json.JsonHelper;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

@Stateless
@LocalBean
public class LocationService {

    private static final String API_KEY = "AIzaSyBPANm9y_qFBTMSrZORJmjA7tPXqv-NbXE";

    @Inject
    JsonHelper helper;

    public String latLongToCountryCode(double lat, double lng) {
        String URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=" + API_KEY;
        String countryCode = null;
        try {
            JSONObject jsonObject = helper.readJsonFromUrl(URL);
            if(!"ok".equalsIgnoreCase(jsonObject.getString("status"))) {
                return "";
            }
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject formattedAddress = results.getJSONObject(0);
            JSONArray addressComponents = formattedAddress.getJSONArray("address_components");

            for (int i = 0; i < addressComponents.length() && countryCode == null; i++) {
                JSONObject component = addressComponents.getJSONObject(i);
                JSONArray types = component.getJSONArray("types");
                if("country".equals(types.getString(0))) {
                    countryCode = component.getString("short_name");
                }
            }
        } catch (IOException | JSONException e) {
            countryCode = "";
        }
        return countryCode;
    }

    public String countryCodeToContinent(String countryCode) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("regions.json");
        JSONObject regions = helper.readInputStreamToString(inputStream);
        try {
            return regions.getString(countryCode);
        } catch (JSONException e) {
            return "";
        }
    }

}

package org.realdolmen.webbroker.service;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

@Stateless
@LocalBean
public class LocationService {

    private static final String API_KEY = "AIzaSyBPANm9y_qFBTMSrZORJmjA7tPXqv-NbXE";

    public String latLongToCountryCode(double lat, double lng) {
        String URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=" + API_KEY;
        String countryCode = null;
        try {
            JSONObject jsonObject = readJsonFromUrl(URL);
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
        try {
            JSONObject regions = new JSONObject(convertStreamToString(inputStream));
            return regions.getString(countryCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

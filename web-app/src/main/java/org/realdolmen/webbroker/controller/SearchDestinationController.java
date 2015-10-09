package org.realdolmen.webbroker.controller;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.realdolmen.webbroker.model.Region;
import org.realdolmen.webbroker.repository.RegionRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@Named
@RequestScoped
public class SearchDestinationController implements Serializable {

    private static final String API_KEY = "AIzaSyBPANm9y_qFBTMSrZORJmjA7tPXqv-NbXE";
    @Inject
    RegionRepository repository;

    String selectedRegion = "";

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public void onPointSelect(PointSelectEvent event) {
        String URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + event.getLatLng().getLat() + "," + event.getLatLng().getLng() + "&key=" + API_KEY;
        try {
            JSONObject jsonObject = readJsonFromUrl(URL);
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Region> getAllRegions() {
        return repository.getAllRegions();
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }
}

package org.realdolmen.webbroker.controller;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.RegionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Named
@javax.faces.view.ViewScoped
public class SearchDestinationController implements Serializable {

    private static final String API_KEY = "AIzaSyBPANm9y_qFBTMSrZORJmjA7tPXqv-NbXE";
    @Inject
    RegionRepository repository;

    String selectedRegion = "";

    private List<Airport> availableDestinations = new ArrayList<>();

    private Long selectedDestination = -1L;

    @Inject
    private AirportRepository airportRepository;

    public Long getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedDestination(Long selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public List<Airport> getAvailableDestinations() {
        return availableDestinations;
    }

    public void setAvailableDestinations(List<Airport> availableDestinations) {
        this.availableDestinations = availableDestinations;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
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
            if(!"ok".equalsIgnoreCase(jsonObject.getString("status"))) {
                return;
            }
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject formattedAddress = results.getJSONObject(0);
            JSONArray addressComponents = formattedAddress.getJSONArray("address_components");

            String countryCode = null;
            for (int i = 0; i < addressComponents.length() && countryCode == null; i++) {
                JSONObject component = addressComponents.getJSONObject(i);
                JSONArray types = component.getJSONArray("types");
                if("country".equals(types.getString(0))) {
                    countryCode = component.getString("short_name");
                }
            }

            String continent = countryCodeToContinent(countryCode);
            selectedRegion = continent;
            availableDestinations = airportRepository.getAirportsInContinent(continent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private String countryCodeToContinent(String countryCode) throws JSONException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("regions.json");
        JSONObject regions = new JSONObject(convertStreamToString(inputStream));
        return regions.getString(countryCode);
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }
}

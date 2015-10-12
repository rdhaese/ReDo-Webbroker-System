package org.realdolmen.webbroker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.realdolmen.webbroker.json.JsonHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Mocking test for the {@link LocationService}.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    JsonHelper helper;

    @InjectMocks
    LocationService service;

    @Test
    public void canResolveLatLongToCountryCode() throws Exception {
        when(helper.readJsonFromUrl(any())).thenReturn(createContinentJson());
        String countryCode = service.latLongToCountryCode(-2, 40);

        assertEquals("US", countryCode);
    }

    @Test
    public void cannotResolveLatLongWhichIsNotACountry() throws Exception {
        JSONObject jsonObject = new JSONObject("{results: {status: no_results}}");
        when(helper.readJsonFromUrl(any())).thenReturn(jsonObject);
        String countryCode = service.latLongToCountryCode(-2, 40);

        assertEquals("", countryCode);
    }

    @Test
    public void cannotResolveLatLongIfJSONException() throws Exception {
        when(helper.readJsonFromUrl(any())).thenThrow(new JSONException(""));
        String countryCode = service.latLongToCountryCode(-2, 40);

        assertEquals("", countryCode);
    }

    @Test
    public void canConvertCountryToContinent() throws Exception {
        when(helper.readInputStreamToString(any())).thenReturn(createCountryCodeJson());
        assertEquals("Europe", service.countryCodeToContinent("AD"));
        assertEquals("Asia", service.countryCodeToContinent("AF"));
    }

    @Test
    public void convertInvalidCountryToContinentReturnsEmptyString() throws Exception {
        when(helper.readInputStreamToString(any())).thenReturn(createCountryCodeJson());
        String continent = service.countryCodeToContinent("sqdze");
        assertTrue(continent.isEmpty());
    }

    private JSONObject createCountryCodeJson() {
        try {
            return new JSONObject("{\n" +
                    "  \"AD\": \"Europe\",\n" +
                    "  \"AE\": \"Asia\",\n" +
                    "  \"AF\": \"Asia\",\n" +
                    "  \"AG\": \"North America\",\n" +
                    "  \"AI\": \"North America\"}");
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONObject createContinentJson() {
        try {
            return new JSONObject("{\n" +
                    "   \"results\" : [\n" +
                    "      {\n" +
                    "         \"address_components\" : [\n" +
                    "            {\n" +
                    "               \"long_name\" : \"1600\",\n" +
                    "               \"short_name\" : \"1600\",\n" +
                    "               \"types\" : [ \"street_number\" ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"long_name\" : \"Amphitheatre Pkwy\",\n" +
                    "               \"short_name\" : \"Amphitheatre Pkwy\",\n" +
                    "               \"types\" : [ \"route\" ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"long_name\" : \"Mountain View\",\n" +
                    "               \"short_name\" : \"Mountain View\",\n" +
                    "               \"types\" : [ \"locality\", \"political\" ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"long_name\" : \"Santa Clara County\",\n" +
                    "               \"short_name\" : \"Santa Clara County\",\n" +
                    "               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"long_name\" : \"California\",\n" +
                    "               \"short_name\" : \"CA\",\n" +
                    "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"long_name\" : \"United States\",\n" +
                    "               \"short_name\" : \"US\",\n" +
                    "               \"types\" : [ \"country\", \"political\" ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"long_name\" : \"94043\",\n" +
                    "               \"short_name\" : \"94043\",\n" +
                    "               \"types\" : [ \"postal_code\" ]\n" +
                    "            }\n" +
                    "         ],\n" +
                    "         \"formatted_address\" : \"1600 Amphitheatre Parkway, Mountain View, CA 94043, USA\",\n" +
                    "         \"geometry\" : {\n" +
                    "            \"location\" : {\n" +
                    "               \"lat\" : 37.4224764,\n" +
                    "               \"lng\" : -122.0842499\n" +
                    "            },\n" +
                    "            \"location_type\" : \"ROOFTOP\",\n" +
                    "            \"viewport\" : {\n" +
                    "               \"northeast\" : {\n" +
                    "                  \"lat\" : 37.4238253802915,\n" +
                    "                  \"lng\" : -122.0829009197085\n" +
                    "               },\n" +
                    "               \"southwest\" : {\n" +
                    "                  \"lat\" : 37.4211274197085,\n" +
                    "                  \"lng\" : -122.0855988802915\n" +
                    "               }\n" +
                    "            }\n" +
                    "         },\n" +
                    "         \"place_id\" : \"ChIJ2eUgeAK6j4ARbn5u_wAGqWA\",\n" +
                    "         \"types\" : [ \"street_address\" ]\n" +
                    "      }\n" +
                    "   ],\n" +
                    "   \"status\" : \"OK\"\n" +
                    "}");
        } catch (JSONException e) {
            return null;
        }
    }
}

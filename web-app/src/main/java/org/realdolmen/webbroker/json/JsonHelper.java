package org.realdolmen.webbroker.json;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by YFMAX32 on 12/10/2015.
 */
@LocalBean
@Stateless
public class JsonHelper {

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public JSONObject readInputStreamToString(InputStream is) {
        try {
            return new JSONObject(convertStreamToString(is));
        } catch (JSONException e) {
            return null;
        }
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

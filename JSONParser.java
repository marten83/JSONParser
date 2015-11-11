import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maroln on 15-11-11.
 */

public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONObject getJSONFromUrl(String url) {

        URL getUrl = null;
        try {
            getUrl = new URL(url);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            assert getUrl != null;
            urlConnection = (HttpURLConnection) getUrl.openConnection();
        }catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert urlConnection != null;
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            json = sb.toString();
            urlConnection.disconnect();
        }catch (Exception e) {
            return null;
        }

        try {
            jObj = new JSONObject(json);
        }catch (JSONException e) {
            return null;
        }
        return jObj;
    }
}
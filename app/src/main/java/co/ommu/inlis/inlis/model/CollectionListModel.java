package co.ommu.inlis.inlis.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectionListModel {
    public String id, location, status, number_barcode;

    // Decodes array of json results into model objects
    public static ArrayList<CollectionListModel> fromJson(JSONArray ja) {
        JSONObject objectJson;
        ArrayList<CollectionListModel> array = new ArrayList<CollectionListModel>(ja.length());

        // Process each result in json array, decode and convert to business object
        try {
            for (int i = 0; i < ja.length(); i++) {
                CollectionListModel item = new CollectionListModel();
                item.id = ja.getJSONObject(i).getString("id");
                item.location = ja.getJSONObject(i).getString("location");
                item.status = ja.getJSONObject(i).getString("status");
                item.number_barcode = ja.getJSONObject(i).getString("number_barcode");
                array.add(item);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("infffffooo", "ada parsingan yg salah");

        }
        return array;
    }
}

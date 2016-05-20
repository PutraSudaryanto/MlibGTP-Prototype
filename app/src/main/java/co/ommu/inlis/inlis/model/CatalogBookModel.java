package co.ommu.inlis.inlis.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CatalogBookModel {
    public String id, title, author, publisher, publish_location,
            publish_year, subject, isbn, callnumber, worksheet, paging,
            sizes, description, note, cover;


    // Decodes array of json results into model objects
    public static ArrayList<CatalogBookModel> fromJson(JSONArray ja) {
        JSONObject objectJson;
        ArrayList<CatalogBookModel> array = new ArrayList<CatalogBookModel>(ja.length());

        // Process each result in json array, decode and convert to business object
        try {
            for (int i = 0; i < ja.length(); i++) {
                CatalogBookModel item = new CatalogBookModel();
                item.id = ja.getJSONObject(i).getString("id");
                item.title = ja.getJSONObject(i).getString("title");
                item.author = ja.getJSONObject(i).getString("author");
                item.publisher = ja.getJSONObject(i).getString("publisher");
                item.publish_location = ja.getJSONObject(i).getString("publish_location");
                item.publish_year = ja.getJSONObject(i).getString("publish_year");
                item.subject = ja.getJSONObject(i).getString("subject");
                item.isbn = ja.getJSONObject(i).getString("isbn");
                item.callnumber = ja.getJSONObject(i).getString("callnumber");
                item.worksheet = ja.getJSONObject(i).getString("worksheet");
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

package co.ommu.inlis.inlis.model;

import org.json.JSONArray;

import java.util.ArrayList;

public class TrackMemberModel {
    public String catalog_id, creation_date, title, author, publisher, publish_location, publish_year, subject, count;


    // Decodes array of json results into model objects
    public static ArrayList<TrackMemberModel> fromJson(JSONArray ja, Boolean status) {

        ArrayList<TrackMemberModel> array = new ArrayList<TrackMemberModel>(ja.length());

        // Process each result in json array, decode and convert to business object
        try {
            for (int i = 0; i < ja.length(); i++) {
                TrackMemberModel item = new TrackMemberModel();
                item.catalog_id = ja.getJSONObject(i).getString("catalog_id");

                if (!status) {
                    // punya biasa track, popolar
                    item.count = ja.getJSONObject(i).getString("count");
                } else {
                    // punya member , bookmark, like, view, favorit
                    item.creation_date = ja.getJSONObject(i).getString("creation_date");
                }

                item.title = ja.getJSONObject(i).getString("title");
                item.author = ja.getJSONObject(i).getString("author");
                item.publisher = ja.getJSONObject(i).getString("publisher");
                item.publish_location = ja.getJSONObject(i).getString("publish_location");
                item.publish_year = ja.getJSONObject(i).getString("publish_year");
                item.subject = ja.getJSONObject(i).getString("subject");
                array.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }
}

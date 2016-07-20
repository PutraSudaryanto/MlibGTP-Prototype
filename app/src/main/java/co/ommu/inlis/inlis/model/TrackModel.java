package co.ommu.inlis.inlis.model;

import org.json.JSONArray;

import java.util.ArrayList;

public class TrackModel {
    public String catalog_id, title, author, publish_year, publisher, publish_location, subject, bookmark, favourite, like, share;  //inlis global
    public String count;  //inlis loan
    public String creation_date;  //inlis track (view, bookmark, favourite, likes)

    // Decodes array of json results into model objects
    public static ArrayList<TrackModel> fromJson(JSONArray ja, Boolean status) {

        ArrayList<TrackModel> array = new ArrayList<TrackModel>(ja.length());

        // Process each result in json array, decode and convert to business object
        try {
            for (int i = 0; i < ja.length(); i++) {
                TrackModel item = new TrackModel();
                item.catalog_id = ja.getJSONObject(i).getString("catalog_id");

                if (!status) //inlis loan
                    item.count = ja.getJSONObject(i).getString("count");
                else //inlis track (view, bookmark, favourite, likes)
                    item.creation_date = ja.getJSONObject(i).getString("creation_date");

                item.title = ja.getJSONObject(i).getString("title");
                item.author = ja.getJSONObject(i).getString("author");
                item.publish_year = ja.getJSONObject(i).getString("publish_year");
                item.publisher = ja.getJSONObject(i).getString("publisher");
                item.publish_location = ja.getJSONObject(i).getString("publish_location");
                item.bookmark = ja.getJSONObject(i).getString("bookmark");
                item.favourite = ja.getJSONObject(i).getString("favourite");
                item.like = ja.getJSONObject(i).getString("like");
                item.share = ja.getJSONObject(i).getString("share");
                array.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }
}

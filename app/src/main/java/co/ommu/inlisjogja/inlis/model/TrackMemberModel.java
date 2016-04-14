package co.ommu.inlisjogja.inlis.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrackMemberModel {
	public String catalog_id;
	public String creation_date;
	public String title;
	public String author;
    public String publisher;
    public String publish_location;
	public String publish_year;
	public String subject;

    // Decodes json into model object
    public static TrackMemberModel toJsonObject(JSONObject jsonObject) {
        TrackMemberModel item = new TrackMemberModel();
        // Deserialize json into object fields
        try {
            item.catalog_id = jsonObject.getString("catalog_id");
            item.creation_date = jsonObject.getString("creation_date");
            item.title = jsonObject.getString("title");
            item.author = jsonObject.getString("author");
            item.publisher = jsonObject.getString("publisher");
            item.publish_location = jsonObject.getString("publish_location");
            item.publish_year = jsonObject.getString("publish_year");
            item.subject = jsonObject.getString("subject");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return item;
    }

    // Decodes array of json results into model objects
    public static ArrayList<TrackMemberModel> fromJson(JSONArray jsonArray) {
        JSONObject objectJson;
        ArrayList<TrackMemberModel> array = new ArrayList<TrackMemberModel>(jsonArray.length());

        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                objectJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            TrackMemberModel item = TrackMemberModel.toJsonObject(objectJson);
            if (item != null) {
                array.add(item);
            }
        }

        return array;
    }
}

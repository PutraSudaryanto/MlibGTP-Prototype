package co.ommu.inlisjogja.inlis.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopularModel {
	public String catalog_id;
	public String count;
	public String title;
	public String author;
	public String publish_year;
	public String publisher;
	public String publish_location;
	public String subject;

    // Decodes json into model object
    public static PopularModel toJsonObject(JSONObject jsonObject) {
        PopularModel item = new PopularModel();
        // Deserialize json into object fields
        try {
            item.catalog_id = jsonObject.getString("catalog_id");
            item.count = jsonObject.getString("count");
            item.title = jsonObject.getString("title");
            item.author = jsonObject.getString("author");
            item.publish_year = jsonObject.getString("publish_year");
            item.publisher = jsonObject.getString("publisher");
            item.publish_location = jsonObject.getString("publish_location");
            item.subject = jsonObject.getString("subject");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return item;
    }

    // Decodes array of json results into model objects
    public static ArrayList<PopularModel> fromJson(JSONArray jsonArray) {
        JSONObject objectJson;
        ArrayList<PopularModel> array = new ArrayList<PopularModel>(jsonArray.length());

        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                objectJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            PopularModel item = PopularModel.toJsonObject(objectJson);
            if (item != null) {
                array.add(item);
            }
        }

        return array;
    }
}

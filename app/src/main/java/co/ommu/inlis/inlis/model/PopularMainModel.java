package co.ommu.inlis.inlis.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopularMainModel {
	public String catalog_id, loans, title,author,publish_year,cover;

	public static ArrayList<PopularMainModel> fromJson(JSONArray ja) {
		JSONObject objectJson;
		ArrayList<PopularMainModel> array = new ArrayList<PopularMainModel>(ja.length());

		// Process each result in json array, decode and convert to business object
		try {
			for (int i = 0; i < ja.length(); i++) {
				PopularMainModel item = new PopularMainModel();
				item.catalog_id = ja.getJSONObject(i).getString("catalog_id");
				item.loans = ja.getJSONObject(i).getString("loans");
				item.title = ja.getJSONObject(i).getString("title");
				item.author = ja.getJSONObject(i).getString("author");
				item.publish_year = ja.getJSONObject(i).getString("publish_year");
				item.cover = ja.getJSONObject(i).getString("cover");
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

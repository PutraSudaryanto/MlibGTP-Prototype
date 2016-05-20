package co.ommu.inlis.inlis.model;

import java.util.ArrayList;

/**
 * Created by KurniawanD on 13-04-2016.
 */
public class SectionBookModel {



    private String headerTitle;
    private ArrayList<SingleBookItemModel> allItemsInSection;


    public SectionBookModel() {

    }
    public SectionBookModel(String headerTitle, ArrayList<SingleBookItemModel> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleBookItemModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<SingleBookItemModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}

package co.ommu.inlisjogja.inlis.model;

/**
 * Created by KurniawanD on 13-04-2016.
 */
public class SingleBookItemModel {


    private String name;
    private String url;
    private String description;


    public SingleBookItemModel() {
    }

    public SingleBookItemModel(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

package Model;

/**
 * Created by PC on 5/31/2018.
 */

public class Food {

    private String name;
    private String brief;
    private String price;
    private String address;
    private String code;
    private String imgUrl;
    private int like_num;
    private int dislike_num;

    public Food() {

    }

    public Food(String name, String brief, String price, String address, String code, String imgUrl) {
        this.name = name;
        this.brief = brief;
        this.price = price;
        this.address = address;
        this.code = code;
        this.imgUrl = imgUrl;
    }

    public String getBrief() {

        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getDislike_num() {
        return dislike_num;
    }

    public void setDislike_num(int dislike_num) {
        this.dislike_num = dislike_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String image) {
        this.imgUrl = image;
    }

}

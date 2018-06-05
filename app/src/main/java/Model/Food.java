package Model;

import android.media.Image;

/**
 * Created by PC on 5/31/2018.
 */

public class Food {

    private String name;
    private String foody;
    private String code;

    public  Food(){

    }

    public Food(String name, String foody, String code) {
        this.name = name;
        this.foody = foody;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getFoody() {
        return foody;
    }

    public String getCode() {
        return code;
    }

   /* public Image getImage() {
        return image;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setFoody(String foody) {
        this.foody = foody;
    }

    public void setCode(String code) {
        this.code = code;
    }

  /*  public void setImage(Image image) {
        this.image = image;
    }*/
}

package za.co.astroweb.remotepopup;

/**
 * Created by vaugenwakeling on 2017/12/07.
 */

public class PopupViewModel {

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String imageUrl;

    public PopupViewModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

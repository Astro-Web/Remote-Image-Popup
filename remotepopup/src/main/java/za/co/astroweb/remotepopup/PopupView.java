package za.co.astroweb.remotepopup;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by vaugenwakeling on 2017/12/07.
 */

public class PopupView {

    public interface PopupListener {
        void onPopupStarted();
        void onPopupClosed();
    }

    private PopupListener listener;
    private Context context;
    private Activity activity;
    private PopupViewOverlay view;
    private String title;
    private ArrayList<PopupViewModel> items;

    /*
    * Constructor
    * */
    public PopupView(Context context, Activity activity, PopupViewOverlay view, String title, ArrayList<PopupViewModel> items) {
        this.context = context;
        this.activity = activity;
        this.view = view;
        this.title = title;
        this.items = items;
    }

    // Set the listener
    public void setListener(PopupListener listener) {
        this.listener = listener;
    }

    // **** For dev purposes only
    public void setupAdapterContent() {

        ArrayList<PopupViewModel> arrayList = new ArrayList<>();

        arrayList.add(new PopupViewModel("https://www.ichs.com/wp-content/uploads/2015/12/Randon-Aea-Website-Photo-e1507329954811-512x512.jpg"));
        arrayList.add(new PopupViewModel("https://www.volusion.com/assets/images/company-RandonKelly.png"));
        arrayList.add(new PopupViewModel("https://pbs.twimg.com/profile_images/458901650153811970/OoATQZSv.jpeg"));

        this.items = arrayList;

    }

    // Draw the popup interfaces
    private void drawPopup() {

        view.overlayTitle.setText(this.title);

        view.setVisibility(View.VISIBLE);

        view.setListener(new PopupViewOverlay.PopupViewOverlayListener() {
            @Override
            public void onClose() {
                closePopup();
            }

            @Override
            public void onOpen() {

            }
        });

        view.setOnTouchListener(new OnSwipeListener(context) {

            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                closePopup();
            }
        });


    }

    private void setupImagesListView() {

        PopupViewRecycleAdapter adapter = new PopupViewRecycleAdapter(context, items);

        view.imagesRecycleView.setAdapter(adapter);

    }

    public void openPopup() {
        this.drawPopup();
        this.setupImagesListView();
        if (listener != null) listener.onPopupStarted();
    }

    private void closePopup() {
        view.setVisibility(View.GONE);
        if (listener != null) listener.onPopupClosed();
    }

    /*
    * Adapter for the recycler view
    * */

    private class PopupViewRecycleAdapter extends RecyclerView.Adapter<PopupViewRecycleAdapter.SimpleViewHolder> {

        private Context context;
        private ArrayList<PopupViewModel> items;

        public PopupViewRecycleAdapter(Context context, ArrayList<PopupViewModel> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(context).inflate(R.layout.popup_layout_item, parent, false);
            SimpleViewHolder viewHolder = new SimpleViewHolder(v);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {

            String item = items.get(position).getImageUrl();

            Log.d("Image URL", item);

            Glide.with(context)
                    .load(item)
                    .apply(new RequestOptions()
                    .centerCrop().centerInside()
                    )
                    .into(holder.image);

        }

        @Override
        public int getItemCount() {

            Log.d("Item Count", ""+items.size());

            if (items == null || items.size() <= 0) {
                return 0;
            } else
                return items.size();
        }

        // View holder
        public class SimpleViewHolder extends RecyclerView.ViewHolder{
            public ImageView image;
            public SimpleViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
            }
        }
    }

}

package za.co.astroweb.remotepopup;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by vaugenwakeling on 2017/12/07.
 */

public class PopupViewOverlay extends RelativeLayout {

    public interface PopupViewOverlayListener {
        void onClose();
        void onOpen();
    }

    private LayoutInflater inflater;
    private LinearLayout viewBackground;
    private ImageButton clsBtn;
    public TextView overlayTitle;
    public RecyclerView imagesRecycleView;
    private PopupViewOverlayListener listener;
    private Context context;

    public PopupViewOverlay(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        this.context = context;
        init();
    }

    public PopupViewOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        this.context = context;
        init();
    }

    public PopupViewOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PopupViewOverlay(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        View v = inflater.inflate(R.layout.popup_layout, this, true);

        clsBtn = v.findViewById(R.id.clsBtn);
        imagesRecycleView = v.findViewById(R.id.imagesRecycleView);
        viewBackground = v.findViewById(R.id.overlayViewHolder);
        overlayTitle = v.findViewById(R.id.popupTitle);

        //viewBackground.getBackground().setAlpha(90);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        int animationID = R.anim.layout_animation_fall_down;

        imagesRecycleView.setLayoutManager(mLayoutManager);
        imagesRecycleView.setItemAnimator(new DefaultItemAnimator());

        clsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView();
            }
        });
    }

    // Set the listener
    public void setListener(PopupViewOverlayListener listener) {
        this.listener = listener;
    }

    public void closeView() {
        if (listener != null) listener.onClose();
    }

}

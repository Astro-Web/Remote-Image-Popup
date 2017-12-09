package za.co.astroweb.popupimagelist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*
import za.co.astroweb.remotepopup.PopupView
import za.co.astroweb.remotepopup.PopupViewOverlay

class MainActivity : AppCompatActivity(), PopupView.PopupListener {

    var openBtn : Button? = null;
    var overlayView : PopupViewOverlay? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI Properties
        openBtn = findViewById(R.id.openBtn)
        overlayView = findViewById(R.id.overlayView)

        var popupView : PopupView = PopupView(this, this, overlayView, "Vaugen Wakeling", null)
        popupView.setupAdapterContent()

        openBtn!!.setOnClickListener { view ->
            popupView.openPopup()
        }

    }

    /*
    * Listener properties for the popup.
    * */

    override fun onPopupStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPopupClosed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

package net.anzix.android.vonalkod;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import javax.naming.directory.SearchResult;

public class VonalkoderActivity extends Activity implements OnClickListener {

    Button btn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn = (Button) findViewById(R.id.libri);
        btn.setOnClickListener(this);
    }

    protected boolean isEmulator() {
        return android.os.Build.DEVICE.equals("generic") && android.os.Build.BRAND.equals("generic");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            searchBook(scanResult.getContents());
        }
    }

    public void searchBook(String isbn) {
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.libri.hu/talalati_lista/?reszletes=1&s_det=1&isbn=" + isbn + "&x=51&y=24"));
        startActivity(viewIntent);
    }

    public void onClick(View view) {
        if (isEmulator()) {
            searchBook("9789631186192");
        } else {
            IntentIntegrator.initiateScan(this);
        }
    }
}



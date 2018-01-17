package f4.protomaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapsActivity extends AppCompatActivity {

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    Boolean mLocationPermissionGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getLocationPermission();
        }
        loadMaps();
    }

    public void loadMaps() {
        WebView mapsWebView = findViewById(R.id.mapsView);
        mapsWebView.setWebChromeClient(new android.webkit.WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, android.webkit.GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        mapsWebView.setWebViewClient(new WebViewClient());

        // Tell the WebView that we accept cookies
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(mapsWebView, true);
            CookieManager.allowFileSchemeCookies();
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }

        // Accept JS
        WebSettings webSettings = mapsWebView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);

        // Load our PWA maps link
        mapsWebView.loadUrl("https://www.google.com/maps?force=qVTs2FOxxTmHHo79-pwa&amp;source=mlapk");
    }

    private void getLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_DENIED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                }
            }
        }
    }
}

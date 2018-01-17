package f4.protomaps;

import android.os.Build;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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
}

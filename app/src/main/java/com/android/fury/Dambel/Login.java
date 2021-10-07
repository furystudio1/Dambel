package com.android.fury.Dambel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.util.IabHelper;
import com.android.billingclient.util.IabResult;
import com.android.billingclient.util.Inventory;
import com.android.billingclient.util.Purchase;
import com.android.billingclient.util.SkuDetails;
import com.android.fury.Dambel.loadtoast.LoadToast;
import com.android.fury.Dambel.page.Page_2;
import com.android.fury.Dambel.sport.sport;
import com.android.fury.Dambel.sport.sport_start;
import com.android.fury.Dambel.sport.sport_v;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fury on 5/1/2017.
 */
public class Login extends Activity {

    TextView welcome_txt, send;
    String s, mobile;
    static String codeSms;
    int how;
    static int tr;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    // The helper object
    private IabHelper mHelper;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Create the helper, passing it our context and the public key to verify signatures with Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(this, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVyidxffI5Lv+2c0u44SL9OfeF0Rbii0EDtNQkAmn4TiguKyk45MhMqlH9nOx03Z16RAdLSw/c7CAXZZtgJiJWhehS5p4ad2MaFCh+e0Mo5vFJi0Xkxu4M/5rBfGpt1MSc9uQ2Li5ZJu2X2NnbB54Jf67LVZjj91FfVJgcPsUXHwIDAQAB");


        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        welcome_txt = (TextView) findViewById(R.id.welcome_txt);
        send = (TextView) findViewById(R.id.send);
        how = 1;
        try {

            Animation day = AnimationUtils.loadAnimation(Login.this, R.anim.in_start);
            welcome_txt.startAnimation(day);

        } catch (Exception e) {
        }


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String payload = "";

                try {
                    mHelper.launchSubscriptionPurchaseFlow(Login.this, "Dambel_123", 10001, mPurchaseFinishedListener, payload);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    ///
                }

            }
        });


        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {             // Oh noes, there was a problem
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    //
                }
                // IAB is fully set up. Now
            }
        });

    }


    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                return;
            }

            SkuDetails skuDetails = inventory.getSkuDetails("Dambel_123");

            /** Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().*/

        }
    };

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private class GetData extends AsyncTask<String, String, String> {

        LoadToast lt = new LoadToast(Login.this);
        private String url = "http://79.175.164.51:8093/api/App/Verification";
        InputStream json;
        StringBuilder responseOutput;
        String mobil = s;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            lt.setText("در حال بررسی ...");
            lt.setProgressColor(getResources().getColor(R.color.color30day));
            lt.setTranslationY(100);
            lt.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("MobileNumber", mobil));
                params.add(new BasicNameValuePair("ServiceCode", "FitShow"));
                params.add(new BasicNameValuePair("AccessKey", sha256("VerificationFitShow" + mobil)));
                one_play_editor.putString("AccessKey", sha256("VerificationFitShow" + mobil));
                one_play_editor.apply();
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                json = httpEntity.getContent();

                BufferedReader br = new BufferedReader(new InputStreamReader(json));
                String line = "";
                responseOutput = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return responseOutput.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                try {


                    JSONObject jsonObject = new JSONObject(s);
                    String mobileNumber = jsonObject.getString("MobileNumber");
                    String status = jsonObject.getString("Status");
                    String activationCode = jsonObject.getString("ActivationCode");

                    if (Objects.equals(status, "Subscribed")) {
                        lt.success();
                        codeSms = activationCode;
                    } else if (Objects.equals(status, "NotSubscribed")) {
                        lt.error();
                    } else {
                        lt.error();
                        Toast.makeText(Login.this, "خطا !", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                }

            } else {
                lt.error();
                Toast.makeText(getApplicationContext(), "ارتباط با سرور برقرار نشد", Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mHelper == null) return;

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        } else {
        }
    }


    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                //
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                //
                return;
            }

            Log.d("buy", "Purchase successful.");

            if (purchase.getSku().equals("Dambel_123")) {
                // bought 1/4 tank of gas. So consume it.
                try {
                    mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    //
                    return;
                }
            }

        }
    };

    private boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHelper != null) {
            mHelper.disposeWhenFinished();
            mHelper = null;
        }
    }

    // Called when consumption is complete
    private IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...

            // successfully consumed, so we apply the effects of the item in our
            // game world's logic, which in our case means filling the gas tank a bit
            Toast.makeText(Login.this, "با موفقیت وارد شدید", Toast.LENGTH_SHORT).show();
            one_play_editor.putInt("one_play_app", 1);
            one_play_editor.putString("MobileNumber", mobile);
            one_play_editor.apply();
            Intent uou = new Intent(Login.this, Page_2.class);
            startActivity(uou);
            Login.this.finish();

        }
    };

}

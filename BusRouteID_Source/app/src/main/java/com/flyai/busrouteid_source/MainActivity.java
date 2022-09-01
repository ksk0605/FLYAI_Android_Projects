package com.flyai.busrouteid_source;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView objTV;
    private String strServiceUrl, strServiceKey, strRouteId;
    private StringBuilder strUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        objTV = (TextView) findViewById(R.id.txtTitle);

        strServiceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getRouteInfo";
        strServiceKey = "g8rSily3QsfV85PVU4LG17aXFZhY9jpLiSfdiOeimYuZcYP599Lm3xtepE5tzw6QZ5nN5ni8b3mDUdtKwvppwg%3D%3D";
        strRouteId = "100100063";

        //strUrl = strServiceUrl + "?ServiceKey=" + strServiceKey + "&busRouteId=" +strRouteId;
        strUrl = new StringBuilder("");
        strUrl.append(strServiceUrl);
        strUrl.append("?ServiceKey=" + strServiceKey);
        strUrl.append("&BusRouteId=" + strRouteId);

        DownloadWebpageTask objTask = new DownloadWebpageTask();
        objTask.execute(strUrl.toString());
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls){
            try {
                String strData = downloadUrl((String) urls[0]);
                return strData;
            } catch (IOException e) {
                return "Fail download !";
            }
        }

        protected void onPostExecute(String result) { objTV.setText(result);}

        private String downloadUrl(String myUrl) throws IOException{
            String strLine = null;
            String strPage = "";

            HttpURLConnection urlConn = null;
            try{
                URL url = new URL(myUrl);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setRequestProperty("Content-type", "application/json");

                BufferedReader bufReader;
                bufReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                while ((strLine = bufReader.readLine()) != null) {
                    strPage += strLine;
                }
                return strPage;
            } finally {
                urlConn.disconnect();
            }
        }
    }
}


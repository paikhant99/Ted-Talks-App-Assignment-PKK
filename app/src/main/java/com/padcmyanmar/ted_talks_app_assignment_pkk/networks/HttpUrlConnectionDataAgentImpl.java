package com.padcmyanmar.ted_talks_app_assignment_pkk.networks;

import android.os.AsyncTask;
import android.util.Log;

import com.padcmyanmar.ted_talks_app_assignment_pkk.utils.TedTalksConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paikhantko on 6/15/18.
 */

public class HttpUrlConnectionDataAgentImpl implements TedDataAgent {

    private static HttpUrlConnectionDataAgentImpl objInstance;

    private HttpUrlConnectionDataAgentImpl() {
    }

    public static HttpUrlConnectionDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {
                    url = new URL(TedTalksConstants.TED_TALKS_API_BASED_URL + TedTalksConstants.GET_TED_TALKS);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(15 * 1000);

                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    List<NameValuePair> params = new ArrayList<>(); //6.
                    params.add(new BasicNameValuePair(TedTalksConstants.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalksConstants.PARAM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    connection.connect(); //7.

                    // read the output from the server
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //8.
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString(); //9.

                    return responseString;
                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

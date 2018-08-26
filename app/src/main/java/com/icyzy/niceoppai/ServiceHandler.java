package com.icyzy.niceoppai;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class ServiceHandler extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.niceoppai.net");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(in);
                StringBuilder sb = new StringBuilder();
                Integer i = 1;
                try {
                    String s;
                    Boolean t = false;
                    while ((s = r.readLine()) != null) {
                        if (s.contains("<div class=\"wrap\">")) {
                            t = true;
                        } else if (s.contains("/body>")) {
                            t = false;
                        }
                        if (t) {
                            sb.append(String.valueOf(i)+": "+s);
                            sb.append("\n");
                            i++;
                        }
                    }
                } finally {
                    r.close();
                }
                Log.d("Test", String.valueOf(sb));
                Log.d("Line", String.valueOf(i));
                return null;
            } catch (Exception e) {

            }

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;

        }
        return null;
    }

}

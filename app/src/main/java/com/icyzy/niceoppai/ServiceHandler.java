package com.icyzy.niceoppai;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class ServiceHandler extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.niceoppai.net");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(in);
                ArrayList<String> lines = new ArrayList<String>();
                ArrayList<String> use = new ArrayList<String>();
//                StringBuilder sb = new StringBuilder();
                Integer i = 1;
                try {
                    String s;
                    Boolean t = false;
                    while ((s = r.readLine()) != null) {
                        if (s.contains("<div class=\"wpm_pag mng_lts_chp grp\">")) {
                            t = true;
                        } else if (s.contains("<div class=\"wpm_nav\">\n")) {
                            t = false;
                        }
                        if (t && !s.contains("</div>") && !s.contains("</a>") && !s.contains("</li>") && !s.contains("<li>") && !s.contains("</ul>") && (s.contains("href")) || s.contains("<img src")){
                            lines.add(s);
//                            sb.append("\n");
                            i++;
                        }
                    }
                } finally {
                    r.close();
                }
                for (int i1 = 9; i1 < lines.size(); i1++) {
                    use.add(lines.get(i1));
                }
                for (String us: use) {
                    Log.d("Test", us);
                }
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

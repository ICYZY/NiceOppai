package com.icyzy.niceoppai;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class ServiceHandler extends AsyncTask<Void, Void, String[]> {
    private ArrayList<String> use = new ArrayList<>();

    @Override
    protected String[] doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.niceoppai.net");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                ArrayList<String> lines = new ArrayList<>();
//                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(in)) {
                    String htmlTag;
                    Boolean t = false;
                    while ((htmlTag = reader.readLine()) != null) {
                        if (htmlTag.contains("<div class=\"wpm_pag mng_lts_chp grp\">")) {
                            t = true;
                        } else if (htmlTag.contains("<div class=\"wpm_nav\">\n")) {
                            t = false;
                        }
                        if (t && !htmlTag.contains("</div>") && !htmlTag.contains("</a>") && !htmlTag.contains("</li>") && !htmlTag.contains("<li>") && !htmlTag.contains("</ul>") && (htmlTag.contains("href")) || htmlTag.contains("<img src")) {
                            lines.add(htmlTag);
                        }
                    }
                }
                for (int i1 = 9; i1 < lines.size(); i1++) {
                    use.add(lines.get(i1));
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }
        return use.toArray(new String[use.size()]);
    }
}

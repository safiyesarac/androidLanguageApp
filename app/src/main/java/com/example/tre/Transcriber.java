package com.example.tre;


import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.concurrent.TimeUnit;


public class Transcriber {

    public static String sendRequest(String path ) throws Exception {
        String secret_key = "bb2fe680823a43d7aafb69619a149a0e";
        HttpURLConnection conn;
        String srtContent = " ";

        // endpoint and options to start a transcription task
        URL endpoint = new URL("https://api.speechtext.ai/recognize?key=" + secret_key +"&language=en-US&punctuation=true&format=m4a");

        // loads the audio into memory
        File file = new File("/sdcard/"+path);
        RandomAccessFile f = new RandomAccessFile(file, "r");
        long sz = f.length();
        byte[] post_body = new byte[(int) sz];
        f.readFully(post_body);
        f.close();

        // send an audio transcription request
        conn = (HttpURLConnection) endpoint.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/octet-stream");

        conn.setDoOutput(true);
        conn.connect();
        OutputStream os = conn.getOutputStream();
        os.write(post_body);
        os.flush();
        os.close();
        int responseCode = conn.getResponseCode();

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = in .readLine()) != null) {
                response.append(line);
            } in .close();
            String result = response.toString();
            JSONObject json = new JSONObject(result);
            // get the id of the speech recognition task
            String task = json.getString("id");
            Log.i("transcript","Task ID: " + task);
            // endpoint to check status of the transcription task
            URL res_endpoint = new URL("https://api.speechtext.ai/results?key=" +secret_key + "&task=" + task + "&summary=true&summary_size=15&highlights=true&max_keywords=15&output=srt");
            Log.i("transcript","Get transcription results, summary, and highlights");
            // use a loop to check if the task is finished
            JSONObject results;
            while (true) {
                conn = (HttpURLConnection) res_endpoint.openConnection();
                conn.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = new StringBuffer();
                String res;
                while ((res = in .readLine()) != null) {
                    response.append(res);
                } in .close();
                if(!response.toString().startsWith("{")){
                    Log.i("transcript",  response.toString());
                    srtContent=response.toString();
                    break;

                }else{

                results = new JSONObject(response.toString());}
                Log.i("transcript","Task status: " + results.getString("status"));
                if (results.getString("status").equals("failed")) {
                    Log.i("transcript","Failed to transcribe!");
                    break;
                }
                if ((results.getString("status").equals("finished"))) {

                    Log.i("transcript",  response.toString());
                    break;
                }
                // sleep for 15 seconds if the task has the status - 'processing'
                TimeUnit.SECONDS.sleep(15);
            }

        } else {

            Log.i("transcript","Failed to transcribe!");
        }
        return srtContent;

    }
}


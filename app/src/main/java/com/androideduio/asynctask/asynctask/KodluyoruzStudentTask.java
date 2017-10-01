package com.androideduio.asynctask.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.androideduio.asynctask.adapter.KodluyoruzStudentAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 24.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class KodluyoruzStudentTask extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>>> {

    // AsyncTask<A, B, C>
    // A = doInBackground'a verilecek parametre icin.
    // B = publishProgress'in tipi. dolayısıyla onProgressUpdate()'in parametresi
    // C = doInBackground geri donus degeri icin ve onPostExecute parametresi icin.

    private Activity activity = null;
    private ProgressDialog progressDialog = null;
    private KodluyoruzStudentAdapter adapter = null;
    private ArrayList<HashMap<String, String>> contactList = new ArrayList<>();

    public KodluyoruzStudentTask(Activity activity, KodluyoruzStudentAdapter adapter) {

        this.activity = activity;
        this.adapter = adapter;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        Toast.makeText(activity, "İndirme Başladı", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... param) {

        HttpHelper httpHelper = new HttpHelper();

        String url = param[0];

        String jsonStr = httpHelper.sendRequest(url);

        Log.e(TAG, "Response URL : " + jsonStr);

        if (jsonStr != null) {

            try {

                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray contacts = jsonObj.getJSONArray("contacts");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String phone = c.getString("phone");

                    // tmp hash map for single contact
                    HashMap<String, String> contact = new HashMap<>();

                    // adding each child node to HashMap key => value
                    contact.put("id", id);
                    contact.put("name", name);
                    contact.put("email", email);
                    contact.put("phone", phone);

                    // adding contact to contact list
                    contactList.add(contact);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();
                }
            });

        }

        for (int i = 0; i < 101; i = i + 10) {
            try {
                publishProgress(i);
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {

                Log.e("AsynTask", e.getMessage());
            }
        }

        return contactList;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Integer currentProgress = values[0];
        progressDialog.setProgress(currentProgress);
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        adapter.setListAndUpdateList(result);
    }
}
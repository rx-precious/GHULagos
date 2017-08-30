package com.preccytech.ghulagos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //variable holds the HTTP Url of java developers in lagos
    private static final String URL_DATA = "https://api.github.com/search/users?q=location:lagos+language:java";

    //Creating array for the list of words
    final ArrayList<DevelopersList> developers = new ArrayList<>();

  private DevelopersAdapter dev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create an arrayAdapter
      dev = new DevelopersAdapter(this, developers);

        DevelopersRequest();


    }

    public void DevelopersRequest() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Retrieving List...");
        progress.show();


        //Request a String response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                progress.dismiss();

                try {
                    // Create root Json Object
                    JSONObject jsonObj = new JSONObject(response);

                    JSONArray developersArray = jsonObj.getJSONArray("items");

                    //loop throuugh all developers provided by the search results
                    for (int i = 0; i < developersArray.length(); i++) {
                        //get the current/first developer info
                        JSONObject currentDeveloper = developersArray.getJSONObject(i);
                        String username = currentDeveloper.getString("login");
                        String userUrl = currentDeveloper.getString("html_url");
                        String userAvatar = currentDeveloper.getString("avatar_url");

                        DevelopersList developerList = new DevelopersList(username, userUrl, userAvatar);
                        developers.add(developerList);



                        //find the list view in the xml and set them yo an object name
                        ListView listView = (ListView) findViewById(R.id.list_developer);
                        // set the list view to the adapter created above
                        listView.setAdapter(dev);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_LONG).show();

            }

        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}




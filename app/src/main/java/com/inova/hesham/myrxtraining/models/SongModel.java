package com.inova.hesham.myrxtraining.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inova.hesham.myrxtraining.entities.Song;
import com.inova.hesham.myrxtraining.utilities.Constants;
import com.inova.hesham.myrxtraining.utilities.VollyCreator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class SongModel {
    private final String TAG = Song.class.getSimpleName();
    // method to get songs from backend
    public Single<ArrayList<Song>> getSongs(final Context mContext){
        return Single.create(new SingleOnSubscribe<ArrayList<Song>>() {
            @Override
            public void subscribe(final SingleEmitter<ArrayList<Song>> emitter) throws Exception {
                JsonObjectRequest  jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.API_URL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                ArrayList<Song> songList =  parseResponse(response);
                                emitter.onSuccess(songList);
                                Log.i("RX","succeeded");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        emitter.onError(error);
                        Log.i("RX","failed: "+ error.getLocalizedMessage());
                    }
                });

                // Add a request (in this example, called stringRequest) to your RequestQueue.
                VollyCreator.getInstance(mContext).addToRequestQueue(jsonObjectRequest);
            }
        });
    }

    private ArrayList<Song> parseResponse(JSONObject response){
        Gson gson = new Gson();
        ArrayList<Song> songs= new ArrayList<>();
        try {
            JSONArray jsonArray = response.getJSONArray("results");
            songs = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<Song>>(){}.getType());
        }catch (Exception ex){
            if (ex.getLocalizedMessage() != null)
                Log.i("RX", ex.getLocalizedMessage());
        }
        return songs;
    }
}

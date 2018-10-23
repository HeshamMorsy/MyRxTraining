package com.inova.hesham.myrxtraining.ui;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inova.hesham.myrxtraining.R;
import com.inova.hesham.myrxtraining.adapters.SongAdapter;
import com.inova.hesham.myrxtraining.entities.Song;
import com.inova.hesham.myrxtraining.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AndroidViewModel viewModel;
    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;
    private ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        ((MainViewModel) viewModel).liveData.observe(this, new Observer<ArrayList<Song>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Song> songs) {
                Log.i("RX",songs +" is the data..");
                if (songs != null) {
                    songList.clear();
                    songList.addAll(songs);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initViews(){
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mRecyclerView = findViewById(R.id.main_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
        songList = new ArrayList<>();
        mAdapter = new SongAdapter(songList, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}

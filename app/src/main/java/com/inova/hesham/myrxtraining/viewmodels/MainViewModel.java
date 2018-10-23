package com.inova.hesham.myrxtraining.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.inova.hesham.myrxtraining.entities.Song;
import com.inova.hesham.myrxtraining.models.SongModel;
import java.util.ArrayList;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
public class MainViewModel extends AndroidViewModel {
    // live data object
    public MutableLiveData<ArrayList<Song>> liveData = new MutableLiveData<>();
    public MainViewModel(Application application){
        super(application);
        new SongModel().getSongs(application.getApplicationContext()).subscribe(new SingleObserver<ArrayList<Song>>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
             disposable = d;
            }

            @Override
            public void onSuccess(ArrayList<Song> songs) {
                liveData.postValue(songs);
                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                liveData.postValue(new ArrayList<Song>());
                if (e.getLocalizedMessage()!= null)
                    Log.i("RX",e.getLocalizedMessage());
                disposable.dispose();
            }
        });
    }
}

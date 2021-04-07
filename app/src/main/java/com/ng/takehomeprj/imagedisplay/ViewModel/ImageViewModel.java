package com.ng.takehomeprj.imagedisplay.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ng.takehomeprj.imagedisplay.api.RetrofitClient;
import com.ng.takehomeprj.imagedisplay.models.ImageModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageViewModel extends ViewModel {
    private String TAG = ImageViewModel.class.getSimpleName();
    private MutableLiveData<List<String>> ImageData;


    public LiveData<List<String>> getImage(String id) {

            ImageData = new MutableLiveData<List<String>>();
            Call<ImageModel> call = RetrofitClient.getInstance().getMyApi().getImage(id);

            call.enqueue(new Callback<ImageModel>() {
                @Override
                public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                    List<String> respArray = new ArrayList<>();
                    if(response.code() != 200){
                        return;
                    }else{
                        respArray.add(response.body().getUrl());
                        respArray.add(response.body().getName());
                        int height = response.body().getHeight();
                        int width = (int) (height * 1.7);
                        respArray.add(String.valueOf(height));
                        respArray.add(String.valueOf(width));

                    }
                    ImageData.postValue(respArray);
                }
                @Override
                public void onFailure(Call<ImageModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        return ImageData;
    }
}

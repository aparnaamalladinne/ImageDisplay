package com.ng.takehomeprj.imagedisplay.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ng.takehomeprj.imagedisplay.api.RetrofitClient;
import com.ng.takehomeprj.imagedisplay.models.ManifestModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManifestViewModel extends ViewModel {
    private String TAG = ManifestViewModel.class.getSimpleName();

    private MutableLiveData<String[][]> ImageManifest;

    public LiveData<String[][]> getManifest() {
        if (ImageManifest == null) {
            ImageManifest = new MutableLiveData<>();
            Call<ManifestModel> call = RetrofitClient.getInstance().getMyApi().getManifest();

            call.enqueue(new Callback<ManifestModel>() {
                @Override
                public void onResponse(Call<ManifestModel> call, Response<ManifestModel> response) {
                    String[][] manifestList = (String[][]) response.body().getMfest();
                    ImageManifest.postValue(manifestList);
                }

                @Override
                public void onFailure(Call<ManifestModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        return ImageManifest;
    }
}

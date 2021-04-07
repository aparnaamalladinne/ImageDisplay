package com.ng.takehomeprj.imagedisplay.api;


import com.ng.takehomeprj.imagedisplay.models.ImageModel;
import com.ng.takehomeprj.imagedisplay.models.ManifestModel;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    String BASE_URL = "https://afternoon-bayou-28316.herokuapp.com/";

    @Headers("X-API-KEY: 33626b03-88b8-4c6e-af34-ac4e6f7faa7c")
    @GET("manifest")
        // API's endpoints
    Call<ManifestModel> getManifest();

    @Headers("X-API-KEY: 33626b03-88b8-4c6e-af34-ac4e6f7faa7c")
    @GET("image/{image_identifier}")
    Call<ImageModel> getImage(@Path("image_identifier") String image_identifier);



}


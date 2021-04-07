package com.ng.takehomeprj.imagedisplay;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.ng.takehomeprj.imagedisplay.ViewModel.ImageViewModel;
import com.ng.takehomeprj.imagedisplay.ViewModel.ManifestViewModel;

import java.util.List;

public class activity_image_show extends AppCompatActivity {
    private static final String TAG = "ImageDisplayActivity";
    private ImageViewModel imageViewModel;
    private ManifestViewModel manifestViewModel;
    private String[] manifest;
    int viewGroupId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewModel =  new ViewModelProvider(this).get(ImageViewModel.class);
        manifestViewModel =  new ViewModelProvider(this).get(ManifestViewModel.class);

        setContentView(R.layout.activity_image_show);
        Bundle extras = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View.OnClickListener  btnNextListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getManifest();
            }
        };

        if(extras != null){
             manifest = extras.getStringArray("manifest");
            int viewGroupId = extras.getInt("viewGroupId");
            Button btnNext = findViewById(R.id.btnNext);
            if(viewGroupId == 3){

                btnNext.setVisibility(View.GONE);
            }else{
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(btnNextListener);
            }
            Log.d(TAG,"Size of manifest "+manifest.length);
            int numOfImages = manifest.length;

            if(manifest.length > 1) {
                LinearLayout ll = findViewById(R.id.horizontalLayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                if(numOfImages == 3)
                    params.setMargins(40, 0, 0, 0);
                else if(numOfImages == 2)
                    params.setMargins(90,0,0,0);
                //add Pagecontrols as page controls dynamically to the page

                for (int i = 0; i < numOfImages; i++) {
                    Button myButton = new Button(this);
                    myButton.setText(manifest[i]);
                    myButton.setId(i);
                    myButton.setWidth(5);
                    myButton.setOnClickListener(listener);
                    ll.addView(myButton, params);

                }
            }
            getImageData(manifest[0]);
        }
    }
    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View btn) {
                getImageData(manifest[btn.getId()]);

            }

    };

    public void getManifest(){
        manifestViewModel.getManifest().observe(this,manifestnew-> {
            Log.d(TAG, "Size of manifest"+manifestnew.length);
            getImageData(manifestnew[viewGroupId+1][0]);
            manifest = manifestnew[viewGroupId+1];
            LinearLayout ll = findViewById(R.id.horizontalLayout);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if(ll != null){
                ll.removeAllViewsInLayout();
            }
            if(manifestnew[viewGroupId+1].length  == 3)
                params.setMargins(40, 0, 0, 0);
            else if(manifestnew[viewGroupId+1].length  == 2)
                params.setMargins(90,0,0,0);
            if(manifestnew[viewGroupId+1].length > 1){
                for (int i = 0; i < manifestnew[viewGroupId+1].length; i++) {
                    Button myButton = new Button(this);
                    myButton.setText(manifestnew[viewGroupId+1][i]);
                    myButton.setId(i);
                    myButton.setWidth(5);
                    myButton.setOnClickListener(listener);
                    ll.addView(myButton, params);

                }
            }
            viewGroupId++;

        });

    }
    public void getImageData(String imgIdentifer){
        Log.d(TAG, "Entered getImageData");
        //String[] images = new String[numofImages];
        imageViewModel.getImage(imgIdentifer).observe(this,image->{

            loadImage(image);

        });
    }
    public void  loadImage(List<String> image){
        ImageView imageView = findViewById(R.id.imageDisplay);
        TextView textForImage = findViewById(R.id.textForImage);
        Log.d(TAG,"Inload page"+image.size());
        int height = Integer.parseInt(image.get(2));
        int width = Integer.parseInt(image.get(3));

        Glide.with(getApplicationContext())
                .load(new GlideUrl(image.get(0), new LazyHeaders.Builder()
                        .addHeader("X-API-KEY", "33626b03-88b8-4c6e-af34-ac4e6f7faa7c")
                        .build()))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(width,height)
                        .centerCrop())
                .into(imageView);
        textForImage.setText(image.get(1));

    }

}
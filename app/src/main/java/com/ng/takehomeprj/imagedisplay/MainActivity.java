package com.ng.takehomeprj.imagedisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ng.takehomeprj.imagedisplay.ViewModel.ManifestViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ManifestViewModel manifestViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manifestViewModel =  new ViewModelProvider(this).get(ManifestViewModel.class);
        setContentView(R.layout.activity_main);
        getImageManifest();
    }
    public void getImageManifest(){
        Log.d(TAG, "Entered getImageManifest");
        manifestViewModel.getManifest().observe(this,manifest->{
            Log.d(TAG,"Printing manifest size "+manifest.length);
            OnClickListener listener = new OnClickListener() {
                Intent intent = null;
                @Override
                public void onClick(View btn) {
                            intent = new Intent(MainActivity.this,  activity_image_show.class);
                            intent.putExtra("manifest",manifest[btn.getId()]);
                            intent.putExtra("viewGroupId", btn.getId());
                            startActivity(intent);
                    }

            };
            for(int i  =0 ;i < manifest.length; i++){
                Button myButton = new Button(this);
                String btnText = "";
                StringBuilder imgLinks = new StringBuilder();
                if(manifest[i].length == 1){
                    btnText = ("View image group ").concat(manifest[i][0]);
                }else if(manifest[i].length > 1){
                    for(int j = 0; j < manifest[i].length; j++){
                        imgLinks.append(manifest[i][j]).append(" ");
                    }

                    btnText = ("View image group ").concat(imgLinks.toString());
                }
                myButton.setText(btnText);
                LinearLayout ll = (LinearLayout)findViewById(R.id.LinearLayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                myButton.setId(i);
                myButton.setOnClickListener(listener);
                ll.addView(myButton, params);
            }

        });


    }
}
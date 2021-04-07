package com.ng.takehomeprj.imagedisplay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ManifestModel {
    @SerializedName("manifest")
    @Expose
    private String[][] manifest = null;

    public String[][]  getMfest(){

        return manifest;
    }

    public void setManifest(String[][]  manifest){
        this.manifest = manifest;
    }
}

package com.example.cloudstorage.models;

import android.net.Uri;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Data class containing the picture, video or audio file
 */
@ParseClassName("Media")
public class Media extends ParseObject {

    // Constructor
    public Media() {
    }

    // Getters & setters
    public int getMediaType() {
        return getInt("mediaType");
    }

    public void setMediaType(int mediaType) {
        put("mediaType", mediaType);
    }

    public Uri getUri() {
        return Uri.parse(getString("uri"));
    }

    public void setUri(Uri uri) {
        put("uri", uri.toString());
    }

    // Custom class methods
    public boolean getMediaFile() {
        // TODO
        return false;
    }
}

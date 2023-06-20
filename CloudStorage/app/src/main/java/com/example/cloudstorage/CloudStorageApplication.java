package com.example.cloudstorage;

import android.app.Application;

import com.example.cloudstorage.models.LoggedUser;
import com.example.cloudstorage.models.Media;
import com.parse.Parse;
import com.parse.ParseObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CloudStorageApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Create desired entities in the database
        ParseObject.registerSubclass(LoggedUser.class);
        ParseObject.registerSubclass(Media.class);

        // Establish connection to DB (get credentials from text file)
        File path = getApplicationContext().getFilesDir();
        File keys_file = new File(path, "credentials.txt");
        String[] vals = new String[2];
        try {
            FileInputStream stream = new FileInputStream(keys_file);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
            String line;
            int cont = 0;
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split("=");
                vals[cont] = fields[1];
                cont++;
            }
        } catch (Exception e) {
            // TODO make toast
            e.printStackTrace();
        }

        final String app_id = vals[0];
        final String client_key = vals[1];

        if (vals[0] == null || vals[1] == null || vals[0].length() == 0 || vals[1].length() == 0) {
            // TODO terminate execution
        }

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(app_id)
                .clientKey(client_key)
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}

package com.mijatovic.wavealerts.service.firebase.push;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * This service class initializes Firebase Cloud Messaging (FCM) by loading the Firebase configuration file and setting
 * up the FirebaseOptions object.
 */
@Log4j2
@Service
public class FCMInitializer {

    /** The path to the Firebase configuration file. */
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    /**
     * Initializes Firebase Cloud Messaging by loading the Firebase configuration file and setting up the FirebaseOptions object.
     * If FirebaseApp is not already initialized, this method initializes it with the given options.
     */
    @PostConstruct
    public void initialize(){
        try{
            InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
            }
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }
}

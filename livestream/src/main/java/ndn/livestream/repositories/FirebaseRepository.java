package ndn.livestream.repositories;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FirebaseRepository {
    private static final String URL_FILE_KEY = "classpath:static/livestreampj-ab132-firebase-adminsdk-tizfw-528540be76.json";
    private static final String DATABASE_URL = "https://livestreampj-ab132.firebaseio.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseRepository.class);
    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initializeApp() throws IOException {
        LOGGER.info("Initialize firebase");
        Resource resource = resourceLoader.getResource(URL_FILE_KEY);
//        FileInputStream serviceAccount = new FileInputStream(resource.getInputStream());
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                .setDatabaseUrl(DATABASE_URL)
                .build();
        FirebaseApp.initializeApp(options);
    }

    public void saveData(Object o, String path) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(path);
        ref.push().setValueAsync(o);
    }




//    public static void main(String[] args) throws FirebaseMessagingException {
////        File file = new File("livestreampj-ab132-firebase-adminsdk-tizfw-528540be76.json");
////        FileInputStream f = new FileInputStream(file);
////        System.err.println("ss"+ file.getName());
////        try {
////            FileInputStream serviceAccount = new FileInputStream(URL_FILE_KEY);
////            FirebaseOptions options = FirebaseOptions.builder()
////                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
////                    .setDatabaseUrl(DATABASE_URL)
////                    .build();
////            FirebaseApp.initializeApp(options).getOptions();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        List<String> token = Arrays.asList("aaadXI_OSZ0dRqTzpdjQr3RfH:APA91bFs-_tk7QgRtZCfGChhmwBhKa9FdyuV4rEfBy2eq9ooR-KFcgI168PmvzJsQ0aVkxq6mq5DS8YcfyWek5kMe5dDdj1YYm-RVxSHsRPxTx1Yt84Jh8k_ZoiKh7NZiuxeR3F6UYr-");
////        subscribeTopic(token, "topic");
////            pushNotification();
//
//
////
////        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("room/1");
////        ref.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                Object document = dataSnapshot.getValue();
////                System.out.println("sa");
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////                System.err.println("cancel");
////            }
////        });
//
//
////
////        DatabaseReference ref = FirebaseDatabase.getInstance()
////                .getReference("user");
////        ChatEntity chat = new ChatEntity();
////        chat.setUsername(UUID.randomUUID().toString());
////        chat.setContent(Long.toString(System.currentTimeMillis()));
////
////        ref.push().setValueAsync(chat).get();
//
//    }
}

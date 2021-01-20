package ndn.livestream.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PushNotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PushNotificationService.class);
    public void pushNotification(List<String> registrationTokens, String title, String content, Long streamerId) {
        LOGGER.info("Push notification to {} member", registrationTokens.size());
        if(registrationTokens.isEmpty()) return;
        MulticastMessage message = MulticastMessage.builder()
                .putData("title", title)
                .putData("content", content)
                .putData("id", streamerId.toString())
                .addAllTokens(registrationTokens)
                .build();
        FirebaseMessaging.getInstance().sendMulticastAsync(message);
    }
}

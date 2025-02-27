package ceg.app.presentation.consumer;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * This class consumes messages from the test-consumer channel.
 */
@ApplicationScoped
public class MyMessagingApplication {


    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("test-consumer")
    public void sink(JsonObject word) {
        
        System.out.println(word);
    }
}

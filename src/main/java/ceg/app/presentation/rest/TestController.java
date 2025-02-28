package ceg.app.presentation.rest;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterForReflection
@Path("/send")
public class TestController {

    @Channel("test-producer")
    Emitter<JsonObject> emitter;
    
    @POST
    public void sendMessage(JsonObject message) {

        emitter.send(message);
    }
}

package ceg.app.presentation.rest;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/send")
public class TestController {

    @Channel("test-producer")
    Emitter<String> emitter;
    
    @POST
    public Response sendMessage(JsonObject message) {

        try{
            emitter.send(message.encodePrettily());
            Log.info("Message sent: " + message);
        } catch (Exception e) {
            return Response.status(500).entity("Error on send message").build();
        }
        return Response.status(200).entity("Message sent").build();
    }
}

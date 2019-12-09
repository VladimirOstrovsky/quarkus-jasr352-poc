package com.example.bre.vowelexhange;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;

@Path("/async")
public class BreVowelResource {
	
    @Inject
    EventBus bus;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/vowelrevert/{sourcetext}")
    public CompletionStage<String> vowelRevert(@PathParam String sourcetext) {
        return bus.<String>request("com.example.bre.vowelexhange.VowelRevertTask", sourcetext)
                .thenApply(Message::body);
    }


}

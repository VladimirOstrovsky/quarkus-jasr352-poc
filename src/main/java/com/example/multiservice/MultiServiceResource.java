package com.example.multiservice;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;
import io.vertx.axle.core.eventbus.EventBus;

@Path("/multi")
public class MultiServiceResource {

    private final Logger log = LoggerFactory.getLogger(MultiServiceResource.class);

    @Inject
    EventBus bus;
    
    @Inject
    MaltiServiceJob serviceJob;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject performMaltiServiceRequest(JsonObject request) {
	log.trace("request:" + request.toString());
	JsonObject ret = serviceJob.execute(request);
	return ret;
    }
}

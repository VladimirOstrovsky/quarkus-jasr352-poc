package com.example.multiservice;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.arc.Unremovable;
import io.quarkus.messageflow.ServiceJsonTask;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
@Unremovable
public class MaltiServiceTask4 implements ServiceJsonTask {

    @Override
    public JsonObject process(JsonObject request, JsonObject response) {
	response.put("field4", request.getInteger("field4").intValue() + 4);
	return response;
    }

}

package com.example.multiservice;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.arc.Unremovable;
import io.quarkus.messageflow.ServiceJsonTask;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
@Unremovable
public class MaltiServiceTask2 implements ServiceJsonTask {

    @Override
    public JsonObject process(JsonObject request, JsonObject response) {
	response.put("field2", request.getInteger("field2").intValue() + 2);
	return response;
    }

}

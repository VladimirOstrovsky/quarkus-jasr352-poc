package io.quarkus.messageflow;

import io.vertx.core.json.JsonObject;

public interface ServiceJsonTask {

    public JsonObject process(JsonObject request, JsonObject response);
    
}

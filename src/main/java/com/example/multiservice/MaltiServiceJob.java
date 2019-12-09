package com.example.multiservice;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.messageflow.ServiceJsonTask;
import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class MaltiServiceJob {

    @Inject
    BeanManager mgr;
    
    private final Logger log = LoggerFactory.getLogger(MaltiServiceJob.class);

    @ConsumeEvent("com.example.multiservice.MaltiServiceJob")
    public JsonObject execute(JsonObject request) {
	JsonObject response = new JsonObject();
	response.mergeIn(request);

	response = processTask("com.example.multiservice.MaltiServiceTask1", request, response);
	response = processTask("com.example.multiservice.MaltiServiceTask2", request, response);
	
	log.trace(request.toString());
	log.trace(response.toString());
	 
	return response;
    }
    
    private JsonObject processTask(String taskName, JsonObject request, JsonObject response) {
	try {
	    ServiceJsonTask task = getServiceBean(taskName);
	    response = task.process(request, response);
	} catch (Exception ex) {
	    log.error("MaltiServiceJob", ex);
	}
	
	return response;
    }
    
    private ServiceJsonTask getServiceBean(String beanClassName) throws Exception {
	ServiceJsonTask ret = null;
	Class<?> clazz = Class.forName(beanClassName);
	Set<Bean<?>> beans = mgr.getBeans(clazz);
	if (beans != null && beans.iterator().hasNext()) {
	    Bean<?> bean = beans.iterator().next();
	    CreationalContext<?> ctx = mgr.createCreationalContext(bean);
	    ret = (ServiceJsonTask) mgr.getReference(bean, clazz, ctx);
	}
	return ret;
    }
    
}

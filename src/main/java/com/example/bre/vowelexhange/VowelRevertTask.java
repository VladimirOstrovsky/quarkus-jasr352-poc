package com.example.bre.vowelexhange;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.messageflow.ServiceTask;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class VowelRevertTask implements ServiceTask {
    
	private final Logger log = LoggerFactory.getLogger(VowelRevertTask.class);

	@Override
	@ConsumeEvent("com.example.bre.vowelexhange.VowelRevertTask")
	public String process(String request ) {
		if(request == null) {
			return null;
		}
		char[] sour = request.toCharArray();
		char[] dest = sour;
		int jCurrent = dest.length - 1; 
		for(int i=0; i < sour.length; i++) {
			if (isVowel(sour[i])) {
				for( int j=jCurrent; j > i; j--) {
					log.trace("from left [" + i + "]=" + sour[i]);
					log.trace("from right [" + j + "]=" + dest[j]);
					if (isVowel(dest[j])) {
						log.trace("Exhanged.");
						char tmp = dest[i];
						dest[i] = dest[j];
						dest[j] = tmp;
						jCurrent = j-1;
						break;
					}
					log.trace("------------------");
				}
			}
		}
		String ret = new String(dest);
		log.debug(ret);
		return ret;
	}

	private boolean isVowel(char letter) {
		switch (letter) {
		case 'a': return true;
		case 'e': return true;
		case 'i': return true;
		case 'o': return true;
		case 'u': return true;
		case 'y': return true;
		}
		return false;
	}
}

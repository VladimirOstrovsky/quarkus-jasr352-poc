package io.quarkus.messageflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.bre.vowelexhange.VowelRevertTask;

public class VowelRevertTaskTest {

	@ParameterizedTest(name = "{0} = {1}")
	@CsvSource({
			"abcdefghijk,ibcdefghajk"
	})
	void vowelRevertTest(String source, String result) {
		VowelRevertTask task = new VowelRevertTask();
		assertEquals(result, task.process(source),
				() -> source + " should equal " + result);
	}		
	
}

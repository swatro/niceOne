package net.enoot.niceone;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

import static net.enoot.niceone.EnvironmentVariableLLocator.*;


public class NiceOneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<>();
    static {
        //"amzn1.echo-sdk-ams.app.[unique-value-here]");
        supportedApplicationIds.add(getEnvironmentVariable("amazonApplicationID"));
    }

    public NiceOneSpeechletRequestStreamHandler() {
        super(new NiceOne(), supportedApplicationIds);
    }

}

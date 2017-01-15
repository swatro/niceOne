package net.enoot.niceone;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NiceOne implements Speechlet{
    private static final Logger log = LoggerFactory.getLogger(NiceOne.class);


    private static final String[] NiceOneJokes = new String[] {

            "I'm reading a book on the history of glue. I just can't seem to put it down",
            "I knew I shouldn’t have had the seafood. I’m feeling a little eel",
            "Why do bees hum? Because they don't know the words",
            "Last night I dreamt I was a muffler. I woke up exhausted",
            "Don't trust atoms. They make up everything",
            "I bought shoes from a drug dealer once. I don't know what he laced them with, but I was tripping all day"

    };

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getNiceOneJoke();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("GetNiceOneJoke".equals(intentName)) {
            return getNiceOneJoke();

        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();

        } else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }


    private SpeechletResponse getNiceOneJoke() {
        String niceOneJoke = getRandomNiceOneJoke();



        String speechText =  niceOneJoke;


        SimpleCard card = new SimpleCard();
        card.setTitle("NiceJoke");
        card.setContent(speechText);


        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private String getRandomNiceOneJoke() {
        return NiceOneJokes[(int) Math.floor(Math.random() * NiceOneJokes.length)];
    }


    private SpeechletResponse getHelpResponse() {
        String speechText =
                "You can ask NiceOne tell me a joke, or, you can say exit. What can I "
                        + "help you with?";

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);


        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
}

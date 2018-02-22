package edu.fit.nao.module.landmarkdetection;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.ModuleRunner;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class LandMarkDetectionTest extends ModuleRunner {

    private LandMarkDetectionProxy lmd;
    private ALTextToSpeech tts;

    public LandMarkDetectionTest(Session session, String robotUrl) throws Exception {
        super(session, robotUrl);

        lmd = new LandMarkDetectionProxy(session);
        tts = new ALTextToSpeech(session);
    }

    @Override
    public void run() throws Exception {

        if (!this.retryConnect(1, 3))
            throw new TimeoutException("Could not connect to Nao");

        long eventId = lmd.setEventCallback(new EventCallback<List>() {

            @Override
            public void onEvent(List alValue) throws InterruptedException, CallError {

                if (alValue.size() > 0) {

                    tts.say("Landmark Detected");
                    LandMarkDetection landmarkValue = new LandMarkDetection(alValue);
                    System.out.println(landmarkValue);
                }
            }
        });
    }
}

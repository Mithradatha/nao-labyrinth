package edu.fit.nao;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

import java.util.concurrent.TimeUnit;

public class LandmarkTest extends NaoRunnable {

    public LandmarkTest(ConnectionInfo connectionInfo) {
        super(connectionInfo);
    }

    @Override
    void run() throws Exception {

        String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
        Application application = new Application(new String[]{"--qi-url", robotUrl});
        application.start();

        Session session = new Session();
        Future<Void> sessionConnected = session.connect(robotUrl);
        sessionConnected.get(1, TimeUnit.SECONDS);

        ALTextToSpeech tts = new ALTextToSpeech(session);
        tts.say("Hello There");

        ALMemory memory = new ALMemory(session);
        memory.subscribeToEvent("LandmarkDetected", new EventCallback() {

            @Override
            public void onEvent(Object o) throws InterruptedException, CallError {

                LandmarkValue landmarkValue = new LandmarkValue(o);
                tts.say("Landmark Detected");
                System.out.println(landmarkValue);
            }
        });

        application.run();
    }
}

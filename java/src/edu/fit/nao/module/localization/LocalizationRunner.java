package edu.fit.nao.module.localization;

import com.aldebaran.qi.AnyObject;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.ModuleRunner;

public class LocalizationRunner extends ModuleRunner {

    private final float landmarkSize;

    public LocalizationRunner(Session session, float landmarkSize) {

        super(session);
        this.landmarkSize = landmarkSize;
    }

    @Override
    public void run() throws Exception {

        ALTextToSpeech tts = new ALTextToSpeech(session);
        ALMotion motion = new ALMotion(session);

        LandmarkDetectionCallback callback = new LandmarkDetectionCallback(tts, motion, landmarkSize);

        ALMemory memory = new ALMemory(session);

        AnyObject subscriber = memory.subscriber("LandmarkDetected");
        subscriber.connect("signal::(m)", "onLandmarkDetected::(m)", callback);
    }
}

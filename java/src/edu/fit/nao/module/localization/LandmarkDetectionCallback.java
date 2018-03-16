package edu.fit.nao.module.localization;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.module.landmarkdetection.LandMarkDetection;

import java.util.HashMap;
import java.util.List;

public class LandmarkDetectionCallback {

    private final ALTextToSpeech tts;

    private HashMap<Integer, Position3D> cache;
    private final LandmarkLocalization landmarkLocalization;

    public LandmarkDetectionCallback(ALTextToSpeech tts, ALMotion motion, float landmarkSizeX) {

        this.tts = tts;

        this.cache = new HashMap<>();
        this.landmarkLocalization = new LandmarkLocalization(motion, landmarkSizeX);
    }

    public void onLandmarkDetected(Object value) {

        LandMarkDetection landmarkDetection = new LandMarkDetection((List) value);
        landmarkDetection.markInfo.forEach(markInfo -> {

            if (!cache.containsKey(markInfo.markID)) {

                try { tts.say("New Landmark Detected!"); }
                catch (CallError | InterruptedException ex) { ex.printStackTrace(); }
            }

            try {

                Position3D distance = landmarkLocalization.localize(landmarkDetection);
                System.out.println(markInfo.markID + ": " + distance.toString());

                cache.put(markInfo.markID, distance);

            } catch (InterruptedException | CallError ex) { ex.printStackTrace(); }
        });
    }
}

package edu.fit.nao.module.localization;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMotion;
import edu.fit.nao.ModuleRunner;
import edu.fit.nao.module.landmarkdetection.LandmarkDetected;
import edu.fit.nao.module.landmarkdetection.LandmarkDetectionProxy;

import java.util.List;

public class LocalizationRunner extends ModuleRunner {

    private final float landmarkSize;

    public LocalizationRunner(Session session, float landmarkSize) {

        super(session);
        this.landmarkSize = landmarkSize;
    }

    @Override
    public void run() throws Exception {

        ALMotion motion = new ALMotion(session);

        LandmarkLocalization localization = new LandmarkLocalization(motion, landmarkSize);

        LandmarkDetectionProxy lmd = new LandmarkDetectionProxy(session);
        lmd.subscribe(data -> {

            List alValue = (List) data;
            if (alValue.size() > 0) {

                LandmarkDetected landmarkDetected = LandmarkDetected.FromALValue(alValue);
                String currentCamera = landmarkDetected.currentCameraName;

                landmarkDetected.markInfo.forEach(markInfo -> {

                    try {

                        Position3D distance = localization.localize(currentCamera, markInfo.shapeInfo);
                        System.out.println(markInfo.markID + ": " + distance.toString());

                    } catch (Exception ex) { ex.printStackTrace(); }
                });
            }
        });
    }
}

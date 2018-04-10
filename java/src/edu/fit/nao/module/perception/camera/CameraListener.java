package edu.fit.nao.module.perception.camera;

import edu.fit.nao.module.geometry.Pose2D;
import edu.fit.nao.module.landmarkdetection.LandmarkDetected;
import edu.fit.nao.module.localization.LandmarkLocalization;
import edu.fit.nao.module.perception.EventList;
import edu.fit.nao.module.perception.TimestampedData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CameraListener {

    public final EventList<CameraEvent> events = new EventList<>();

    private LandmarkLocalization localization;

    public CameraListener(LandmarkLocalization localization) { this.localization = localization; }

    public void attachTo(Camera camera) throws Exception {

        // TODO: Return subscription ids, Store active listeners
        camera.subscribeLandmarkDetected("onLandmarkDetection::v(m)", this);
    }

    public void onLandmarkDetection(Object o) {

        List alValue = (List) o;
        if (alValue.size() > 0) {

            LandmarkDetected landmarkDetected = LandmarkDetected.FromALValue(alValue);

            String currentCamera = landmarkDetected.currentCameraName;
            long millis = TimeUnit.MICROSECONDS.toMillis(landmarkDetected.timeStamp.microseconds);
            long seconds = landmarkDetected.timeStamp.seconds;

            landmarkDetected.markInfo.forEach(markInfo -> {

                try {

                    Pose2D displacement = localization.localize(currentCamera, markInfo.shapeInfo);

                    CameraEvent event = new CameraEvent(markInfo.markID, displacement);
                    TimestampedData<CameraEvent> data = new TimestampedData<>(seconds, millis, event);

                    this.events.add(data);

                } catch (Exception ex) { ex.printStackTrace(); }
            });
        }
    }

    @Override
    public String toString() {

        return "CameraListener{" +
                "events=" + events +
                '}';
    }
}

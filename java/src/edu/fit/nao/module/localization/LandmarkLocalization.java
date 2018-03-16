package edu.fit.nao.module.localization;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALMotion;
import edu.fit.nao.module.landmarkdetection.LandMarkDetection;

import java.util.List;

/**
 * AngularSize (radians): [0.035, 0.400]
 * AngularSize (degrees): [2, 23]
 * AngularSize (pixels): [14, 160]
 * <p>
 * Tilt (degrees): [-60, 60]
 */
public class LandmarkLocalization {

    private final float landMarkTheoreticalSizeX; // meters

    private final ALMotion motion;

    public LandmarkLocalization(ALMotion motion, float landMarkTheoreticalSizeX) {

        this.motion = motion;
        this.landMarkTheoreticalSizeX = landMarkTheoreticalSizeX;
    }

    // http://doc.aldebaran.com/2-1/dev/python/examples/vision/landmark.html#landmark-localization
    public Position3D localize(LandMarkDetection landMarkDetection) throws InterruptedException, CallError {

        String currentCamera = landMarkDetection.currentCameraName;

        float wzCamera = landMarkDetection.markInfo.get(0).shapeInfo.alpha;
        float wyCamera = landMarkDetection.markInfo.get(0).shapeInfo.beta;

        float angularSize = landMarkDetection.markInfo.get(0).shapeInfo.sizeX;

        float distanceFromCameraToLandmark = (float) (landMarkTheoreticalSizeX / (2 * Math.tan(angularSize / 2)));

        // {FRAME_TORSO = 0, FRAME_WORLD = 1, FRAME_ROBOT = 2}
        List<Float> pFloats = motion.getTransform(currentCamera, 2, true);
        Transform robotToCamera = new Transform(pFloats);

        Transform cameraToLandmarkRotation = Transform.From3DRotation(0, wyCamera, wzCamera);
        Transform cameraToLandmarkTranslation = new Transform(distanceFromCameraToLandmark, 0, 0);

        Transform robotToLandmark = robotToCamera
                .matrixMultiply(cameraToLandmarkRotation)
                .matrixMultiply(cameraToLandmarkTranslation);

        return robotToLandmark.translation();
    }
}

package edu.fit.nao.module.localization;

import com.aldebaran.qi.helper.proxies.ALMotion;
import edu.fit.nao.module.landmarkdetection.ShapeInfo;

import java.util.List;

/**
 * AngularSize (radians): [0.035, 0.400]
 * AngularSize (degrees): [2, 23]
 * AngularSize (pixels): [14, 160]
 * <p>
 * Tilt (degrees): [-60, 60]
 */
public class LandmarkLocalization {

    private final float landmarkTheoreticalSizeX; // meters

    private final ALMotion motion;

    public LandmarkLocalization(ALMotion motion, float landmarkTheoreticalSizeX) {

        this.motion = motion;
        this.landmarkTheoreticalSizeX = landmarkTheoreticalSizeX;
    }

    // http://doc.aldebaran.com/2-1/dev/python/examples/vision/landmark.html#landmark-localization
    public Position3D localize(String currentCamera, ShapeInfo shapeInfo) throws Exception {

        float distanceFromCameraToLandmark = (float) (landmarkTheoreticalSizeX / (2 * Math.tan(shapeInfo.sizeX / 2)));

        List<Float> floats = motion.getTransform(currentCamera, Frame.ROBOT.ordinal(), true);
        Transform robotToCamera = new Transform(floats);

        Transform cameraToLandmarkRotation = Transform.From3DRotation(0, shapeInfo.beta, shapeInfo.alpha);
        Transform cameraToLandmarkTranslation = new Transform(distanceFromCameraToLandmark, 0, 0);

        Transform robotToLandmark = robotToCamera
                .matrixMultiply(cameraToLandmarkRotation)
                .matrixMultiply(cameraToLandmarkTranslation);

        return robotToLandmark.translation();
    }
}

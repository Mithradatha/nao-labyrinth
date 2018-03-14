package edu.fit.nao.module.navigation;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMotion;
import edu.fit.nao.module.landmarkdetection.LandMarkDetection;

import java.util.List;

public class LandmarkLocalization {

    // meters
    private final float landMarkTheoreticalSizeX;

    private final ALMotion motionProxy;

    public LandmarkLocalization(final int landMarkTheoreticalSizeX, final Session session) throws Exception {

        this.landMarkTheoreticalSizeX = landMarkTheoreticalSizeX;
        this.motionProxy = new ALMotion(session);
    }

    public Position3D localize(final LandMarkDetection landMarkDetection) throws InterruptedException, CallError {

        final String currentCamera = landMarkDetection.currentCameraName;

        final float wzCamera = landMarkDetection.markInfo.get(0).shapeInfo.alpha;
        final float wyCamera = landMarkDetection.markInfo.get(0).shapeInfo.beta;

        final float angularSize = landMarkDetection.markInfo.get(0).shapeInfo.sizeX;

        final float distanceFromCameraToLandmark = (float) (landMarkTheoreticalSizeX / (2 * Math.tan(angularSize / 2)));

        // {FRAME_TORSO = 0, FRAME_WORLD = 1, FRAME_ROBOT = 2}
        final List<Float> pFloats = motionProxy.getTransform(currentCamera, 2, true);
        final Transform robotToCamera = new Transform(pFloats);

        final Transform cameraToLandmarkRotation = Transform.From3DRotation(0, wyCamera, wzCamera);
        final Transform cameraToLandmarkTranslation = new Transform(distanceFromCameraToLandmark, 0, 0);

        final Transform robotToLandmark =
                robotToCamera.matrixMultiply(cameraToLandmarkRotation).matrixMultiply(cameraToLandmarkTranslation);

        return robotToLandmark.translation();
    }
}

package edu.fit.nao.module.localization;

import com.aldebaran.qi.helper.proxies.ALMotion;
import edu.fit.nao.module.geometry.Pose2D;
import edu.fit.nao.module.geometry.Position2D;
import edu.fit.nao.module.geometry.Position3D;
import edu.fit.nao.module.geometry.Transform;
import edu.fit.nao.module.landmarkdetection.ShapeInfo;

import java.util.List;

/**
 * AngularSize (radians): [0.035, 0.400]
 * AngularSize (degrees): [2, 23]
 * AngularSize (pixels): [14, 160]
 * <p>
 * Tilt (degrees): [-60, 60]
 * <p>
 * referenceTranslation = Frame.WORLD to Frame.ROBOT in [x, y]
 * referenceRotation = Frame.WORLD to Frame.ROBOT in (theta)
 */
public class LandmarkLocalization {

    private final float landmarkTheoreticalSizeX; // meters

    private final ALMotion motion;

    public LandmarkLocalization(ALMotion motion, float landmarkTheoreticalSizeX) {

        this.motion = motion;
        this.landmarkTheoreticalSizeX = landmarkTheoreticalSizeX;
    }

    // http://doc.aldebaran.com/2-1/dev/python/examples/vision/landmark.html#landmark-localization
    public Position3D landmarkPositionInRobotFrame(String currentCamera, ShapeInfo shapeInfo) throws Exception {

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

    public Position2D landmarkPositionInWorldFrame(Pose2D worldToRobot, Position2D robotToLandmark) {

        Transform theta = Transform.FromRotZ(worldToRobot.theta);
        Transform landmark = new Transform(robotToLandmark.x, robotToLandmark.y, 0.0f);

        Position3D temp = Transform.MatrixMultiply(theta, landmark).translation();

        return new Position2D(temp.x + worldToRobot.x, temp.y + worldToRobot.y);
    }

    public Position2D landmarkPositionInRobotFrame(Pose2D worldToRobot, Position2D worldToLandmark) {

        Transform thetaTranspose = Transform.FromRotZTranspose(worldToRobot.theta);
        Transform landmark = new Transform(worldToLandmark.x - worldToRobot.getPosition().x,
                worldToLandmark.y - worldToRobot.getPosition().y, 0.0f);

        Position3D temp = Transform.MatrixMultiply(thetaTranspose, landmark).translation();

        return new Position2D(temp.x, temp.y);
    }
}

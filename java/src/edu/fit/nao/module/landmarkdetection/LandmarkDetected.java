package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.ALValue;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ALLandMarkDetection {
 * TimeStamp,
 * MarkInfo[N],
 * CameraPoseInFrameTorso,
 * CameraPoseInFrameRobot,
 * CurrentCameraName
 * }
 */
public class LandmarkDetected extends ALValue {

    public final TimeStamp timeStamp;
    public final List<MarkInfo> markInfo;
    public final Pose6D cameraPoseInFrameTorso;
    public final Pose6D cameraPoseInFrameRobot;

    // “CameraTop” or “CameraBottom”
    public final String currentCameraName;

    private LandmarkDetected(
            TimeStamp timeStamp,
            List<MarkInfo> markInfo,
            Pose6D cameraPoseInFrameTorso,
            Pose6D cameraPoseInFrameRobot,
            String currentCameraName
    ) {
        this.timeStamp = timeStamp;
        this.markInfo = markInfo;
        this.cameraPoseInFrameTorso = cameraPoseInFrameTorso;
        this.cameraPoseInFrameRobot = cameraPoseInFrameRobot;
        this.currentCameraName = currentCameraName;
    }

    public static LandmarkDetected FromALValue(List alValue) {

        TimeStamp timeStamp = TimeStamp.FromALValue((List) alValue.get(0));
        List<MarkInfo> markInfo = (List<MarkInfo>) ((List) alValue.get(1))
                .stream().map(o -> MarkInfo.FromALValue((List) o)).collect(Collectors.toList());

        Pose6D torso = Pose6D.FromALValue((List) alValue.get(2));
        Pose6D robot = Pose6D.FromALValue((List) alValue.get(3));
        String currentCameraName = (String) alValue.get(4);

        return new LandmarkDetected(timeStamp, markInfo, torso, robot, currentCameraName);
    }
}



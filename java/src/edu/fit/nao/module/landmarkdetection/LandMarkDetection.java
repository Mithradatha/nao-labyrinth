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
public class LandMarkDetection extends ALValue<List> {

    public final TimeStamp timeStamp;
    public final List<MarkInfo> markInfo;
    public final Position6D cameraPoseInFrameTorso;
    public final Position6D cameraPoseInFrameRobot;

    // “CameraTop” or “CameraBottom”
    public final String currentCameraName;

    private LandMarkDetection(
            TimeStamp timeStamp,
            List<MarkInfo> markInfo,
            Position6D cameraPoseInFrameTorso,
            Position6D cameraPoseInFrameRobot,
            String currentCameraName
    ) {
        this.timeStamp = timeStamp;
        this.markInfo = markInfo;
        this.cameraPoseInFrameTorso = cameraPoseInFrameTorso;
        this.cameraPoseInFrameRobot = cameraPoseInFrameRobot;
        this.currentCameraName = currentCameraName;
    }

    public LandMarkDetection(List alValue) {

        this(
                new TimeStamp((List) alValue.get(0)),
                (List<MarkInfo>) ((List) alValue.get(1))
                        .stream().map(o -> {
                            return new MarkInfo((List) o);
                        }).collect(Collectors.toList()),
                new Position6D((List) alValue.get(2)),
                new Position6D((List) alValue.get(3)),
                (String) alValue.get(4)
        );
    }
}



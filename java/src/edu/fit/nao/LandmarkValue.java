package edu.fit.nao;

import java.util.ArrayList;
import java.util.List;

public class LandmarkValue {

    private TimeStamp timeStamp;
    private List<MarkInfo> markInfo;
    private Position6D cameraPoseInFrameTorso;
    private Position6D cameraPoseInFrameRobot;
    private String currentCameraName;

    public LandmarkValue(Object o) {

        if (o instanceof List) {

            List landmarkValue = (List)o;
            timeStamp = new TimeStamp(landmarkValue.get(0));
            List idk = (List)landmarkValue.get(1);
            markInfo = new ArrayList<MarkInfo>();
            idk.forEach(item -> this.markInfo.add(new MarkInfo(item)));
            cameraPoseInFrameTorso = new Position6D(landmarkValue.get(2));
            cameraPoseInFrameRobot = new Position6D(landmarkValue.get(3));
            currentCameraName = ((List)landmarkValue.get(4)).get(0).toString();
            assert currentCameraName.equals("CameraTop") || currentCameraName.equals("CameraBottom");

        } else throw new ClassCastException("LandmarkValue conversion not implemented correctly");
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ALLandMarkDetection {\n")
                .append(timeStamp.toString()).append(",\n")
                .append("MarkInfo [\n");

        for (MarkInfo info: markInfo) {
            stringBuilder.append(info.toString()).append(",\n");
        }

        stringBuilder.append("],\n")
                .append("CameraPoseInFrameTorso: ")
                .append(cameraPoseInFrameTorso.toString()).append(",\n")
                .append("CameraPoseInFrameRobot: ")
                .append(cameraPoseInFrameRobot.toString()).append(",\n")
                .append(currentCameraName).append("\n")
                .append("}");

        return stringBuilder.toString();
    }
}



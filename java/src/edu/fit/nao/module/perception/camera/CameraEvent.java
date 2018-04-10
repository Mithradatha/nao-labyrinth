package edu.fit.nao.module.perception.camera;

import edu.fit.nao.module.geometry.Pose2D;

public class CameraEvent {

    public int landmarkId;
    public Pose2D displacement;

    public CameraEvent(int landmarkId, Pose2D displacement) {

        this.landmarkId = landmarkId;
        this.displacement = displacement;
    }

    @Override
    public String toString() {

        return "CameraEvent{" +
                "landmarkId=" + landmarkId +
                ", displacement=" + displacement +
                '}';
    }
}

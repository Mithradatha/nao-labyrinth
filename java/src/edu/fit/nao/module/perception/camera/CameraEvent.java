package edu.fit.nao.module.perception.camera;

import edu.fit.nao.helper.geometry.Position3D;

public class CameraEvent {

    public int landmarkId;
    public Position3D displacement;

    public CameraEvent(int landmarkId, Position3D displacement) {

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

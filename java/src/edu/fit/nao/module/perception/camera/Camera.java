package edu.fit.nao.module.perception.camera;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import edu.fit.nao.module.landmarkdetection.LandmarkDetectionProxy;

public class Camera {

    private LandmarkDetectionProxy lmd;

    public Camera(LandmarkDetectionProxy lmd) {

        this.lmd = lmd;
    }

    public void start() throws InterruptedException, CallError { this.lmd.subscribe("_Camera"); }

    public void stop() throws InterruptedException, CallError { this.lmd.unsubscribe("_Camera"); }

    public long subscribeLandmarkDetected(EventCallback callback) throws Exception {

        return this.lmd.subscribeToLandmarkDetected(callback);
    }

    public long subscribeLandmarkDetected(String callbackSignature, Object callbackObject) throws Exception {

        return this.lmd.subscribeToLandmarkDetected(callbackSignature, callbackObject);
    }
}

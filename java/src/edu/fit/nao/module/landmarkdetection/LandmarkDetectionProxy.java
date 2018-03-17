package edu.fit.nao.module.landmarkdetection;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.ALProxy;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;

public class LandmarkDetectionProxy extends ALProxy {

    private final ALMemory memory;

    public LandmarkDetectionProxy(Session session) throws Exception {

        super(session, "ALLandMarkDetection");
        this.memory = new ALMemory(session);
    }

    public long subscribe(EventCallback callback) throws Exception {

        // LandMarkDetection extractor: period=30 (ms), precision=1
        this.call("subscribe", "Proxy_LandmarkDetected").get();

        // returns subscriber number
        return memory.subscribeToEvent("LandmarkDetected", callback);
    }

    public long subscribe(String callbackSignature, Object callbackObject) throws Exception {

        // LandMarkDetection extractor: period=30 (ms), precision=1
        this.call("subscribe", "Proxy_LandmarkDetected").get();

        // returns subscriber number
        return memory.subscribeToEvent("LandmarkDetected", callbackSignature, callbackObject);
    }
}

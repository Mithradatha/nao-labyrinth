package edu.fit.nao.helper.proxies;

import com.aldebaran.qi.CallError;
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

    // LandMarkDetection extractor: period=30 (ms), precision=1
    public void subscribe(String name) throws CallError, InterruptedException {

        this.call("subscribe", name).get();
    }

    public void unsubscribe(String name) throws CallError, InterruptedException {

        this.call("unsubscribe", name).get();
    }

    public long subscribeToLandmarkDetected(EventCallback callback) throws Exception {

        return memory.subscribeToEvent("LandmarkDetected", callback);
    }

    public long subscribeToLandmarkDetected(String callbackSignature, Object callbackObject) throws Exception {

        return memory.subscribeToEvent("LandmarkDetected", callbackSignature, callbackObject);
    }
}

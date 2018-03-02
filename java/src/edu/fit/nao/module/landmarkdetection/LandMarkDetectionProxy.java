package edu.fit.nao.module.landmarkdetection;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.ALProxy;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;

import java.util.List;

public class LandMarkDetectionProxy extends ALProxy {

    private ALMemory memory;

    public LandMarkDetectionProxy(Session session) throws Exception {

        super(session, "ALLandMarkDetection");

        memory = new ALMemory(session);
    }

    public void subscribe() throws CallError, InterruptedException {

        this.call("subscribe", "Test_LandmarkDetected");
    }

    public long setEventCallback(EventCallback<List> callback) throws Exception {

        return memory.subscribeToEvent("LandmarkDetected", callback);
    }
}

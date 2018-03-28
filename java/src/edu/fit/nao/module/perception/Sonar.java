package edu.fit.nao.module.perception;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSonar;

/**
 * Frequency: 40kHz
 * Effective cone: 60°
 * Resolution: [0.1, 0.4] (meters)
 *
 * Hardware detection range: [0.2, 0.8] (meters)
 * Default obstacle detection threshold: 0.5 (meters)
 *
 * All events return float distance (meters) from object
 *
 * SonarLeftDetected  -> object left, navigation options: { right }
 * SonarRightDetected -> object right, navigation options: { left }
 * SonarLeftNothingDetected  -> object right, navigation options: { forward, left }
 * SonarRightNothingDetected -> object left, navigation options: { forward, right }
 *
 * Frame.TORSO:
 *     US sensor 1: x=0.0537, y=-0.0341, z=0.0698, wx=0.0, wy=-0.1745, wz=-0.3490
 *     US sensor 2: x=0.0477, y=-0.0416, z=0.0509, wx=0.0, wy=0.2618,  wz=-0.4363
 */
public class Sonar {

    private ALSonar sonar;
    private ALMemory memory;

    public Sonar(ALSonar sonar, ALMemory memory) {

        this.sonar = sonar;
        this.memory = memory;
    }

    public void startListening() throws InterruptedException, CallError { this.sonar.subscribe("_Sonar"); }
    public void stopListening() throws InterruptedException, CallError { this.sonar.unsubscribe("_Sonar"); }

    public long subscribeLeftDetected(EventCallback callback) throws Exception {
        return memory.subscribeToEvent("SonarLeftDetected", callback);
    }

    public long subscribeLeftDetected(String callbackSignature, Object callbackObject) throws Exception {
        return memory.subscribeToEvent("SonarLeftDetected", callbackSignature, callbackObject);
    }

    public long subscribeRightDetected(EventCallback callback) throws Exception {
        return memory.subscribeToEvent("SonarRightDetected", callback);
    }

    public long subscribeRightDetected(String callbackSignature, Object callbackObject) throws Exception {
        return memory.subscribeToEvent("SonarRightDetected", callbackSignature, callbackObject);
    }

    public long subscribeLeftNothingDetected(EventCallback callback) throws Exception {
        return memory.subscribeToEvent("SonarLeftNothingDetected", callback);
    }

    public long subscribeLeftNothingDetected(String callbackSignature, Object callbackObject) throws Exception {
        return memory.subscribeToEvent("SonarLeftNothingDetected", callbackSignature, callbackObject);
    }

    public long subscribeRightNothingDetected(EventCallback callback) throws Exception {
        return memory.subscribeToEvent("SonarRightNothingDetected", callback);
    }

    public long subscribeRightNothingDetected(String callbackSignature, Object callbackObject) throws Exception {
        return memory.subscribeToEvent("SonarRightNothingDetected", callbackSignature, callbackObject);
    }
}

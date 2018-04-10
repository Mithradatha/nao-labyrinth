package edu.fit.nao.module.perception.sonar;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSonar;
import edu.fit.nao.module.perception.TimestampedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Frequency: 40kHz
 * Effective cone: 60°
 * Resolution: [0.1, 0.4] (meters)
 * <p>
 * Hardware detection range: [0.2, 0.8] (meters)
 * Default obstacle detection threshold: 0.5 (meters)
 * <p>
 * All events return float distance (meters) from object
 * Return value of 0.0 indicates an error
 * <p>
 * SonarLeftDetected  -> object left, navigation options: { right }
 * SonarRightDetected -> object right, navigation options: { left }
 * SonarLeftNothingDetected  -> object right, navigation options: { forward, left }
 * SonarRightNothingDetected -> object left, navigation options: { forward, right }
 * <p>
 * Frame.TORSO:
 * US sensor 1: x=0.0537, y=-0.0341, z=0.0698, wx=0.0, wy=-0.1745, wz=-0.3490
 * US sensor 2: x=0.0477, y=-0.0416, z=0.0509, wx=0.0, wy=0.2618,  wz=-0.4363
 */
public class Sonar {

    private ALSonar sonar;
    private ALMemory memory;

    public Sonar(ALSonar sonar, ALMemory memory) {

        this.sonar = sonar;
        this.memory = memory;
    }

    public void start() throws InterruptedException, CallError { this.sonar.subscribe("_Sonar"); }

    public void stop() throws InterruptedException, CallError { this.sonar.unsubscribe("_Sonar"); }

    private List<Float> getEchoes(String side) throws InterruptedException, CallError {

        List<Float> echoes = new ArrayList<>(10);

        try {

            for (int i = 0; i < 10; i++) {

                String memoryKey = "Device/SubDeviceList/US/" + side + "/Sensor/Value";
                float memoryValue = (float) this.memory.getData((i == 0) ? memoryKey : memoryKey + i);
                if (memoryValue != 0.0f) echoes.add(memoryValue);
            }

        } catch (ClassCastException ignored) {}

        return echoes;
    }

    public List<Float> getLeftEchoes() throws CallError, InterruptedException { return getEchoes("Left"); }

    public List<Float> getRightEchoes() throws InterruptedException, CallError { return getEchoes("Right"); }

    private List<TimestampedData<Float>> getTimestampedEchoes(String side) throws InterruptedException, CallError {

        List<TimestampedData<Float>> echoes = new ArrayList<>(10);

        try {

            for (int i = 0; i < 10; i++) {

                String memoryKey = "Device/SubDeviceList/US/" + side + "/Sensor/Value";
                Object memoryValue = this.memory.getTimestamp((i == 0) ? memoryKey : memoryKey + i);

                TimestampedData<Float> data = TimestampedData.<Float>FromMemory(memoryValue);
                if (data.value != 0.0f) echoes.add(data);
            }

        } catch (ClassCastException ignored) {}

        return echoes;
    }

    public List<TimestampedData<Float>> getLeftTimestampedEchoes() throws CallError, InterruptedException {

        return getTimestampedEchoes("Left");
    }

    public List<TimestampedData<Float>> getRightTimestampedEchoes() throws CallError, InterruptedException {

        return getTimestampedEchoes("Right");
    }

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

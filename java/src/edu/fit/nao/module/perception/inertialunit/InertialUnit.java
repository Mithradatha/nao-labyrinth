package edu.fit.nao.module.perception.inertialunit;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALMemory;

/**
 * IMU:
 * Gyroscope -> [wx, wy], angular speed = ~500 degrees / second
 * Accelerometer -> [x, y, z], acceleration = ~19.62 meters / second
 * <p>
 * Frame.TORSO:
 * Accelerometer: x=-0.008, y=0.00606, z=0.027
 * Gyroscope:     x=-0.008, y=0.006,   z=0.029
 */
public class InertialUnit {

    private ALMemory memory;

    public InertialUnit(ALMemory memory) { this.memory = memory; }

    public Gyroscope getGyroscope() throws InterruptedException, CallError {

        Object x = this.memory.getData("Device/SubDeviceList/InertialSensor/GyroscopeX/Sensor/Value");
        Object y = this.memory.getData("Device/SubDeviceList/InertialSensor/GyroscopeY/Sensor/Value");

        return new Gyroscope((float) x, (float) y);
    }

    public Accelerometer getAccelerometer() throws InterruptedException, CallError {

        Object x = this.memory.getData("Device/SubDeviceList/InertialSensor/AccelerometerX/Sensor/Value");
        Object y = this.memory.getData("Device/SubDeviceList/InertialSensor/AccelerometerY/Sensor/Value");
        Object z = this.memory.getData("Device/SubDeviceList/InertialSensor/AccelerometerZ/Sensor/Value");

        return new Accelerometer((float) x, (float) y, (float) z);
    }

    public TorsoAngle getTorsoAngle() throws InterruptedException, CallError {

        Object x = this.memory.getData("Device/SubDeviceList/InertialSensor/AngleX/Sensor/Value");
        Object y = this.memory.getData("Device/SubDeviceList/InertialSensor/AngleY/Sensor/Value");

        return new TorsoAngle((float) x, (float) y);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("InertialUnit{");

        try {

            Accelerometer accelerometer = getAccelerometer();
            Gyroscope gyroscope = getGyroscope();
            TorsoAngle torsoAngle = getTorsoAngle();

            sb.append(accelerometer).append(", ")
                    .append(gyroscope).append(", ")
                    .append(torsoAngle);

        } catch (InterruptedException | CallError e) {
            e.printStackTrace();
        }

        sb.append('}');
        return sb.toString();
    }
}

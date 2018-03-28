package edu.fit.nao.module.perception;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALMemory;

/**
 * Gyroscope -> [wx, wy], angular speed = ~500 degrees / second
 * Accelerometer -> [x, y, z], acceleration = ~19.62 meters / second
 *
 * Frame.TORSO:
 *     Accelerometer: x=-0.008, y=0.00606, z=0.027
 *     Gyroscope:     x=-0.008, y=0.006,   z=0.029
 */
public class InertialUnit {

    private ALMemory memory;

    public InertialUnit(ALMemory memory) { this.memory = memory; }

    // radians per second (float)
    public Object[] getGyroscope() throws InterruptedException, CallError {

        Object x = this.memory.getData("Device/SubDeviceList/InertialSensor/GyroscopeX/Sensor/Value");
        Object y = this.memory.getData("Device/SubDeviceList/InertialSensor/GyroscopeY/Sensor/Value");

        return new Object[]{ x, y };
    }

    // meters per second (float), 1g = 9.81 m/s, precision = 8 | 12 (bits)
    public Object[] getAccelerometer() throws InterruptedException, CallError {

        Object x = this.memory.getData("Device/SubDeviceList/InertialSensor/AccelerometerX/Sensor/Value");
        Object y = this.memory.getData("Device/SubDeviceList/InertialSensor/AccelerometerY/Sensor/Value");
        Object z = this.memory.getData("Device/SubDeviceList/InertialSensor/AccelerometerZ/Sensor/Value");

        return new Object[]{ x, y, z };
    }

    // radians (float)
    public Object[] getTorsoAngle() throws InterruptedException, CallError {

        Object x = this.memory.getData("Device/SubDeviceList/InertialSensor/AngleX/Sensor/Value");
        Object y = this.memory.getData("Device/SubDeviceList/InertialSensor/AngleY/Sensor/Value");

        return new Object[]{ x, y };
    }
}

package edu.fit.nao.module.geometry;

import java.util.List;

/**
 * http://doc.aldebaran.com/2-1/ref/libalmath/a00028_source.html
 */
public class Pose2D {

    // position (meters)
    public final float x;
    public final float y;

    // orientation (radians)
    public final float theta; // wz: [-pi, pi]

    public Pose2D(float x, float y, float theta) {

        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public Pose2D(List<Float> floats) {

        this.x = floats.get(0);
        this.y = floats.get(1);
        this.theta = floats.get(2);
    }

    public Position2D getPosition() {

        return new Position2D(x, y);
    }

    public Pose2D add(Pose2D other) {

        return new Pose2D(this.x + other.x, this.y + other.y, this.theta + other.theta);
    }

    public Pose2D subtract(Pose2D other) {

        return new Pose2D(this.x - other.x, this.y - other.y, this.theta - other.theta);
    }

    public Pose2D multiply(Pose2D other) {

        float x = (float) (this.x + Math.cos(this.theta) * other.x - Math.sin(this.theta) * other.y);
        float y = (float) (this.y + Math.sin(this.theta) * other.x - Math.cos(this.theta) * other.y);
        float theta = this.theta + other.theta;

        return new Pose2D(x, y, theta);
    }

    public Pose2D scale(float multiplier) {

        return new Pose2D(this.x * multiplier, this.y * multiplier, this.theta * multiplier);
    }

    public float norm() {

        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float getAngle() {

        return (float) Math.atan2(this.y, this.x);
    }

    public float distance(Pose2D other) {

        return (float) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public float tan() {

        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public boolean isNear(Pose2D other) { return isNear(other, 0.0001f); }

    public boolean isNear(Pose2D other, float epsilon) {

        return this.distance(other) < epsilon;
    }

    public Pose2D inverse() {

        float x = (float) (-this.x * Math.cos(this.theta) - this.y * Math.sin(this.theta));
        float y = (float) (this.x * Math.sin(this.theta) - this.y * Math.cos(this.theta));

        return new Pose2D(x, y, -this.theta);
    }

    public Pose2D diff(Pose2D other) {

        return this.inverse().multiply(other);
    }

    private Pose2D globalToLocal(Pose2D other) {

        float x = (float) ((other.x - this.x) * Math.cos(this.theta) + (other.y - this.y) * Math.sin(this.theta));
        float y = (float) ((other.x - this.y) * -Math.sin(this.theta) + (other.y - this.y) * Math.cos(this.theta));
        // TODO: Fix, this looks wrong
        return new Pose2D(x, y, other.theta);
    }

    @Override
    public String toString() {

        return "Pose2D{" +
                "x=" + x +
                ", y=" + y +
                ", theta=" + theta +
                '}';
    }
}

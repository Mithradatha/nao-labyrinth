package edu.fit.nao.module.localization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Homogenous Transformation Matrix
 * <p>
 * | 1 0 0 x |
 * | 0 1 0 y |
 * | 0 0 1 z |
 * | 0 0 0 1 |
 * <p>
 * http://en.wikipedia.org/wiki/Transformation_matrix
 *
 * http://doc.aldebaran.com/2-1/ref/libalmath/a00002.html
 */
public class Transform implements Cloneable {

    private float r1_c1, r1_c2, r1_c3, r1_c4;
    private float r2_c1, r2_c2, r2_c3, r2_c4;
    private float r3_c1, r3_c2, r3_c3, r3_c4;

    // identity matrix
    public Transform() {

        this.r1_c1 = 1.0f;
        this.r1_c2 = 0.0f;
        this.r1_c3 = 0.0f;
        this.r1_c4 = 0.0f;

        this.r2_c1 = 0.0f;
        this.r2_c2 = 1.0f;
        this.r2_c3 = 0.0f;
        this.r2_c4 = 0.0f;

        this.r3_c1 = 0.0f;
        this.r3_c2 = 0.0f;
        this.r3_c3 = 1.0f;
        this.r3_c4 = 0.0f;
    }

    public Transform(List<Float> floats) {

        this.r1_c1 = floats.get(0);
        this.r1_c2 = floats.get(1);
        this.r1_c3 = floats.get(2);
        this.r1_c4 = floats.get(3);

        this.r2_c1 = floats.get(4);
        this.r2_c2 = floats.get(5);
        this.r2_c3 = floats.get(6);
        this.r2_c4 = floats.get(7);

        this.r3_c1 = floats.get(8);
        this.r3_c2 = floats.get(9);
        this.r3_c3 = floats.get(10);
        this.r3_c4 = floats.get(11);
    }

    // populate translation vector
    public Transform(float x, float y, float z) {

        this();

        this.r1_c4 = x;
        this.r2_c4 = y;
        this.r3_c4 = z;
    }

    public Position3D translation() {

        return new Position3D(this.r1_c4, this.r2_c4, this.r3_c4);
    }

    // euler angle in radians
    public Transform from3DRotation(float wx, float wy, float wz) {

        Transform transform = this.clone();

        Transform rotZ = transform.fromRotZ(wz);
        Transform rotY = transform.fromRotY(wy);
        Transform rotX = transform.fromRotX(wx);

        return rotZ.matrixMultiply(rotY).matrixMultiply(rotX);
    }

    // euler angle in radians
    public static Transform From3DRotation(float wx, float wy, float wz) {

        return MatrixMultiply(MatrixMultiply(FromRotZ(wz), FromRotY(wy)), FromRotX(wx));
    }

    // z axis rotation angle in radians
    public Transform fromRotZ(float rotZ) {

        Transform transform = this.clone();

        transform.r1_c1 = (float) Math.cos(rotZ);
        transform.r1_c2 = (float) (-1 * Math.sin(rotZ));
        transform.r2_c1 = (float) Math.sin(rotZ);
        transform.r2_c2 = (float) Math.cos(rotZ);

        return transform;
    }

    // z axis rotation angle in radians
    public static Transform FromRotZ(float rotZ) {

        Transform transform = new Transform();
        transform.r1_c1 = (float) Math.cos(rotZ);
        transform.r1_c2 = (float) (-1 * Math.sin(rotZ));
        transform.r2_c1 = (float) Math.sin(rotZ);
        transform.r2_c2 = (float) Math.cos(rotZ);

        return transform;
    }

    // y axis rotation angle in radians
    public Transform fromRotY(float rotY) {

        Transform transform = this.clone();

        transform.r1_c1 = (float) Math.cos(rotY);
        transform.r1_c3 = (float) Math.sin(rotY);
        transform.r3_c1 = (float) (-1 * Math.sin(rotY));
        transform.r3_c3 = (float) Math.cos(rotY);

        return transform;
    }

    // y axis rotation angle in radians
    public static Transform FromRotY(float rotY) {

        Transform transform = new Transform();
        transform.r1_c1 = (float) Math.cos(rotY);
        transform.r1_c3 = (float) Math.sin(rotY);
        transform.r3_c1 = (float) (-1 * Math.sin(rotY));
        transform.r3_c3 = (float) Math.cos(rotY);

        return transform;
    }

    // x axis rotation angle in radians
    public Transform fromRotX(float rotX) {

        Transform transform = this.clone();

        transform.r2_c2 = (float) Math.cos(rotX);
        transform.r2_c3 = (float) (-1 * Math.sin(rotX));
        transform.r3_c2 = (float) Math.sin(rotX);
        transform.r3_c3 = (float) Math.cos(rotX);

        return transform;
    }

    // x axis rotation angle in radians
    public static Transform FromRotX(float rotX) {

        Transform transform = new Transform();
        transform.r2_c2 = (float) Math.cos(rotX);
        transform.r2_c3 = (float) (-1 * Math.sin(rotX));
        transform.r3_c2 = (float) Math.sin(rotX);
        transform.r3_c3 = (float) Math.cos(rotX);

        return transform;
    }

    public Transform matrixMultiply(Transform o) {

        Transform transform = this.clone();

        transform.r1_c1 = this.r1_c1 * o.r1_c1 + this.r1_c2 * o.r2_c1 + this.r1_c3 * o.r3_c1;
        transform.r1_c2 = this.r1_c1 * o.r1_c2 + this.r1_c2 * o.r2_c2 + this.r1_c3 * o.r3_c2;
        transform.r1_c3 = this.r1_c1 * o.r1_c3 + this.r1_c2 * o.r2_c3 + this.r1_c3 * o.r3_c3;
        transform.r1_c4 = this.r1_c1 * o.r1_c4 + this.r1_c2 * o.r2_c4 + this.r1_c3 * o.r3_c4 + this.r1_c4;

        transform.r2_c1 = this.r2_c1 * o.r1_c1 + this.r2_c2 * o.r2_c1 + this.r2_c3 * o.r3_c1;
        transform.r2_c2 = this.r2_c1 * o.r1_c2 + this.r2_c2 * o.r2_c2 + this.r2_c3 * o.r3_c2;
        transform.r2_c3 = this.r2_c1 * o.r1_c3 + this.r2_c2 * o.r2_c3 + this.r2_c3 * o.r3_c3;
        transform.r2_c4 = this.r2_c1 * o.r1_c4 + this.r2_c2 * o.r2_c4 + this.r2_c3 * o.r3_c4 + this.r2_c4;

        transform.r3_c1 = this.r3_c1 * o.r1_c1 + this.r3_c2 * o.r2_c1 + this.r3_c3 * o.r3_c1;
        transform.r3_c2 = this.r3_c1 * o.r1_c2 + this.r3_c2 * o.r2_c2 + this.r3_c3 * o.r3_c2;
        transform.r3_c3 = this.r3_c1 * o.r1_c3 + this.r3_c2 * o.r2_c3 + this.r3_c3 * o.r3_c3;
        transform.r3_c4 = this.r3_c1 * o.r1_c4 + this.r3_c2 * o.r2_c4 + this.r3_c3 * o.r3_c4 + this.r3_c4;

        return transform;
    }

    public static Transform MatrixMultiply(Transform a, Transform b) {

        List<Float> floats = new ArrayList<>(12);

        floats.add(a.r1_c1 * b.r1_c1 + a.r1_c2 * b.r2_c1 + a.r1_c3 * b.r3_c1);
        floats.add(a.r1_c1 * b.r1_c2 + a.r1_c2 * b.r2_c2 + a.r1_c3 * b.r3_c2);
        floats.add(a.r1_c1 * b.r1_c3 + a.r1_c2 * b.r2_c3 + a.r1_c3 * b.r3_c3);
        floats.add(a.r1_c1 * b.r1_c4 + a.r1_c2 * b.r2_c4 + a.r1_c3 * b.r3_c4 + a.r1_c4);

        floats.add(a.r2_c1 * b.r1_c1 + a.r2_c2 * b.r2_c1 + a.r2_c3 * b.r3_c1);
        floats.add(a.r2_c1 * b.r1_c2 + a.r2_c2 * b.r2_c2 + a.r2_c3 * b.r3_c2);
        floats.add(a.r2_c1 * b.r1_c3 + a.r2_c2 * b.r2_c3 + a.r2_c3 * b.r3_c3);
        floats.add(a.r2_c1 * b.r1_c4 + a.r2_c2 * b.r2_c4 + a.r2_c3 * b.r3_c4 + a.r2_c4);

        floats.add(a.r3_c1 * b.r1_c1 + a.r3_c2 * b.r2_c1 + a.r3_c3 * b.r3_c1);
        floats.add(a.r3_c1 * b.r1_c2 + a.r3_c2 * b.r2_c2 + a.r3_c3 * b.r3_c2);
        floats.add(a.r3_c1 * b.r1_c3 + a.r3_c2 * b.r2_c3 + a.r3_c3 * b.r3_c3);
        floats.add(a.r3_c1 * b.r1_c4 + a.r3_c2 * b.r2_c4 + a.r3_c3 * b.r3_c4 + a.r3_c4);

        return new Transform(floats);
    }

    @Override
    protected Transform clone() {

        // shallow copy suffices for primitive
        try { return (Transform) super.clone(); }
        catch (CloneNotSupportedException ex) { ex.printStackTrace(); }

        return null;
    }

    @Override
    public String toString() {

        return  "| " + norm(r1_c1) + ' ' + norm(r1_c2) + ' ' + norm(r1_c3) + ' ' + norm(r1_c4) + " |\n" +
                "| " + norm(r2_c1) + ' ' + norm(r2_c2) + ' ' + norm(r2_c3) + ' ' + norm(r2_c4) + " |\n" +
                "| " + norm(r3_c1) + ' ' + norm(r3_c2) + ' ' + norm(r3_c3) + ' ' + norm(r3_c4) + " |\n" +
                "| +0.0 +0.0 +0.0 +1.0 |\n";
    }

    private String norm(Float f) { return (f < 0) ? f.toString() : "+" + f; }

    public static void main(String... args) {

        System.out.println(new Transform(3.0f, 0.0f, 0.0f));

        List<Float> pFloatsA = Arrays.asList(
                1.0f, 0.0f, 0.0f, 2.0f,
                0.0f, 0.0f, -1.0f, -1.0f,
                0.0f, 1.0f, 0.0f, 2.0f
        );

        List<Float> pFloatsB = Arrays.asList(
                0.0f, -1.0f, 0.0f, -3.0f,
                0.0f, 0.0f, 1.0f, -1.0f,
                -1.0f, 0.0f, 0.0f, -3.0f
        );

        Transform a = new Transform(pFloatsA);
        Transform b = new Transform(pFloatsB);

        System.out.println(a);
        System.out.println(b);

        System.out.println(Transform.MatrixMultiply(a, b));

        System.out.println(a.matrixMultiply(b));
    }
}

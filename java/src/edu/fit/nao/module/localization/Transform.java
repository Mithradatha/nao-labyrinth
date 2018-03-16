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

    public Transform(List<Float> pFloats) {

        this.r1_c1 = pFloats.get(0);
        this.r1_c2 = pFloats.get(1);
        this.r1_c3 = pFloats.get(2);
        this.r1_c4 = pFloats.get(3);

        this.r2_c1 = pFloats.get(4);
        this.r2_c2 = pFloats.get(5);
        this.r2_c3 = pFloats.get(6);
        this.r2_c4 = pFloats.get(7);

        this.r3_c1 = pFloats.get(8);
        this.r3_c2 = pFloats.get(9);
        this.r3_c3 = pFloats.get(10);
        this.r3_c4 = pFloats.get(11);
    }

    // populate translation vector
    public Transform(float pPosX, float pPosY, float pPosZ) {

        this();

        this.r1_c4 = pPosX;
        this.r2_c4 = pPosY;
        this.r3_c4 = pPosZ;
    }

    public Position3D translation() {

        return new Position3D(this.r1_c4, this.r2_c4, this.r3_c4);
    }

    // the float values for euler angle in radians
    public Transform from3DRotation(float pWX, float pWY, float pWZ) {

        Transform transform = this.clone();

        Transform rotZ = transform.fromRotZ(pWZ);
        Transform rotY = transform.fromRotY(pWY);
        Transform rotX = transform.fromRotX(pWX);

        return rotZ.matrixMultiply(rotY).matrixMultiply(rotX);
    }

    // the float values for euler angle in radians
    public static Transform From3DRotation(float pWX, float pWY, float pWZ) {

        Transform transform = MatrixMultiply(FromRotZ(pWZ), FromRotY(pWY));
        return MatrixMultiply(transform, FromRotX(pWX));
    }

    // the float value for angle rotation in radian around z axis
    public Transform fromRotZ(float pRotZ) {

        Transform transform = this.clone();

        transform.r1_c1 = (float) Math.cos(pRotZ);
        transform.r1_c2 = (float) (-1 * Math.sin(pRotZ));
        transform.r2_c1 = (float) Math.sin(pRotZ);
        transform.r2_c2 = (float) Math.cos(pRotZ);

        return transform;
    }

    // the float value for angle rotation in radian around z axis
    public static Transform FromRotZ(float pRotZ) {

        Transform transform = new Transform();
        transform.r1_c1 = (float) Math.cos(pRotZ);
        transform.r1_c2 = (float) (-1 * Math.sin(pRotZ));
        transform.r2_c1 = (float) Math.sin(pRotZ);
        transform.r2_c2 = (float) Math.cos(pRotZ);

        return transform;
    }

    // the float value for angle rotation in radian around y axis
    public Transform fromRotY(float pRotY) {

        Transform transform = this.clone();

        transform.r1_c1 = (float) Math.cos(pRotY);
        transform.r1_c3 = (float) Math.sin(pRotY);
        transform.r3_c1 = (float) (-1 * Math.sin(pRotY));
        transform.r3_c3 = (float) Math.cos(pRotY);

        return transform;
    }

    // the float value for angle rotation in radian around y axis
    public static Transform FromRotY(float pRotY) {

        Transform transform = new Transform();
        transform.r1_c1 = (float) Math.cos(pRotY);
        transform.r1_c3 = (float) Math.sin(pRotY);
        transform.r3_c1 = (float) (-1 * Math.sin(pRotY));
        transform.r3_c3 = (float) Math.cos(pRotY);

        return transform;
    }

    // the float value for angle rotation in radian around x axis
    public Transform fromRotX(float pRotX) {

        Transform transform = this.clone();

        transform.r2_c2 = (float) Math.cos(pRotX);
        transform.r2_c3 = (float) (-1 * Math.sin(pRotX));
        transform.r3_c2 = (float) Math.sin(pRotX);
        transform.r3_c3 = (float) Math.cos(pRotX);

        return transform;
    }

    // the float value for angle rotation in radian around x axis
    public static Transform FromRotX(float pRotX) {

        Transform transform = new Transform();
        transform.r2_c2 = (float) Math.cos(pRotX);
        transform.r2_c3 = (float) (-1 * Math.sin(pRotX));
        transform.r3_c2 = (float) Math.sin(pRotX);
        transform.r3_c3 = (float) Math.cos(pRotX);

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

        List<Float> pFloats = new ArrayList<>(12);

        pFloats.add(a.r1_c1 * b.r1_c1 + a.r1_c2 * b.r2_c1 + a.r1_c3 * b.r3_c1);
        pFloats.add(a.r1_c1 * b.r1_c2 + a.r1_c2 * b.r2_c2 + a.r1_c3 * b.r3_c2);
        pFloats.add(a.r1_c1 * b.r1_c3 + a.r1_c2 * b.r2_c3 + a.r1_c3 * b.r3_c3);
        pFloats.add(a.r1_c1 * b.r1_c4 + a.r1_c2 * b.r2_c4 + a.r1_c3 * b.r3_c4 + a.r1_c4);

        pFloats.add(a.r2_c1 * b.r1_c1 + a.r2_c2 * b.r2_c1 + a.r2_c3 * b.r3_c1);
        pFloats.add(a.r2_c1 * b.r1_c2 + a.r2_c2 * b.r2_c2 + a.r2_c3 * b.r3_c2);
        pFloats.add(a.r2_c1 * b.r1_c3 + a.r2_c2 * b.r2_c3 + a.r2_c3 * b.r3_c3);
        pFloats.add(a.r2_c1 * b.r1_c4 + a.r2_c2 * b.r2_c4 + a.r2_c3 * b.r3_c4 + a.r2_c4);

        pFloats.add(a.r3_c1 * b.r1_c1 + a.r3_c2 * b.r2_c1 + a.r3_c3 * b.r3_c1);
        pFloats.add(a.r3_c1 * b.r1_c2 + a.r3_c2 * b.r2_c2 + a.r3_c3 * b.r3_c2);
        pFloats.add(a.r3_c1 * b.r1_c3 + a.r3_c2 * b.r2_c3 + a.r3_c3 * b.r3_c3);
        pFloats.add(a.r3_c1 * b.r1_c4 + a.r3_c2 * b.r2_c4 + a.r3_c3 * b.r3_c4 + a.r3_c4);

        return new Transform(pFloats);
    }

    @Override
    protected Transform clone() {

        try {
            return (Transform) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {

        return "| " + normalize(r1_c1) + ' ' + normalize(r1_c2) +
                ' ' + normalize(r1_c3) + ' ' + normalize(r1_c4) + " |\n" +
                "| " + normalize(r2_c1) + ' ' + normalize(r2_c2) +
                ' ' + normalize(r2_c3) + ' ' + normalize(r2_c4) + " |\n" +
                "| " + normalize(r3_c1) + ' ' + normalize(r3_c2) +
                ' ' + normalize(r3_c3) + ' ' + normalize(r3_c4) + " |\n" +
                "| +0.0 +0.0 +0.0 +1.0 |\n";
    }

    private String normalize(Float f) {

        return (f < 0) ? f.toString() : "+" + f;
    }

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

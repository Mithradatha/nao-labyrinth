package edu.fit.nao;

import java.util.List;

public class ShapeInfo {

    private int one;
    private float alpha;
    private float beta;
    private float sizeX;
    private float sizeY;
    private float heading;

    public ShapeInfo(Object o) {

        if (o instanceof List) {

            List shapeInfo = (List)o;
            one = (int)shapeInfo.get(0);
            alpha = (float)shapeInfo.get(1);
            beta = (float)shapeInfo.get(2);
            sizeX = (float)shapeInfo.get(3);
            sizeY = (float)shapeInfo.get(4);
            heading = (float)shapeInfo.get(5);

        } else throw new ClassCastException("ShapeInfo conversion not implemented correctly");
    }

    @Override
    public String toString() {

        return "ShapeInfo {\n" +
                one + ",\n" +
                alpha + ",\n" +
                beta + ",\n" +
                sizeX + ",\n" +
                sizeY + ",\n" +
                heading + "\n" +
                "}";
    }
}

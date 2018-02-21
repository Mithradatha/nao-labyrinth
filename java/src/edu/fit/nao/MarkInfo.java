package edu.fit.nao;

import java.util.List;

public class MarkInfo {

    private ShapeInfo shapeInfo;
    private int markID;

    public MarkInfo(Object o) {

        if (o instanceof List) {

            List markInfo = (List)o;
            shapeInfo = new ShapeInfo(markInfo.get(0));
            markID = (int)markInfo.get(1);

        } else throw new ClassCastException("MarkInfo conversion not implemented correctly");
    }

    @Override
    public String toString() {

        return "MarkInfo {\n" +
                shapeInfo.toString() + ",\n" +
                markID + "\n" +
                "}";
    }
}

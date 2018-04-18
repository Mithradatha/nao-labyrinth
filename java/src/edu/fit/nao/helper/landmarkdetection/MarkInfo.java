package edu.fit.nao.helper.landmarkdetection;

import edu.fit.nao.helper.ALValue;

import java.util.List;

/**
 * MarkInfo {
 * ShapeInfo,
 * MarkID
 * }
 */
public class MarkInfo extends ALValue {

    public final ShapeInfo shapeInfo;
    public final int markID;

    public MarkInfo(ShapeInfo shapeInfo, int markID) {

        this.shapeInfo = shapeInfo;
        this.markID = markID;
    }

    public static MarkInfo FromALValue(List alValue) {

        ShapeInfo shapeInfo = ShapeInfo.FromALValue((List) alValue.get(0));
        int markID = (int) ((List) alValue.get(1)).get(0);

        return new MarkInfo(shapeInfo, markID);
    }
}

package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.ALValue;

import java.util.List;

/**
 * MarkInfo {
 * ShapeInfo,
 * MarkID
 * }
 */
public class MarkInfo extends ALValue<List> {

    public ShapeInfo shapeInfo;
    public int markID;

    public MarkInfo(final ShapeInfo shapeInfo, final int markID) {

        this.shapeInfo = shapeInfo;
        this.markID = markID;
    }

    public MarkInfo(List alValue) {

        this(
                new ShapeInfo((List) alValue.get(0)),
                (int) ((List) alValue.get(1)).get(0)
        );
    }
}

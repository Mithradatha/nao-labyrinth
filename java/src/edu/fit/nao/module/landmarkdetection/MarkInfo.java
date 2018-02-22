package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.Util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MarkInfo {
 * ShapeInfo,
 * MarkID
 * }
 */
public class MarkInfo {

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

    @Override
    public String toString() {

        List<Map.Entry<String, Object>> fields = new ArrayList<>();
        fields.add(new AbstractMap.SimpleImmutableEntry<String, Object>("markID", markID));
        fields.add(new AbstractMap.SimpleImmutableEntry<String, Object>("shapeInfo", shapeInfo));

        return Util.ToJson(fields);
    }
}

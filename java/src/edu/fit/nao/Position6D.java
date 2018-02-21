package edu.fit.nao;

import java.util.List;

public class Position6D {

    private int x;
    private int y;
    private int z;
    private float wx;
    private float wy;
    private float wz;

    public Position6D(Object o) {

        if (o instanceof List) {

            List position6D = (List)o;
            x = (int)position6D.get(0);
            y = (int)position6D.get(1);
            z = (int)position6D.get(2);
            wx = (float)position6D.get(3);
            wy = (float)position6D.get(2);
            wz = (float)position6D.get(3);

        } else throw new ClassCastException("Position6D conversion not implemented correctly");
    }

    @Override
    public String toString() {

        return "Position6D {\n" +
                x + ",\n" +
                y + ",\n" +
                z + ",\n" +
                wx + ",\n" +
                wy + ",\n" +
                wz + "\n" +
                "}";
    }
}

package edu.fit.nao.helper;

import java.lang.reflect.Field;

public abstract class ALValue {

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        Class<?> oClass = this.getClass();

        Field[] fields = oClass.getDeclaredFields();
        for (final Field field : fields) {

            try {

                Object value = field.get(this);
                String fieldName = field.getName();

                stringBuilder.append(fieldName).append(": ")
                        .append(value.toString()).append(",\n");

            } catch (IllegalAccessException ignored) {

                stringBuilder.append("N/A").append(",\n");
            }
        }

        // remove the last comma
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}

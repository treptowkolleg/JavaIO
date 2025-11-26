package treptowkolleg.model.orm;

import java.lang.reflect.Field;

public class ORMUtils {

    /**
     * Liefert den SQL-Spaltentyp basierend auf der @Column-Annotation des
     * Feldes.
     * Wirft eine Exception, wenn das Feld nicht mit @Column annotiert ist.
     */
    public static String toSqlType(Field field) {
        ORMType type;
        int length;

        if (field.isAnnotationPresent(PrimaryKey.class)) {
            PrimaryKey pk = field.getAnnotation(PrimaryKey.class);
            type = pk.type();
            length = pk.length();
        } else if (field.isAnnotationPresent(Column.class)) {
            Column col = field.getAnnotation(Column.class);
            type = col.type();
            length = col.length();
        } else {
            throw new IllegalArgumentException(
                    "Feld muss @PrimaryKey oder @Column tragen: " +
                            field.getDeclaringClass().getSimpleName() + "." + field.getName()
            );
        }
        assert type != null;
        return switch (type) {
            case VARCHAR -> "VARCHAR(" + length + ")";
            case TEXT -> "TEXT";
            case INTEGER -> "INT";
            case BIGINT -> "BIGINT";
            case REAL -> "REAL";
            case BOOLEAN -> "BOOLEAN";
            case DATE -> "DATE";
            case TIME -> "TIME";
            case DATETIME -> "DATETIME";
        };
    }

    public static String toSnakeTail(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String snake =
                String.join("_", value.split("(?=\\p{Upper})")).toLowerCase();
        if (snake.charAt(0) == '_') {
            return snake.substring(1);
        }
        return snake;
    }

    public static String toCamelCase(String snake) {
        return toCamelCase(snake, false);
    }

    public static String toPascalCase(String snake) {
        return toCamelCase(snake, true);
    }

    private static String toCamelCase(String snake, boolean upperFirst) {
        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = upperFirst;

        if (snake == null || snake.isEmpty()) {
            return snake;
        }
        snake = snake.toLowerCase();
        for (char c : snake.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    result.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }
        return result.toString();
    }
}

package com.example.database;

import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class DatabaseTable<T> {
    private List<T> table;

    public void insert(T entity) {
        table.add(entity);
    }

    public List<T> selectByField(String fieldName, Object value) {
        List<T> result = new ArrayList<>();
        for (T entity : table) {
            try {
                Field field = entity.getClass().getDeclaredField(fieldName);
                // Enable access to private fields if necessary
                field.setAccessible(true);
                Object fieldValue = field.get(entity);
                if (fieldValue != null && fieldValue.equals(value)) {
                    result.add(entity);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return result;
    }

    public List<T> getAll() {
        return new ArrayList<>(table);
    }

    public List<T> selectByFields(List<String> fieldNames, List<Object> values) {
        List<T> result = new ArrayList<>();
        for (T entity : table) {
            boolean matches = true;
            for (int i = 0; i < fieldNames.size(); i++) {
                String fieldName = fieldNames.get(i);
                Object value = values.get(i);
                try {
                    Field field = entity.getClass().getDeclaredField(fieldName);
                    // Enable access to private fields if necessary
                    field.setAccessible(true);
                    Object fieldValue = field.get(entity);
                    if (fieldValue == null || !fieldValue.equals(value)) {
                        matches = false;
                        break;
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    return new ArrayList<>();
                }
            }
            if (matches) {
                result.add(entity);
            }
        }
        return result;
    }

}

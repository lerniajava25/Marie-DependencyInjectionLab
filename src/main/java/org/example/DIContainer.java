package org.example;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DIContainer {

    private final Map<Class<?>, Class<?>> bindings = new HashMap<>();

    public <T> void bind(Class<T> abstraction, Class<? extends T> implementation) {
        bindings.put(abstraction, implementation);
    }

    public <T> T getInstance(Class<T> type) {
        return getInstance(type, new HashSet<>());
    }

    private <T> T getInstance(Class<T> type, Set<Class<?>> activeTypes) {

        try {
            Class<?> implementation = bindings.get(type);

            if (implementation != null) {
                type = (Class<T>) implementation;
            }

            if (type.isInterface()) {
                throw new IllegalArgumentException("No implementation bound for " + type.getName());
            }

            if (!activeTypes.add(type)) {
                throw new IllegalArgumentException("Circular dependency found for " + type.getName());
            }

            try {
                Constructor<?> constructor = type.getDeclaredConstructors()[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] dependencies = new Object[parameterTypes.length];

                for (int i = 0; i < parameterTypes.length; i++) {
                    dependencies[i] = getInstance(parameterTypes[i], activeTypes);
                }

                return type.cast(constructor.newInstance(dependencies));
            } finally {
                activeTypes.remove(type);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DIContainer {

    private final Map<Class<?>, Class<?>> bindings = new HashMap<>();

    public <T> void bind(Class<T> abstraction, Class<? extends T> implementation) {
        bindings.put(abstraction, implementation);
    }

    public <T> T getInstance(Class<T> type) {

        try {
            Class<?> implementation = bindings.get(type);

            if (implementation != null) {
                type = (Class<T>) implementation;
            }

            if (type.isInterface()) {
                throw new IllegalArgumentException("No implementation bound for " + type.getName());
            }
            Constructor<?> constructor = type.getDeclaredConstructors()[0];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] dependencies = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                dependencies[i] = getInstance(parameterTypes[i]);
            }

            return type.cast(constructor.newInstance(dependencies));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

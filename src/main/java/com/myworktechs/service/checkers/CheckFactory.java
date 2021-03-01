package com.myworktechs.service.checkers;

import com.myworktechs.service.checkers.collective.CollectAndCheck;

import java.lang.reflect.InvocationTargetException;

public class CheckFactory {

    public static CollectAndCheck fromThresholdTypeAccumulative(Class<? extends CollectAndCheck> collectiveImplementationClazz, long threshold) {
        try {
            return collectiveImplementationClazz.getDeclaredConstructor(Long.class).newInstance(threshold);
        } catch (IllegalAccessException | ClassCastException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Check fromThresholdType(Class<? extends Check> implementationClazz, long threshold) {
        try {
            return implementationClazz.getDeclaredConstructor(long.class).newInstance(threshold);
        } catch (IllegalAccessException | ClassCastException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

package org.actressframework.core;

import java.util.HashMap;
import java.util.Map;

class ActorThreadNamer {

    private Map<Object, String> names = new HashMap<Object, String>();

    synchronized String name(Object target) {
        if (!names.containsKey(target)) {
            names.put(target, generateName(target));
        }
        return names.get(target);
    }

    private String generateName(Object target) {
        return target.getClass().getSimpleName() + "-Actor-" + actorCount(target);
    }

    private int actorCount(Object target) {
        int count = 0;
        for (Object o : names.keySet()) {
            if (o.getClass().equals(target.getClass())) {
                count++;
            }
        }
        return count;
    }
}

package org.example.util;

import org.example.model.PriorityQueue;
import org.example.model.Set;
import org.example.model.StaticPriorityQueue;
import org.example.model.StaticSet;

public class PriorityQueueUtil {

    public static PriorityQueue copy(PriorityQueue queue) {
        PriorityQueue aux = new StaticPriorityQueue();
        PriorityQueue aux2 = new StaticPriorityQueue();

        while(!queue.isEmpty()) {
            aux.add(queue.getFirst(), queue.getPriority());
            aux2.add(queue.getFirst(), queue.getPriority());
            queue.remove();
        }

        while(!aux2.isEmpty()) {
            queue.add(aux2.getFirst(), aux2.getPriority());
            aux2.remove();
        }

        return aux;
    }
    public static boolean allPrioritiesAreValues(PriorityQueue q) {
        Set priorities = new StaticSet();
        Set values = new StaticSet();

        PriorityQueue temp = new StaticPriorityQueue();

        while (!q.isEmpty()) {
            int value = q.getFirst();
            int priority = q.getPriority();
            values.add(value);
            priorities.add(priority);
            temp.add(value, priority);
            q.remove();
        }

        while (!temp.isEmpty()) {
            q.add(temp.getFirst(), temp.getPriority());
            temp.remove();
        }

        return SetUtil.subseteq(priorities, values);
    }



}

package org.example.util;

import org.example.model.Queue;
import org.example.model.Set;
import org.example.model.StaticQueue;
import org.example.model.StaticSet;

public class QueueUtil {
    public static Queue copy(Queue queue) {
        Queue aux = new StaticQueue();
        Queue aux2 = new StaticQueue();

        while(!queue.isEmpty()) {
            aux.add(queue.getFirst());
            aux2.add(queue.getFirst());
            queue.remove();
        }

        while(!aux2.isEmpty()) {
            queue.add(aux2.getFirst());
            aux2.remove();
        }

        return aux;
    }
    public static Set commonElementsInQueues(Queue q1, Queue q2) {
        Set set1 = queueToSet(q1);
        Set set2 = queueToSet(q2);

        return SetUtil.intersection(set1, set2);
    }
    public static Set queueToSet(Queue q) {
        Set result = new StaticSet();
        Queue temp = new StaticQueue();

        while (!q.isEmpty()) {
            int value = q.getFirst();
            result.add(value);
            temp.add(value);
            q.remove();
        }

        while (!temp.isEmpty()) {
            q.add(temp.getFirst());
            temp.remove();
        }

        return result;
    }
}

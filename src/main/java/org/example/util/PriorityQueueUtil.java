package org.example.util;

import org.example.model.PriorityQueue;
import org.example.model.StaticPriorityQueue;

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



}

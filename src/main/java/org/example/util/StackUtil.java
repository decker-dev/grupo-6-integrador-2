package org.example.util;

import org.example.model.Stack;
import org.example.model.StaticStack;

public class StackUtil {

    public static Stack copy(Stack stack) {
        Stack aux = new StaticStack();
        Stack aux2 = new StaticStack();

        while(!stack.isEmpty()) {
            aux.add(stack.getTop());
            aux2.add(stack.getTop());
            stack.remove();
        }

        while(!aux2.isEmpty()) {
            stack.add(aux2.getTop());
            aux2.remove();
        }

        while(!aux.isEmpty()) {
            aux2.add(aux.getTop());
            aux.remove();
        }

        return aux2;
    }

    public static void print(Stack stack) {
        Stack copy = copy(stack);
        while(!copy.isEmpty()) {
            System.out.println(copy.getTop());
            copy.remove();
        }
    }

    public static int middle(Stack stack) {
        Stack copy = copy(stack);
        Stack aux = new StaticStack();
        Stack aux2 = new StaticStack();
        Stack aux3 = new StaticStack();

        boolean p = true;
        while(!copy.isEmpty()) {
            if(p) {
                aux.add(copy.getTop());
            } else {
                aux2.add(copy.getTop());
            }
            p = !p;
            aux3.add(copy.getTop());
        }

        while(!aux2.isEmpty()) {
            aux3.remove();
            aux2.remove();
        }

        return aux3.getTop();
    }

}

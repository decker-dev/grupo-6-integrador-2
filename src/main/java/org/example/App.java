package org.example;

import org.example.model.*;
import org.example.model.Set;
import org.example.util.*;
import org.example.model.Dictionary;
import org.example.model.StaticDictionary;

public class App {

    public static void main(String[] args) {
        System.out.println("==== PRUEBA DE LsAS TABLAS DE VERDAD ====\n");

        probarTDA(new UnaryBinaryLogicTruthTable(), "UnaryBinaryLogicTruthTable");
        probarTDA(new BinaryBinaryLogicTruthTable(), "BinaryBinaryLogicTruthTable");
        probarTDA(new UnaryTernaryLogicTruthTable(), "UnaryTernaryLogicTruthTable");
        probarTDA(new BinaryTernaryLogicTruthTable(), "BinaryTernaryLogicTruthTable");

        System.out.println("\n==== PRUEBA DE DICTIONARY ====\n");

        Dictionary dictionary = new StaticDictionary();
        dictionary.add(1, 2);
        dictionary.add(2, 4);
        dictionary.add(8, 16);
        DictionaryUtil.print(dictionary);
    }

    public static void probarTDA(LogicTruthTable tda, String nombre) {
        System.out.println(">>> Probando: " + nombre);

        // Obtener y mostrar las operaciones disponibles
        Set operaciones = tda.obtenerOperaciones();
        System.out.println("Operaciones disponibles:");
        while (!operaciones.isEmpty()) {
            int codigo = operaciones.choose();
            System.out.println("- " + LogicTruthTableUtil.convertirEnteroAOperacion(codigo));
            operaciones.remove(codigo);
        }

        // Probar cada operación
        System.out.println("\nTablas de verdad:");
        String[] operacionesPrueba = {"AND", "OR", "XOR", "NAND", "NOR", "NOT"};

        for (String operacion : operacionesPrueba) {
            try {
                int[][] tabla = tda.obtener(operacion);
                System.out.println("Tabla de " + operacion + ":");
                for (int[] fila : tabla) {
                    if (fila.length == 3) {
                        System.out.println(fila[0] + " " + operacion + " " + fila[1] + " = " + fila[2]);
                    } else {
                        System.out.println(operacion + " " + fila[0] + " = " + fila[1]);
                    }
                }
                System.out.println();
            } catch (RuntimeException e) {
                System.out.println("Operación '" + operacion + "' no encontrada en " + nombre);
            }
        }

        System.out.println("=============================================\n");

        System.out.println("EJERCICIO 5");

        Stack stack = new StaticStack();
        Stack stack2 = new StaticStack();

        stack.add(1);
        stack.add(2);
        stack.add(3);

        stack2.add(2);
        stack2.add(3);
        stack2.add(4);

        System.out.println("Elementos comunes en pilas: ");
        Set common = StackUtil.commonElementsInStacks(stack, stack2);
        SetUtil.printSet(common);

        Queue queue = new StaticQueue();
        Queue queue2 = new StaticQueue();

        queue.add(10);
        queue.add(20);
        queue.add(30);

        queue2.add(20);
        queue2.add(30);
        queue2.add(40);


        System.out.println("Elementos comunes en colas: ");
        Set commonQ = QueueUtil.commonElementsInQueues(queue, queue2);
        SetUtil.printSet(commonQ);

        PriorityQueue priorityQueue = new StaticPriorityQueue();

        priorityQueue.add(5, 1);
        priorityQueue.add(2, 2);
        priorityQueue.add(1, 5);
        priorityQueue.add(50, 1);

        System.out.println("¿Todas las prioridades aparecen como valores? " + PriorityQueueUtil.allPrioritiesAreValues(priorityQueue));

        priorityQueue.add(50, 99);

        System.out.println("¿Todas las prioridades aparecen como valores? " + PriorityQueueUtil.allPrioritiesAreValues(priorityQueue));
    }
}

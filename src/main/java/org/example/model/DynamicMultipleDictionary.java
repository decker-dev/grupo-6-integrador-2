package org.example.model;

import org.example.model.nodes.DictionaryNode;
import org.example.model.nodes.MultipleDictionaryNode;
import org.example.model.nodes.Node;

public class DynamicMultipleDictionary implements MultipleDictionary {

    private MultipleDictionaryNode node;

    @Override
    public List get(int key) {
        if(this.node == null) {
            throw new RuntimeException("No se puede obtener un valor de una estructura vacía");
        }
        MultipleDictionaryNode aux = this.node;
        while(aux.getNext() != null) {
            if(aux.getKey() == key) {
                return map(aux.getValue());
            }
            aux = aux.getNext();
        }
        if(aux.getKey() == key) {
            return map(aux.getValue());
        }

        throw new RuntimeException("La clave no existe");
    }

    private List map(Node node) {
        List list = new LinkedList();
        Node aux = node;
        while(aux != null) {
            list.add(aux.getValue());
            aux = aux.getNext();
        }
        return list;
    }

    @Override
    public Set getKeys() {
        Set result = new StaticSet();

        MultipleDictionaryNode aux = this.node;
        while(aux != null) {
            result.add(aux.getKey());
            aux = aux.getNext();
        }

        return result;
    }

    @Override
    public void add(int key, int value) {
        if(this.node == null) {
            this.node = new MultipleDictionaryNode(key, new Node(value, null), null);
            return;
        }

        MultipleDictionaryNode aux = this.node;
        while(aux.getNext() != null) {
            if(aux.getKey() == key) {
                Node aux2 = aux.getValue();

                while(aux2.getNext() != null) {
                    aux2 = aux2.getNext();
                }

                aux2.setNext(new Node(value, null));
                return;
            }
            aux = aux.getNext();
        }

        if(aux.getKey() == key) {
            Node aux2 = aux.getValue();

            while(aux2.getNext() != null) {
                aux2 = aux2.getNext();
            }

            aux2.setNext(new Node(value, null));
            return;
        }

        aux.setNext(new MultipleDictionaryNode(key, new Node(value, null), null));
    }

    @Override
    public void remove(int key, int value) {
        if(this.node == null) {
            throw new RuntimeException("La clave no existe");
        }

        if(this.node.getNext() == null) {
            if(this.node.getKey() == key) {
                if(this.node.getValue().getNext() == null) {
                    if(this.node.getValue().getValue() == value) {
                        this.node = null;
                        return;
                    }
                    throw new RuntimeException("No existe el valor para la clave indicada");
                }

                boolean result = remove(value, this.node.getValue());
                if(!result) {
                    throw new RuntimeException("No existe el valor para la clave indicada");
                }

                return;
            }
            throw new RuntimeException("La clave no existe");
        }

        if(this.node.getKey() == key) {
            if(this.node.getValue().getNext() == null) {
                if(this.node.getValue().getValue() == value) {
                    this.node = this.node.getNext();
                    return;
                }
                throw new RuntimeException("No existe el valor para la clave indicada");
            }

            boolean result = remove(value, this.node.getValue());
            if(!result) {
                throw new RuntimeException("No existe el valor para la clave indicada");
            }
            return;
        }

        MultipleDictionaryNode backup = this.node;
        MultipleDictionaryNode current = this.node.getNext();
        while(current != null) {
            if(current.getKey() == key) {
                if(current.getValue().getNext() == null) {
                    if(current.getValue().getValue() == value) {
                        backup.setNext(current.getNext());
                        return;
                    }
                    throw new RuntimeException("No existe el valor para la clave indicada");
                }
                boolean result = remove(value, current.getValue());
                if(!result) {
                    throw new RuntimeException("No existe el valor para la clave indicada");
                }
                return;
            }
            backup = current;
            current = current.getNext();
        }

        throw new RuntimeException("La clave no existe");
    }

    /**
     * Precondición: El tamaño de la lista es > 1.
     * @param value, valor a borrar
     * @param node, lista enlazada que tendrá borrado el nodo que contiene value
     * @return true, si pudo borrar
     */
    private boolean remove(int value, Node node) {
        Node backup = node;
        Node current = node.getNext();

        while(current.getNext() != null) {
            if(current.getValue() == value) {
                backup.setNext(current.getNext());
                return true;
            }
            backup = current;
            current = current.getNext();
        }

        if(current.getValue() == value) {
            backup.setNext(current.getNext());
            return true;
        }

        return false;
    }
}

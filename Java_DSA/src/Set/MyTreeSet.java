package Set;

import java.util.*;

public class MyTreeSet<E> implements Set<E> {

    private Node root;
    private int size;
    private Comparator<? super E> comparator;

    private class Node {
        private E element;
        private Node left;
        private Node right;

        public Node(E element) {
            this.element = element;
        }
    }

    public MyTreeSet() {
        this.comparator = null;
    }

    public MyTreeSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public boolean add(E element) {
        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                if (comparator != null) {
                    int cmp = comparator.compare(element, current.element);
                    if (cmp < 0) {
                        parent = current;
                        current = current.left;
                        if (current == null) {
                            parent.left = new Node(element);
                            size++;
                            return true;
                        }
                    } else if (cmp > 0) {
                        parent = current;
                        current = current.right;
                        if (current == null) {
                            parent.right = new Node(element);
                            size++;
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    Comparable<? super E> comp = (Comparable<? super E>) element;
                    int cmp = comp.compareTo(current.element);
                    if (cmp < 0) {
                        parent = current;
                        current = current.left;
                        if (current == null) {
                            parent.left = new Node(element);
                            size++;
                            return true;
                        }
                    } else if (cmp > 0) {
                        parent = current;
                        current = current.right;
                        if (current == null) {
                            parent.right = new Node(element);
                            size++;
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public boolean contains(Object o) {
        E element = (E) o;
        Node current = root;
        while (current != null) {
            if (comparator != null) {
                int cmp = comparator.compare(element, current.element);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    return true;
                }
            } else {
                Comparable<? super E> comp = (Comparable<? super E>) element;
                int cmp = comp.compareTo(current.element);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(Object o) {
        E element = (E) o;
        Node current = root;
        Node parent = null;
        boolean isLeftChild = true;
        while (current != null) {
            if (comparator != null) {
                int cmp = comparator.compare(element, current.element);
                if (cmp < 0) {
                    parent = current;
                    current = current.left;
                    isLeftChild = true;
                } else if (cmp > 0) {
                    parent = current;
                    current = current.right;
                    isLeftChild = false;
                } else {
                    if (current.left == null && current.right == null) {
                        if (current == root) {
                            root = null;
                        } else if (isLeftChild) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    } else if (current.left == null) {
                        if (current == root) {
                            root = current.right;
                        } else if (isLeftChild) {
                            parent.left = current.right;
                        } else {
                            parent.right = current.right;
                        }
                    } else if (current.right == null) {
                        if (current == root) {
                            root = current.left;
                        } else if (isLeftChild) {
                            parent.left = current.left;
                        } else {
                            parent.right = current.left;
                        }
                    } else {
                        Node successor = getSuccessor(current);
                        current.element = successor.element;
                        current = successor;
                    }
                    size--;
                    return true;
                }
            } else {
                Comparable<? super E> comp = (Comparable<? super E>) element;
                int cmp = comp.compareTo(current.element);
                if (cmp < 0) {
                    parent = current;
                    current = current.left;
                    isLeftChild = true;
                } else if (cmp > 0) {
                    parent = current;
                    current = current.right;
                    isLeftChild = false;
                } else {
                    if (current.left == null && current.right == null) {
                        if (current == root) {
                            root = null;
                        } else if (isLeftChild) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    } else if (current.left == null) {
                        if (current == root) {
                            root = current.right;
                        } else if (isLeftChild) {
                            parent.left = current.right;
                        } else {
                            parent.right = current.right;
                        }
                    } else if (current.right == null) {
                        if (current == root) {
                            root = current.left;
                        } else if (isLeftChild) {
                            parent.left = current.left;
                        } else {
                            parent.right = current.left;
                        }
                    } else {
                        Node successor = getSuccessor(current);
                        current.element = successor.element;
                        current = successor;
                    }
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Iterator<E> iterator() {
        return new TreeSetIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    private class TreeSetIterator implements Iterator<E> {

        private Node current = root;
        private int expectedModCount = size;
        private Stack<Node> stack = new Stack<>();

        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        public E next() {
            checkForConcurrentModification();
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            E element = current.element;
            current = current.right;
            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void checkForConcurrentModification() {
            if (expectedModCount != size) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
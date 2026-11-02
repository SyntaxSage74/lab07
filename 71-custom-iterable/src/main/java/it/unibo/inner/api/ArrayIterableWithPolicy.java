package it.unibo.inner.api;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterableWithPolicy<T> implements IterableWithPolicy<T> {
    private final T[] elements;
    private Predicate<T> filter;

    public ArrayIterableWithPolicy(T[] elements) {
        this(elements, x -> true);
    }

    public ArrayIterableWithPolicy(T[] elements, Predicate<T> filter) {
        this.elements = elements;
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    class ArrayIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            while(index < elements.length) {
                if(filter.test(elements[index])) {
                    return true;
                }

                index++;
            }
            
            return false;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            return elements[index++];
        }
    }
        
}

package dev.katsute.civitai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Page<T> implements Iterator<T> {

    private int index = -1; // thread safe by methods
    List<T> list = new ArrayList<>();
    int size;

    private boolean hasNextItem(){
        return index + 1 < size;
    }

    @Override
    public final boolean hasNext(){
        return hasNextItem() || hasNextPage();
    }

    @Override
    public synchronized final T next(){
        if(hasNextItem())
            return list.get(++index);
        else if(hasNextPage()){
            nextPage();
            return list.get(++index);
        }else
            throw new NoSuchElementException();
    }

    /**
     * Returns if the response has a next page.
     *
     * @return if has next page
     *
     * @since 1.0.0
     */
    abstract boolean hasNextPage();

    private synchronized void nextPage(){
        list = getNextPage();
        size = list.size();
        index = -1;
    }

    /**
     * Returns the next page in the response.
     *
     * @return next page
     *
     * @since 1.0.0
     */
    abstract List<T> getNextPage();

    @Override
    public synchronized final void remove(){
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the current page as a list.
     *
     * @return list
     *
     * @since 1.0.0
     */
    public final List<T> toList(){
        return new ArrayList<>(list);
    }

}

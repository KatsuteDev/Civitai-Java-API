package dev.katsute.civitai.query;

public abstract class Page<T> {

    public abstract int getPageIndex();

    public abstract int getPageSize();

    public abstract Page<T> next();

    public abstract Page<T> last();

}

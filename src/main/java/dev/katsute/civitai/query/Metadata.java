package dev.katsute.civitai.query;

public abstract class Metadata {

    public abstract int getTotalItems();

    public abstract int getPage();

    public abstract int getPageSize();

    public abstract int getPageCount();

}

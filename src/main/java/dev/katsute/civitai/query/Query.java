package dev.katsute.civitai.query;

public abstract class Query<R> {

    public abstract Query<R> limit(int limit);

    public abstract Query<R> page(int page);

    public abstract Query<R> query(String query);

    public abstract Page<R> search();

}
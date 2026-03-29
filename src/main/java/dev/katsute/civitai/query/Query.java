package dev.katsute.civitai.query;

import dev.katsute.civitai.Page;

public abstract class Query<R, Q extends Query<R, Q>> {

    public abstract Q limit(int limit);

    public abstract Q page(int page);

    public abstract Q query(String query);

    //

    public abstract R[] search();

    public abstract Page<R> searchAll();

}
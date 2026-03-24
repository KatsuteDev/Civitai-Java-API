package dev.katsute.civitai.model;

import dev.katsute.civitai.query.Page;

public abstract class ModelVersionPage extends Page<ModelVersion> {

    public abstract int getTotalItems();

    public abstract int getTotalPages();

}

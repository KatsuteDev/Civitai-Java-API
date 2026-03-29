package dev.katsute.civitai.model;

import dev.katsute.civitai.Page;

public abstract class ModelVersionPage extends Page<ModelVersion> {

    public abstract int getTotalItems();

    public abstract int getTotalPages();

}

package dev.katsute.civitai;

import dev.katsute.civitai.Json.JsonObject;
import dev.katsute.civitai.image.Image;
import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelVersion;
import dev.katsute.civitai.model.file.ModelFile;
import dev.katsute.civitai.tag.Tag;

final class CivitaiObjectService {

    private final CivitaiService service;

    CivitaiObjectService(final CivitaiService service){
        this.service = service;
    }

    final Creator asCreator(final JsonObject obj){
        return null;
    }

    final Model asModel(final JsonObject obj){
        return null;
    }

    final ModelVersion asModelVersion(final JsonObject obj){
        return null;
    }

    final ModelFile asModelFile(final JsonObject obj){
        return null;
    }

    final Image asImage(final JsonObject obj){
        return null;
    }

    final Tag asTag(final JsonObject obj){
        return null;
    }

}

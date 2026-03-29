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
        return new Creator(){

            private final String username = obj.getString("username");
            private final int modelCount = obj.getInt("modelCount");

            @Override
            public final String getUsername() {
                return username;
            }

            @Override
            public final int getModelCount() {
                return modelCount;
            }

            @Override
            public final Page<Model> getModels() {
                return service.queryModels()
                    .username(username)
                    .searchAll();
            }

            @Override
            public final Page<Image> getImages() {
                return service.queryImages()
                    .username(username)
                    .searchAll();
            }

        };
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
        return new Tag(){

            private final String name = obj.getString("name");
            private final int modelCount = obj.getInt("modelCount");

            @Override
            public final String getName() {
                return name;
            }

            @Override
            public final int getModelCount() {
                return modelCount;
            }

        };
    }

}

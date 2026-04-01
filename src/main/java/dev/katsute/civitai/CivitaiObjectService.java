/*
 * Copyright (C) 2026 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

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
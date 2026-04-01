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

import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelVersion;
import dev.katsute.civitai.query.CreatorQuery;
import dev.katsute.civitai.query.ImageQuery;
import dev.katsute.civitai.query.ModelQuery;
import dev.katsute.civitai.query.TagQuery;

public abstract class Civitai {

    Civitai(){};

    public static Civitai authenticate(final String token){
        return new CivitaiService(token);
    }

    // creator

    public abstract CreatorQuery queryCreators();

    // image

    public abstract ImageQuery queryImages();

    // model

    public abstract Model getModel(final int id);

    public abstract ModelQuery queryModels();

    public abstract ModelVersion getModelVersion(final int id);

    public abstract ModelVersion getModelVersion(final String hash);

    // tags

    public abstract TagQuery queryTags();

    // generation

    public abstract String queueImage(final String options);

    public abstract String getModelCoverage(final String model);

    public abstract String getModelCoverage(final String[] models);

    public abstract String getJob(final int job);

    public abstract String getJob(final String token);

    public abstract String queryJob(final String query);

    public abstract boolean cancelJob(final String id);

}
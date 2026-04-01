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

package dev.katsute.civitai.query;

import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelType;

public abstract class ModelQuery extends Query<Model,ModelQuery> {

    public ModelQuery(){};

    public abstract ModelQuery tag(final String tag);

    public abstract ModelQuery username(final String username);

    public abstract ModelQuery types(final ModelType... types);

    public abstract ModelQuery sort(final Sort sort);

    public abstract ModelQuery favorites();

    public abstract ModelQuery favorites(final boolean favorites);

    public abstract ModelQuery hidden();

    public abstract ModelQuery hidden(final boolean hidden);

    public abstract ModelQuery primaryFileOnly();

    public abstract ModelQuery primaryFileOnly(final boolean primaryFileOnly);

    public abstract ModelQuery allowNoCredit();

    public abstract ModelQuery allowNoCredit(final boolean allowNoCredit);

    public abstract ModelQuery allowDifferentLicenses();

    public abstract ModelQuery allowDifferentLicenses(final boolean allowDifferentLicenses);

    public abstract ModelQuery allowCommercialUse();

    public abstract ModelQuery allowCommercialUse(final boolean allowCommercialUse);

    public abstract ModelQuery nsfw();

    public abstract ModelQuery nsfw(final NSFW nsfw);

    public abstract ModelQuery nsfw(final boolean nsfw);

    public abstract ModelQuery supportsGeneration();

    public abstract ModelQuery supportsGeneration(final boolean supportsGeneration);

}
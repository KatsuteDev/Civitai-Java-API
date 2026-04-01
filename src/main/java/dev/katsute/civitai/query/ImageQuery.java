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

import dev.katsute.civitai.image.Image;

public abstract class ImageQuery extends Query<Image,ImageQuery> {

    public ImageQuery(){};

    public abstract ImageQuery modelId(final int id);

    public abstract ImageQuery modelVersionId(final int id);

    public abstract ImageQuery username(final String username);

    public abstract ImageQuery nsfw();

    public abstract ImageQuery nsfw(final NSFW nsfw);

    public abstract ImageQuery nsfw(final boolean nsfw);

    public abstract ImageQuery sort(final Sort sort);

    public abstract ImageQuery period(final Period period);

}
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

package dev.katsute.civitai.model;

import dev.katsute.civitai.Creator;

public abstract class Model {

    public Model(){};

    public abstract int getId();

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isNSFW();

    public abstract String[] getTags();

    public abstract ModelMode getMode();

    public abstract Creator getCreator();

    public abstract class Stats {

        public abstract int getDownloadCount();

        public abstract int getFavoriteCount();

        public abstract int getCommentCount();

        public abstract int getRatingCount();

        public abstract float getRating();

    }

    public abstract ModelVersion[] getVersions();

}
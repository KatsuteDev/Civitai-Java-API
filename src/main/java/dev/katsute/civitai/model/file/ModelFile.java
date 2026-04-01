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

package dev.katsute.civitai.model.file;

import java.util.Date;

import dev.katsute.civitai.model.ModelVersionFormat;

public abstract class ModelFile {

    private ModelFile(){};

    public abstract int getSizeKB();

    public abstract VirusScanResult getVirusScanResult();

    public abstract PickleScanResult getPickleScanResult();

    public abstract Date getScannedAt();

    public abstract boolean isPrimary();

    public abstract FloatingPoint getFP();

    public abstract ModelSize getSize();

    public abstract ModelVersionFormat getFormat();

}
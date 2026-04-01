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

package dev.katsute.civitai.generate;

public class AIResource {

    private final String air;

    private final String ecosystem = "urn";
    private final String name;
    private final String type;
    private final String source;
    private final int id;
    private final int version;

    public AIResource(final String name){
        final String[] parts = name.split(":");

        if(parts.length != 6)
            throw new MalformedResourceException("Resource name must have 6 parts, is '" + parts.length + "'");
        if(!"urn".equalsIgnoreCase(parts[0]))
            throw new MalformedResourceException("Resource name did not start with 'urn', was '" + parts[0] + "'");
        if(!"air".equalsIgnoreCase(parts[1]) && !"arn".equalsIgnoreCase(parts[1]))
            throw new MalformedResourceException("Resource name did not start with 'urn:air' or 'urn:arn', was 'urn:" + parts[1] + "'");

        this.name = parts[1];
        this.type = parts[2];
        this.source = parts[3];

        final String[] model = parts[4].split("@");

        if(model.length != 2){
            throw new MalformedResourceException("Resource name did not contain a version, was '" + model + "'");
        }

        try{
            this.id = Integer.parseInt(model[0]);
        }catch(NumberFormatException e){
            throw new MalformedResourceException("Resource id must be an integer, was '" + parts[4] + "'");
        }

        try{
            this.version = Integer.parseInt(model[1]);
        }catch(NumberFormatException e){
            throw new MalformedResourceException("Resource version must be an integer, was '" + parts[5] + "'");
        }

        this.air = "urn:" + name + ':' + ecosystem + ':' + type + ':' + source + ':' + id + '@' + version;
    }

    public final String name(){
        return air;
    }

    @Override
    public final String toString() {
        return "AIResource{" +
               "ecosystem='" + ecosystem + '\'' +
               ", name='" + name + '\'' +
               ", type='" + type + '\'' +
               ", source='" + source + '\'' +
               ", id=" + id +
               ", version=" + version +
               '}';
    }

}
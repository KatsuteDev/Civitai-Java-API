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

/**
 * Thrown if the server does not return a 200 OK response.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public final class HttpException extends RuntimeException {

    private final String URL;
    private final int code;

    HttpException(final String URL, final int code){
        super("Server returned code " + code + " from '" + URL + "'");
        this.URL = URL;
        this.code = code;
    }

    /**
     * Returns the URL from the Http request.
     * @return URL
     *
     * @since 1.0.0
     */
    public final String URL(){
        return URL;
    }

    /**
     * Returns the code for the Http request.
     * @return code
     *
     * @since 1.0.0
     */
    public final int code(){
        return code;
    }

}
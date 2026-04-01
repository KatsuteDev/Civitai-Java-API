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

public enum Scheduler {

    EULER_A     ("EulerA"),
    EULER       ("Euler"),
    LMS         ("LMS"),
    HEUN        ("Heun"),
    DPM2        ("DPM2"),
    DPM2A       ("DPM2A"),
    DPM2SA      ("DPM2SA"),
    DPM2M       ("DPM2M"),
    DPMSDE      ("DPMSDE"),
    DPMFAST     ("DPMFast"),
    DPMADAPTIVE ("DPMAdaptive"),
    LMSKARRAS   ("LMSKarras"),
    DPM2KARRAS  ("DPM2Karras"),
    DPM2AKARRAS ("DPM2AKarras"),
    DPM2SAKARRAS("DPM2SAKarras"),
    DPM2MKARRAS ("DPM2MKarras"),
    DPMSDEKARRAS("DPMSDEKarras"),
    DDIM        ("DDIM"),
    PLMS        ("PLMS"),
    UNI_PC      ("UniPC"),
    UNDEFINED   ("Undefined"),
    LCM         ("LCM"),
    DDPM        ("DDPM"),
    DEIS        ("DEIS");

    private final String value;

    Scheduler(String value) {
        this.value = value;
    }

    public final String value(){
        return value;
    }

    @Override
    public final String toString() {
        return name();
    }

}
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

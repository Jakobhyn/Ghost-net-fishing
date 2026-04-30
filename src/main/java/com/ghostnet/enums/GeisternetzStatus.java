package com.ghostnet.enums;

public enum GeisternetzStatus {
    GEMELDET("Gemeldet"),
    BERGUNG_BEVORSTEHEND("Bergung bevorstehend"),
    GEBORGEN("Geborgen"),
    VERSCHOLLEN("Verschollen");

    private final String anzeigeName;

    GeisternetzStatus(String anzeigeName) {
        this.anzeigeName = anzeigeName;
    }

    public String getAnzeigeName() {
        return anzeigeName;
    }
}

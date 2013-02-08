package com.asinbow.assetman

class Roll {

    String name;
    String description;

    Boolean adminRead;
    Boolean adminWrite;
    Boolean normalRead;
    Boolean normalWrite;

    static constraints = {
        name(blank: false, size: 4..20, matches: /[a-zA-Z]*/)
    }
}

package com.asinbow.assetman

class Roll {

    String name
    String description

    Boolean adminRead
    Boolean adminWrite
    Boolean normalRead
    Boolean normalWrite

    Date dateCreated
    Date lastUpdated

    String toString() {
        name
    }

    static constraints = {
        name(blank: false, size: 4..20, matches: /[a-zA-Z]*/)
        description()

        adminRead()
        adminWrite()
        normalRead()
        normalWrite()
    }
}

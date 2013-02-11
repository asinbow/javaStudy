package com.asinbow.assetman

class Asset {

    String name
    Boolean deleted

    Date dateCreated
    Date lastUpdated

    String toString() {
        name
    }

    static constraints = {
        name()
        deleted()
    }
    static hasMany = [histories:AssetHistory]
}

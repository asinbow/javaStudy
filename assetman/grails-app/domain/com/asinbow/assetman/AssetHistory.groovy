package com.asinbow.assetman

class AssetHistory {

    //Asset asset
    User creator

    Date dateCreated

    static constraints = {
        asset()
        creator()
    }
    static belongsTo = [asset:Asset]
}

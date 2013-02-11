package com.asinbow.assetman

class User {

    String name
    String description
    Boolean deleted

    Roll roll
    User author

    Date dateCreated
    Date lastUpdated

    String toString() {
        name
    }

    static constraints = {
        name(blank: false, size: 4..20, matches: /[a-zA-Z0-9]*/)
        description()
        deleted()
        roll(blank: false)
        author(nullable: true)
    }
}

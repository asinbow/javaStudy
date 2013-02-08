package com.asinbow.assetman

class User {

    String name;
    String description;

    Roll roll;
    User author;

    static constraints = {
        name(blank: false, size: 4..20, matches: /[a-zA-Z0-9]*/)
        roll(blank: false)
        author(nullable: true)
    }
}

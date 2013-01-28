package com.asinbow.guestbook

class User {

    String name
    String email
    String webpage

    static constraints = {
        name(blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9]+")
        email(email:true)
        webpage(url:true)
    }

    String toString() {
        return name;
    }
}

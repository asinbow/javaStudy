package com.asinbow.guestbook

class Feedback {

    String title
    String feedback
    Date createdDate
    Date updatedDate

    User user
    static hasMany=[comments:Comment]

    static constraints = {
        title(blank:false, nullable:false, size:3..80)
        feedback(blank:false, nullable:false, size:3..500)
        user(nullable:false)
    }
}

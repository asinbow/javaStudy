package com.asinbow.guestbook

class Comment {

    String comment
    Date createdDate
    Date updatedDate

    User user

    static belongsTo=[feedback:Feedback]

    static constraints = {
        comment(blank:false, nullable:false, size:5..500)
        user(nullable:true)
    }

    String toString() {
        if (comment.size()>20) {
            return comment.substring(0, 19);
        } else {
            return comment;
        }
    }
}

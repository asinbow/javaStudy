package com.asinbow.guestbook

import org.springframework.dao.DataIntegrityViolationException

class FeedbackController {

    def scaffold = true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [feedbackInstanceList: Feedback.list(params), feedbackInstanceTotal: Feedback.count()]
    }

    def create() {
        [feedbackInstance: new Feedback(params)]
    }

    def save() {
        def feedbackInstance = new Feedback(params)
        if (!feedbackInstance.save(flush: true)) {
            render(view: "create", model: [feedbackInstance: feedbackInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])
        redirect(action: "show", id: feedbackInstance.id)
    }

    def show(Long id) {
        def feedbackInstance = Feedback.get(id)
        if (!feedbackInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), id])
            redirect(action: "list")
            return
        }

        [feedbackInstance: feedbackInstance]
    }

    def edit(Long id) {
        def feedbackInstance = Feedback.get(id)
        if (!feedbackInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), id])
            redirect(action: "list")
            return
        }

        [feedbackInstance: feedbackInstance]
    }

    def update(Long id, Long version) {
        def feedbackInstance = Feedback.get(id)
        if (!feedbackInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (feedbackInstance.version > version) {
                feedbackInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'feedback.label', default: 'Feedback')] as Object[],
                          "Another user has updated this Feedback while you were editing")
                render(view: "edit", model: [feedbackInstance: feedbackInstance])
                return
            }
        }

        feedbackInstance.properties = params

        if (!feedbackInstance.save(flush: true)) {
            render(view: "edit", model: [feedbackInstance: feedbackInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])
        redirect(action: "show", id: feedbackInstance.id)
    }

    def delete(Long id) {
        def feedbackInstance = Feedback.get(id)
        if (!feedbackInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), id])
            redirect(action: "list")
            return
        }

        try {
            feedbackInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedback.label', default: 'Feedback'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'feedback.label', default: 'Feedback'), id])
            redirect(action: "show", id: id)
        }
    }
}

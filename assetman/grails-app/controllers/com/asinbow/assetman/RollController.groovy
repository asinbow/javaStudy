package com.asinbow.assetman

import org.springframework.dao.DataIntegrityViolationException

class RollController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rollInstanceList: Roll.list(params), rollInstanceTotal: Roll.count()]
    }

    def create() {
        [rollInstance: new Roll(params)]
    }

    def save() {
        def rollInstance = new Roll(params)
        if (!rollInstance.save(flush: true)) {
            render(view: "create", model: [rollInstance: rollInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'roll.label', default: 'Roll'), rollInstance.id])
        redirect(action: "show", id: rollInstance.id)
    }

    def show(Long id) {
        def rollInstance = Roll.get(id)
        if (!rollInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roll.label', default: 'Roll'), id])
            redirect(action: "list")
            return
        }

        [rollInstance: rollInstance]
    }

    def edit(Long id) {
        def rollInstance = Roll.get(id)
        if (!rollInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roll.label', default: 'Roll'), id])
            redirect(action: "list")
            return
        }

        [rollInstance: rollInstance]
    }

    def update(Long id, Long version) {
        def rollInstance = Roll.get(id)
        if (!rollInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roll.label', default: 'Roll'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rollInstance.version > version) {
                rollInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'roll.label', default: 'Roll')] as Object[],
                          "Another user has updated this Roll while you were editing")
                render(view: "edit", model: [rollInstance: rollInstance])
                return
            }
        }

        rollInstance.properties = params

        if (!rollInstance.save(flush: true)) {
            render(view: "edit", model: [rollInstance: rollInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'roll.label', default: 'Roll'), rollInstance.id])
        redirect(action: "show", id: rollInstance.id)
    }

    def delete(Long id) {
        def rollInstance = Roll.get(id)
        if (!rollInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roll.label', default: 'Roll'), id])
            redirect(action: "list")
            return
        }

        try {
            rollInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'roll.label', default: 'Roll'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'roll.label', default: 'Roll'), id])
            redirect(action: "show", id: id)
        }
    }
}

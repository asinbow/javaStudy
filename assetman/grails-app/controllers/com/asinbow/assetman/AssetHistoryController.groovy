package com.asinbow.assetman

import org.springframework.dao.DataIntegrityViolationException

class AssetHistoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [assetHistoryInstanceList: AssetHistory.list(params), assetHistoryInstanceTotal: AssetHistory.count()]
    }

    def create() {
        [assetHistoryInstance: new AssetHistory(params)]
    }

    def save() {
        def assetHistoryInstance = new AssetHistory(params)
        if (!assetHistoryInstance.save(flush: true)) {
            render(view: "create", model: [assetHistoryInstance: assetHistoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), assetHistoryInstance.id])
        redirect(action: "show", id: assetHistoryInstance.id)
    }

    def show(Long id) {
        def assetHistoryInstance = AssetHistory.get(id)
        if (!assetHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), id])
            redirect(action: "list")
            return
        }

        [assetHistoryInstance: assetHistoryInstance]
    }

    def edit(Long id) {
        def assetHistoryInstance = AssetHistory.get(id)
        if (!assetHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), id])
            redirect(action: "list")
            return
        }

        [assetHistoryInstance: assetHistoryInstance]
    }

    def update(Long id, Long version) {
        def assetHistoryInstance = AssetHistory.get(id)
        if (!assetHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (assetHistoryInstance.version > version) {
                assetHistoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'assetHistory.label', default: 'AssetHistory')] as Object[],
                          "Another user has updated this AssetHistory while you were editing")
                render(view: "edit", model: [assetHistoryInstance: assetHistoryInstance])
                return
            }
        }

        assetHistoryInstance.properties = params

        if (!assetHistoryInstance.save(flush: true)) {
            render(view: "edit", model: [assetHistoryInstance: assetHistoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), assetHistoryInstance.id])
        redirect(action: "show", id: assetHistoryInstance.id)
    }

    def delete(Long id) {
        def assetHistoryInstance = AssetHistory.get(id)
        if (!assetHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), id])
            redirect(action: "list")
            return
        }

        try {
            assetHistoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'assetHistory.label', default: 'AssetHistory'), id])
            redirect(action: "show", id: id)
        }
    }
}

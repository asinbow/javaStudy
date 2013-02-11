package com.asinbow.assetman

import org.springframework.dao.DataIntegrityViolationException

class AssetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [assetInstanceList: Asset.list(params), assetInstanceTotal: Asset.count()]
    }

    def create() {
        [assetInstance: new Asset(params)]
    }

    def save() {
        def assetInstance = new Asset(params)
        if (!assetInstance.save(flush: true)) {
            render(view: "create", model: [assetInstance: assetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'asset.label', default: 'Asset'), assetInstance.id])
        redirect(action: "show", id: assetInstance.id)
    }

    def show(Long id) {
        def assetInstance = Asset.get(id)
        if (!assetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), id])
            redirect(action: "list")
            return
        }

        [assetInstance: assetInstance]
    }

    def edit(Long id) {
        def assetInstance = Asset.get(id)
        if (!assetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), id])
            redirect(action: "list")
            return
        }

        [assetInstance: assetInstance]
    }

    def update(Long id, Long version) {
        def assetInstance = Asset.get(id)
        if (!assetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (assetInstance.version > version) {
                assetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'asset.label', default: 'Asset')] as Object[],
                          "Another user has updated this Asset while you were editing")
                render(view: "edit", model: [assetInstance: assetInstance])
                return
            }
        }

        assetInstance.properties = params

        if (!assetInstance.save(flush: true)) {
            render(view: "edit", model: [assetInstance: assetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'asset.label', default: 'Asset'), assetInstance.id])
        redirect(action: "show", id: assetInstance.id)
    }

    def delete(Long id) {
        def assetInstance = Asset.get(id)
        if (!assetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), id])
            redirect(action: "list")
            return
        }

        try {
            assetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'asset.label', default: 'Asset'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'asset.label', default: 'Asset'), id])
            redirect(action: "show", id: id)
        }
    }
}

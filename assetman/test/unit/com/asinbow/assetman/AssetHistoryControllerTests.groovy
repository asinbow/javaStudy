package com.asinbow.assetman



import org.junit.*
import grails.test.mixin.*

@TestFor(AssetHistoryController)
@Mock(AssetHistory)
class AssetHistoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/assetHistory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.assetHistoryInstanceList.size() == 0
        assert model.assetHistoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.assetHistoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.assetHistoryInstance != null
        assert view == '/assetHistory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/assetHistory/show/1'
        assert controller.flash.message != null
        assert AssetHistory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/assetHistory/list'

        populateValidParams(params)
        def assetHistory = new AssetHistory(params)

        assert assetHistory.save() != null

        params.id = assetHistory.id

        def model = controller.show()

        assert model.assetHistoryInstance == assetHistory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/assetHistory/list'

        populateValidParams(params)
        def assetHistory = new AssetHistory(params)

        assert assetHistory.save() != null

        params.id = assetHistory.id

        def model = controller.edit()

        assert model.assetHistoryInstance == assetHistory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/assetHistory/list'

        response.reset()

        populateValidParams(params)
        def assetHistory = new AssetHistory(params)

        assert assetHistory.save() != null

        // test invalid parameters in update
        params.id = assetHistory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/assetHistory/edit"
        assert model.assetHistoryInstance != null

        assetHistory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/assetHistory/show/$assetHistory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        assetHistory.clearErrors()

        populateValidParams(params)
        params.id = assetHistory.id
        params.version = -1
        controller.update()

        assert view == "/assetHistory/edit"
        assert model.assetHistoryInstance != null
        assert model.assetHistoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/assetHistory/list'

        response.reset()

        populateValidParams(params)
        def assetHistory = new AssetHistory(params)

        assert assetHistory.save() != null
        assert AssetHistory.count() == 1

        params.id = assetHistory.id

        controller.delete()

        assert AssetHistory.count() == 0
        assert AssetHistory.get(assetHistory.id) == null
        assert response.redirectedUrl == '/assetHistory/list'
    }
}

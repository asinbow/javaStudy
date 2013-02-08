package com.asinbow.assetman



import org.junit.*
import grails.test.mixin.*

@TestFor(RollController)
@Mock(Roll)
class RollControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/roll/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rollInstanceList.size() == 0
        assert model.rollInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rollInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rollInstance != null
        assert view == '/roll/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/roll/show/1'
        assert controller.flash.message != null
        assert Roll.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/roll/list'

        populateValidParams(params)
        def roll = new Roll(params)

        assert roll.save() != null

        params.id = roll.id

        def model = controller.show()

        assert model.rollInstance == roll
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/roll/list'

        populateValidParams(params)
        def roll = new Roll(params)

        assert roll.save() != null

        params.id = roll.id

        def model = controller.edit()

        assert model.rollInstance == roll
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/roll/list'

        response.reset()

        populateValidParams(params)
        def roll = new Roll(params)

        assert roll.save() != null

        // test invalid parameters in update
        params.id = roll.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/roll/edit"
        assert model.rollInstance != null

        roll.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/roll/show/$roll.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        roll.clearErrors()

        populateValidParams(params)
        params.id = roll.id
        params.version = -1
        controller.update()

        assert view == "/roll/edit"
        assert model.rollInstance != null
        assert model.rollInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/roll/list'

        response.reset()

        populateValidParams(params)
        def roll = new Roll(params)

        assert roll.save() != null
        assert Roll.count() == 1

        params.id = roll.id

        controller.delete()

        assert Roll.count() == 0
        assert Roll.get(roll.id) == null
        assert response.redirectedUrl == '/roll/list'
    }
}

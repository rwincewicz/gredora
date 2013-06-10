package gredora



import org.junit.*
import grails.test.mixin.*

@TestFor(GredoraObjectController)
@Mock(GredoraObject)
class GredoraObjectControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gredoraObject/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gredoraObjectInstanceList.size() == 0
        assert model.gredoraObjectInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.gredoraObjectInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gredoraObjectInstance != null
        assert view == '/gredoraObject/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gredoraObject/show/1'
        assert controller.flash.message != null
        assert GredoraObject.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gredoraObject/list'

        populateValidParams(params)
        def gredoraObject = new GredoraObject(params)

        assert gredoraObject.save() != null

        params.id = gredoraObject.id

        def model = controller.show()

        assert model.gredoraObjectInstance == gredoraObject
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gredoraObject/list'

        populateValidParams(params)
        def gredoraObject = new GredoraObject(params)

        assert gredoraObject.save() != null

        params.id = gredoraObject.id

        def model = controller.edit()

        assert model.gredoraObjectInstance == gredoraObject
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gredoraObject/list'

        response.reset()

        populateValidParams(params)
        def gredoraObject = new GredoraObject(params)

        assert gredoraObject.save() != null

        // test invalid parameters in update
        params.id = gredoraObject.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gredoraObject/edit"
        assert model.gredoraObjectInstance != null

        gredoraObject.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gredoraObject/show/$gredoraObject.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gredoraObject.clearErrors()

        populateValidParams(params)
        params.id = gredoraObject.id
        params.version = -1
        controller.update()

        assert view == "/gredoraObject/edit"
        assert model.gredoraObjectInstance != null
        assert model.gredoraObjectInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gredoraObject/list'

        response.reset()

        populateValidParams(params)
        def gredoraObject = new GredoraObject(params)

        assert gredoraObject.save() != null
        assert GredoraObject.count() == 1

        params.id = gredoraObject.id

        controller.delete()

        assert GredoraObject.count() == 0
        assert GredoraObject.get(gredoraObject.id) == null
        assert response.redirectedUrl == '/gredoraObject/list'
    }
}

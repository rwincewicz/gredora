package gredora



import org.junit.*
import grails.test.mixin.*

@TestFor(GredoraDatastreamController)
@Mock(GredoraDatastream)
class GredoraDatastreamControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gredoraDatastream/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gredoraDatastreamInstanceList.size() == 0
        assert model.gredoraDatastreamInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.gredoraDatastreamInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gredoraDatastreamInstance != null
        assert view == '/gredoraDatastream/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gredoraDatastream/show/1'
        assert controller.flash.message != null
        assert GredoraDatastream.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gredoraDatastream/list'

        populateValidParams(params)
        def gredoraDatastream = new GredoraDatastream(params)

        assert gredoraDatastream.save() != null

        params.id = gredoraDatastream.id

        def model = controller.show()

        assert model.gredoraDatastreamInstance == gredoraDatastream
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gredoraDatastream/list'

        populateValidParams(params)
        def gredoraDatastream = new GredoraDatastream(params)

        assert gredoraDatastream.save() != null

        params.id = gredoraDatastream.id

        def model = controller.edit()

        assert model.gredoraDatastreamInstance == gredoraDatastream
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gredoraDatastream/list'

        response.reset()

        populateValidParams(params)
        def gredoraDatastream = new GredoraDatastream(params)

        assert gredoraDatastream.save() != null

        // test invalid parameters in update
        params.id = gredoraDatastream.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gredoraDatastream/edit"
        assert model.gredoraDatastreamInstance != null

        gredoraDatastream.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gredoraDatastream/show/$gredoraDatastream.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gredoraDatastream.clearErrors()

        populateValidParams(params)
        params.id = gredoraDatastream.id
        params.version = -1
        controller.update()

        assert view == "/gredoraDatastream/edit"
        assert model.gredoraDatastreamInstance != null
        assert model.gredoraDatastreamInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gredoraDatastream/list'

        response.reset()

        populateValidParams(params)
        def gredoraDatastream = new GredoraDatastream(params)

        assert gredoraDatastream.save() != null
        assert GredoraDatastream.count() == 1

        params.id = gredoraDatastream.id

        controller.delete()

        assert GredoraDatastream.count() == 0
        assert GredoraDatastream.get(gredoraDatastream.id) == null
        assert response.redirectedUrl == '/gredoraDatastream/list'
    }
}

application {
    title = 'griffon-groovy-java-fx-dbaccess-sample'
    startupGroups = ['griffonGroovyJavaFxDBaccessSample', 'searcher']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "griffonGroovyJavaFxDBaccessSample"
    'griffonGroovyJavaFxDBaccessSample' {
        model      = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleModel'
        view       = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleView'
        controller = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleController'
    }
    'searcher' {
        model      = 'de.dumischbaenger.SearcherModel'
        view       = 'de.dumischbaenger.SearcherView'
        controller = 'de.dumischbaenger.SearcherController'
    }
}
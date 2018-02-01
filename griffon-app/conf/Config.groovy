application {
    title = 'griffon-groovy-java-fx-dbaccess-sample'
    startupGroups = ['griffonGroovyJavaFxDBaccessSample','searchBar']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "griffonGroovyJavaFxDBaccessSample"
    'griffonGroovyJavaFxDBaccessSample' {
        model      = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleModel'
        view       = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleView'
        controller = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleController'
    }
    'searchBar' {
        model      = 'de.dumischbaenger.SearchBarModel'
        view       = 'de.dumischbaenger.SearchBarView'
        controller = 'de.dumischbaenger.SearchBarController'
    }
}
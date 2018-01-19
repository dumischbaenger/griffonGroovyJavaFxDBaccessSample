application {
    title = 'griffon-groovy-java-fx-dbaccess-sample'
    startupGroups = ['griffonGroovyJavaFxDBaccessSample']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "griffonGroovyJavaFxDBaccessSample"
    'griffonGroovyJavaFxDBaccessSample' {
        model      = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleModel'
        view       = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleView'
        controller = 'de.dumischbaenger.GriffonGroovyJavaFxDBaccessSampleController'
    }
}
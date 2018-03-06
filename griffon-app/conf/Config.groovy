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
    'searchBar' {
        model      = 'de.dumischbaenger.SearchBarModel'
        view       = 'de.dumischbaenger.SearchBarView'
        controller = 'de.dumischbaenger.SearchBarController'
    }
    'personDetail' {
        model      = 'de.dumischbaenger.PersonDetailModel'
        view       = 'de.dumischbaenger.PersonDetailView'
        controller = 'de.dumischbaenger.PersonDetailController'
    }
    'personList' {
        model      = 'de.dumischbaenger.PersonListModel'
        view       = 'de.dumischbaenger.PersonListView'
        controller = 'de.dumischbaenger.PersonListController'
    }
}
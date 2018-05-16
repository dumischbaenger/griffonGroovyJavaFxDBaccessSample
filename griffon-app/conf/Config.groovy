application {
    title = 'griffon-groovy-java-fx-dbaccess-sample'
    startupGroups = ['mainWindow']
    autoShutdown = true
}
windowManager {
  startingWindow = 'nothing' //no automatism here; Attention: empty string leads to griffon exception
}
mvcGroups {
    // MVC Group for "griffonGroovyJavaFxDBaccessSample"
    'mainWindow' {
        model      = 'de.dumischbaenger.MainModel'
        view       = 'de.dumischbaenger.MainView'
        controller = 'de.dumischbaenger.MainController'
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
    'login' {
        model      = 'de.dumischbaenger.LoginModel'
        view       = 'de.dumischbaenger.LoginView'
        controller = 'de.dumischbaenger.LoginController'
    }
}
package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javafx.stage.Modality
import javafx.stage.Stage

@Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
@ArtifactProviderFor(GriffonView)
class LoginView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    FactoryBuilderSupport builder
    @MVCMember @Nonnull
    LoginController controller
    @MVCMember @Nonnull
    LoginModel model

    void initUI() {
        Stage window=builder.application(title: application.configuration['application.title'],
            sizeToScene: true, centerOnScreen: true, name: 'login') {
            scene(fill: WHITE, width: 200, height: 60) {
                g = gridPane {
                    label(id: 'clickLabel', row: 0, column: 0,
                          text: bind(model.clickCountProperty()))
                    button(row: 1, column: 0, prefWidth: 200,
                           griffonActionId: 'click')
                }
                connectActions(g, controller)
                connectMessageSource(g)
            }
        }
        window.initModality(Modality.APPLICATION_MODAL)
        Stage mainWindow=application.windowManager.findWindow('mainWindow')
        window.initOwner(mainWindow)
    }
    
    void show() {
      application.windowManager.show('login')
    }
    void hide() {
      application.windowManager.hide('login')
    }
}
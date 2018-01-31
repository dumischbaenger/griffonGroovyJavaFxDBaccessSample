package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.scene.Scene
import javafx.scene.control.ButtonBar

@ArtifactProviderFor(GriffonView)
class SearcherView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    FactoryBuilderSupport builder
    @MVCMember @Nonnull
    SearcherController controller
    @MVCMember @Nonnull
    SearcherModel model
    
    

    void initUI() {
      ButtonBar node = loadFromFXML()
    }
}
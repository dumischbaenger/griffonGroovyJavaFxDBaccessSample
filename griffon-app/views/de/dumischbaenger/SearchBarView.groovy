package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.scene.control.ButtonBar

@ArtifactProviderFor(GriffonView)
class SearchBarView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    SearchBarController controller
    @MVCMember @Nonnull
    SearchBarModel model

    void initUI() {
        ButtonBar node = loadFromFXML()
    }
}
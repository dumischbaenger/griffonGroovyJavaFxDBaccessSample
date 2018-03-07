package de.dumischbaenger

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonModel)
class SearchBarModel extends AbstractGriffonModel {
    @FXObservable String nameFilter = ""
}
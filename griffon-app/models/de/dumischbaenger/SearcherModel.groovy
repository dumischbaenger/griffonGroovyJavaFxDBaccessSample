package de.dumischbaenger

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable

@ArtifactProviderFor(GriffonModel)
class SearcherModel extends AbstractGriffonModel{
    @FXObservable String clickCount = "0"
}
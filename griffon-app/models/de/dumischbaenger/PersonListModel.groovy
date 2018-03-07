package de.dumischbaenger

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import javafx.collections.ObservableList

@ArtifactProviderFor(GriffonModel)
class PersonListModel extends AbstractGriffonModel{
  ObservableList<Person> personsObervable
}

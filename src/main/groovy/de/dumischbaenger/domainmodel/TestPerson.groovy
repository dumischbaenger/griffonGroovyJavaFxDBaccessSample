package de.dumischbaenger.domainmodel

import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Transient

import javafx.beans.property.LongProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

@Entity
@Access(AccessType.PROPERTY)  
public class TestPerson {
  @Transient
  private final LongProperty id = new SimpleLongProperty();
  @Transient
  private final StringProperty summary=new SimpleStringProperty();
  @Transient
  private final StringProperty description=new SimpleStringProperty();
  
//  @Transient
//  private final StringProperty manufacturer = new SimpleStringProperty();
//  @Transient
//  private final StringProperty model = new SimpleStringProperty();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id.getValue();
  }
  public void setId(Long id) {
    this.id.setValue(id);
  }

  public String getSummary() {
    return summary.getValue();
  }

  public void setSummary(String summary) {
    this.summary.setValue(summary);
  }

  public String getDescription() {
    return description.getValue();
  }

  public void setDescription(String description) {
    this.description.setValue(description);
  }

  @Override
  public String toString() {
    return "Todo [summary=" + summary.getValue() + ", description=" + description.getValue()
    + "]";
  }
}
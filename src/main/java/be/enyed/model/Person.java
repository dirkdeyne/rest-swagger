package be.enyed.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Person() {}

  public Person(String name) {this.name = name;}

  public Long getId() {return id;}

  public String getName() {return name;}

  @Override
	public String toString() {
		return name;
	}
}

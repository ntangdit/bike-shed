package com.bikeshed.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="users")
@Data
@ToString
public class User {

  @Id
  @Column(name="id")
  @GeneratedValue(strategy= GenerationType.AUTO)
  private int id;

  @Column(name="name")
  private String name;

  @Column(name="age")
  private int age;
}

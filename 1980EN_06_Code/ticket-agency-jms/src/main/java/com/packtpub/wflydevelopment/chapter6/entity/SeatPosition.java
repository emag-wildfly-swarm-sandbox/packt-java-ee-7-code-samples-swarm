package com.packtpub.wflydevelopment.chapter6.entity;

/**
 * @author Yoshimasa Tanabe
 */
public enum SeatPosition {
  ORCHESTRA("Orchestra", "orchestra"),
  BOX("Box", "box"),
  BALCONY("Balcony", "balcony");

  private final String label;
  private final String dbRepresentation;

  private SeatPosition(String label, String dbRepresentation) {
    this.label = label;
    this.dbRepresentation = dbRepresentation;
  }

  public String getLabel() {
    return label;
  }

  public String getDatabaseRepresentation() {
    return dbRepresentation;
  }

}

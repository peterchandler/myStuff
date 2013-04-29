package jemstone.mystuff.model;

public class Building extends Item {
  public enum F { Building };
  
  /** Constructed using {@link EntityManager#newBuilding()} */
  Building(int id) {
    super(id);
  }
}

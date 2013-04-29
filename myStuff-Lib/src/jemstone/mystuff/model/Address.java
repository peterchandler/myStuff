package jemstone.mystuff.model;

import java.io.PrintWriter;

import jemstone.util.Printable;
import jemstone.util.Printer;

/**
 * @author Peter
 * @version 1.0
 * @created 08-Jun-2011 21:25:47
 */
public class Address implements Printable {
  public enum F { Address, Street, Suburb, City, PostCode, CountryCode };
  
  /** Address street */
  private String street;

  /** Address suburb */
  private String suburb;

  /** Address city */
  private String city;

  /** Address post code */
  private String postCode;

  /** Address country ISO code */
  private String countryCode;
  
  Address() {
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String country) {
    this.countryCode = country;
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.print(out, this, depth);
  }
}
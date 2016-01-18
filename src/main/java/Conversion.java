

//[START all]
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

import java.lang.String;
import java.util.Date;
import java.util.List;

/**
 * The @Entity tells Objectify about our entity.  We also register it in {@link OfyHelper}
 * Our primary key @Id is set automatically by the Google Datastore for us.
 *
 * Objectify, unlike the AppEngine library requires that you specify the fields you
 * want to index using @Index.  Only indexing the fields you need can lead to substantial gains in
 * performance -- though if not indexing your data from the start will require indexing it later.
 *
 * NOTE - all the properties are PUBLIC so that can keep the code simple.
 **/
@Entity
public class Conversion {
  @Id public Long id;
  public String type;
  public String value;
  public String result;
  @Index public Date date;

  /**
   * Simple constructor just sets the date
   **/
  public Conversion() {
    date = new Date();
  }

  /**
   * A connivence constructor
   **/
  public Conversion(String type, String value, String result) {
    this();
    this.type = type;
	this.value = value;
	this.result = result;
  }
}
//[END all]

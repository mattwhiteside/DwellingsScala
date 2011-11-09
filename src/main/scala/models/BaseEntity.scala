package models

import org.squeryl._
import java.sql.Timestamp

class BaseEntity extends KeyedEntity[Long] {
 
  val id:Long = 0
  var updated_at = new Timestamp(System.currentTimeMillis)
 
}

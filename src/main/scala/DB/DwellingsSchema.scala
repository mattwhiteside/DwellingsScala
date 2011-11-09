package DB

import models.Dwelling
import org.squeryl._

object DwellingsSchema extends Schema {
//When the table name doesn't match the class name, it is specified here :

  val dwellings = table[Dwelling]("dwellings")

}

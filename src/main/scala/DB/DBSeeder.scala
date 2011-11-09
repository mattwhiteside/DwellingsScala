package DB

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.annotations.Column
import org.squeryl._
import initialization_helpers._
import adapters.PostgreSqlAdapter
import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
import com.eltimn.scalaxml.TagSoupFactoryAdapter
import DwellingsSchema._
import main._
import models._


object DBSeeder extends DatabaseInitializer with LogHelper {

	val dbSession = new scala.util.DynamicVariable[Session](null)
	
	
	
  dbSession.withValue(SessionFactory.newSession) {      
    dbSession.value.bindToCurrentThread
    Session.currentSession.setLogger(msg => logger.debug(msg))
    try {  
      logger.debug("getting called")      
    } finally {
      dbSession.value.close
      dbSession.value.unbindFromCurrentThread
    }
  }

	// 
	// 
  def seed() : Unit = {
     
    inTransaction{
      drop  // Bad idea in production application!!!!
      create
      printDdl
      val adapter = new TagSoupFactoryAdapter
      val index_html = adapter.load("http://losangeles.craigslist.org/apa/")
      val links = (index_html \\ "blockquote" \ "p" \ "a" )

      for (link <- links){
        val href = link \ "@href"
        val listing_html = adapter.load(href.text)
        println(href) 
        //inTransaction {
          dwellings.insert(new Dwelling(href.text,0))
        //}
      }
      
    }

  } 
  
  
}

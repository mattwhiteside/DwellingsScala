import java.util.Calendar
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.annotations.Column
import org.squeryl._
import org.scalatra._
import java.net.URL
import org.scalatra.scalate.ScalateSupport
import main.LogHelper

import com.dd.plist._  
import java.io.File
import models.Dwelling
import initialization_helpers._
import DB.DwellingsSchema._
import DB.DBSeeder._
import java.io.ByteArrayOutputStream




class MyScalatraServlet extends ScalatraServlet 
														with ScalateSupport 
														with DatabaseInitializer 
														with DatabaseSessionSupport 
														with LogHelper 
{

	before() { 
		//DB.DBSeeder.seed()
		logger.debug("we're in before")
		contentType = "application/plist"
	}



	get("/") {
		var i = 0
		val _dwellings = from(dwellings)((dwelling) =>
			where(dwelling.id.gt(0)) 
			select(dwelling)
			orderBy(dwelling.updated_at)
			
		).page(0,100)
		val nsArray = new NSArray(_dwellings.size)
		for(dwelling <- _dwellings){
			nsArray.setValue(i,dwelling.toPlist)
			i += 1
		}
		try{
			val root = new NSDictionary
			root.put("dwellings",nsArray)
			BinaryPropertyListWriter.write(response.getOutputStream,root)    
		} catch {
			case e:Exception => e.printStackTrace()
			case _ => println("other problems")
		} 
	}

	notFound {
		// Try to render a ScalateTemplate if no route matched
		findTemplate(requestPath) map { path =>
			contentType = "text/html"
			layoutTemplate(path)
		} orElse serveStaticResource() getOrElse resourceNotFound() 
	}
}

package initialization_helpers

import org.apache.log4j._
import org.scalatra.Handler
import main.LogHelper
import javax.servlet.http.{HttpServletRequest,HttpServletResponse}
import org.squeryl.{SessionFactory,Session}

trait DatabaseSessionSupport extends Handler with LogHelper{
  val dbSession = new scala.util.DynamicVariable[Session](null)
  abstract override def handle(req: HttpServletRequest, res: HttpServletResponse) {
    dbSession.withValue(SessionFactory.newSession) {      
      dbSession.value.bindToCurrentThread
      Session.currentSession.setLogger(msg => logger.debug(msg))
      try {  
        logger.debug("getting called")
        super.handle(req, res)
      } finally {
        dbSession.value.close
        dbSession.value.unbindFromCurrentThread
      }
    }
  }
}
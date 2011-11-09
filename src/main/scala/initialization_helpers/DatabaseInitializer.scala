package initialization_helpers

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.scalatra.Initializable
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{SessionFactory,Session}

trait DatabaseInitializer extends Initializable {
	//TODO: move these into config file
  val databaseUsername = "someusernameinaconfigfile"
  val databasePassword = "somepasswordinaconfigfile"
  val databaseConnection = "jdbc:postgresql://127.0.0.1:5432/somedatabase"

  var cpds = new ComboPooledDataSource
  override def initialize(config: Config) {
    org.apache.log4j.BasicConfigurator.configure 
    cpds.setDriverClass("org.postgresql.Driver")
    cpds.setJdbcUrl(databaseConnection)
    cpds.setUser(databaseUsername)
    cpds.setPassword(databasePassword)

    cpds.setMinPoolSize(1)
    cpds.setAcquireIncrement(1)
    cpds.setMaxPoolSize(50)

    SessionFactory.concreteFactory = Some(() => connection)

    def connection = {
      Session.create(cpds.getConnection, new PostgreSqlAdapter)
    }
  }
}
package controllers

import model.{Whiskies, Distilleries}
import play.api._
import play.api.mvc._

import scala.slick.lifted.TableQuery
import play.api.db.slick.Config.driver.simple._


object Application extends Controller {

  def index = Action {
    val distilleries = TableQuery[Distilleries]
    val whiskies = TableQuery[Whiskies]
    Logger.debug((distilleries.ddl ++ whiskies.ddl).createStatements.mkString)
    Ok(views.html.index("Your new application is ready."))
  }

}
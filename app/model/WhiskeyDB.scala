package model

import models.{Whiskey, Whiskies}
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.json._

import scala.slick.lifted.TableQuery



object WhiskeyDB {
  val whiskies = TableQuery[Whiskies]
  implicit val whiskeyFormat = Json.format[Whiskey]
  def list: List[Whiskey] = DB.withSession { implicit session: Session =>
    whiskies.list
  }
  
  def get(id: Long): Option[Whiskey] = DB.withSession { implicit session: Session =>
    whiskies.filter(_.id === id).firstOption
  }
  
  def save(w: Whiskey) = DB.withSession { implicit session: Session =>
    whiskies.map(w => (w.name, w.age, w.distillery_id)) += (w.name, w.age, w.distillery_id)
  }
  
  def update(w: Whiskey) = DB.withSession { implicit session: Session =>
    whiskies.update(w)
  }

  def delete(id: Long) = DB.withSession { implicit session: Session =>
    whiskies.filter(_.id === id).delete
  }
}

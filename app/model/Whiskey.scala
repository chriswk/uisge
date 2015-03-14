package model

import models.Whiskies

import scala.slick.lifted.TableQuery
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Whiskey(id: Long, name: String, age: Int, distillery_id: Long)


object Whiskey {
  val whiskies = TableQuery[Whiskies]
  implicit val whiskeyFormat = Json.format[Whiskey]
  def list: List[Whiskey] = DB.withSession { implicit session: Session =>
    whiskies.list.map(f => Whiskey(f._1, f._2, f._3, f._4))
  }
  
  def get(id: Long): Option[Whiskey] = DB.withSession { implicit session: Session =>
    val q = for {
      w <- whiskies if w.id === id
    } yield (w.id, w.name, w.age, w.distillery_id)
    q.firstOption.map(w => Whiskey(w._1, w._2, w._3, w._4))
  }
  
  def save(w: Whiskey) = DB.withSession { implicit session: Session =>
    whiskies.map(w => (w.name, w.age, w.distillery_id)) += (w.name, w.age, w.distillery_id)
  }
  
  def update(w: Whiskey) = DB.withSession { implicit session: Session =>
    whiskies.update(w.id, w.name, w.age, w.distillery_id)
  }
}

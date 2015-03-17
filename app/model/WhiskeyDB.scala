package model

import models.{Whiskey, Whiskies}

import scala.slick.lifted.TableQuery
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.json._
import play.api.libs.functional.syntax._



object WhiskeyDB {
  val whiskies = TableQuery[Whiskies]
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
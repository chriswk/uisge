package model

import models.{Distilleries, Distillery}
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.json._

import scala.slick.lifted.TableQuery


object DistilleryDB {
  implicit val distilleryFormat = Json.format[Distillery]
  val distilleries = TableQuery[Distilleries]

  def list() : List[Distillery] = DB.withSession { implicit session: Session =>
    distilleries.list
  }

  def get(id: Long): Option[Distillery] = DB.withSession { implicit session: Session =>
    distilleries.filter(_.id === id).firstOption
  }

  def save(d: Distillery) = DB.withSession { implicit session: Session =>
    distilleries += d
  }

  def update(d: Distillery) = DB.withSession { implicit session: Session =>
    distilleries.filter(_.id === d.id).update(d).run
  }

  def delete(id: Long) = DB.withSession { implicit session: Session =>
    distilleries.filter(_.id === id).delete
  }

}

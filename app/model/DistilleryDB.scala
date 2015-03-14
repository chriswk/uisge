package model

import models.{Distillery, Distilleries}

import scala.slick.lifted.TableQuery
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.json._
import play.api.libs.functional.syntax._


object DistilleryDB {

  val distilleries = TableQuery[Distilleries]
  implicit val distilleryFormat = Json.format[Distillery]
  def list() : List[Distillery] = DB.withSession { implicit session: Session =>
    distilleries.list
  }

  def get(id: Long): Option[Distillery] = DB.withSession { implicit session: Session =>
    distilleries.filter(_.id === id).firstOption
  }

  def save(d: Distillery) = DB.withSession { implicit session: Session =>
    distilleries.map(dist => (dist.name, dist.established)) += (d.name, d.established)
  }

  def update(d: Distillery) = DB.withSession { implicit session: Session =>
    distilleries.filter(_.id === d.id).update(d).run
  }

  def delete(id: Long) = DB.withSession { implicit session: Session =>
    distilleries.filter(_.id === id).delete
  }

}

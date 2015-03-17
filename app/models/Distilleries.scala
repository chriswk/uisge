package models

import play.api.db.slick.Config.driver.simple._
case class Distillery(id: Long, name: String, established: Int)

class Distilleries(tag: Tag) extends Table[Distillery](tag, "DISTILLERY") {
  def id = column[Long]("DIST_ID", O.PrimaryKey,  O.AutoInc)
  def name = column[String]("NAME")
  def established = column[Int]("ESTABLISHED")
  def * = (id, name, established) <> (Distillery.tupled, Distillery.unapply)
}

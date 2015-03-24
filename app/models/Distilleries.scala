package models

import play.api.db.slick.Config.driver.simple._
case class Distillery(id: Long, name: String, established: Option[Int],
                      closed: Option[Int], spirit_stills: Option[Int], wash_stills: Option[Int],
                      owner: Option[String], capacity_per_year: Option[BigDecimal])

class Distilleries(tag: Tag) extends Table[Distillery](tag, "DISTILLERY") {
  def id = column[Long]("DIST_ID", O.PrimaryKey,  O.AutoInc)
  def name = column[String]("NAME")
  def established = column[Option[Int]]("ESTABLISHED")
  def closed = column[Option[Int]]("CLOSED")
  def spirit_stills = column[Option[Int]]("SPIRIT_STILLS")
  def wash_stills = column[Option[Int]]("WASH_STILLS")
  def owner = column[Option[String]]("OWNER")
  def capacity = column[Option[BigDecimal]]("CAPACITY_PER_YEAR")
  def * = (id, name, established, closed, spirit_stills, wash_stills, owner, capacity) <> (Distillery.tupled, Distillery.unapply)
}

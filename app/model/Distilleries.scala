package model

import play.api.db.slick.Config.driver.simple._

class Distilleries(tag: Tag) extends Table[(Long, String, Int)](tag, "DISTILLERY") {
  def id = column[Long]("DIST_ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def established = column[Int]("ESTABLISHED")
  def * = (id, name, established)
}

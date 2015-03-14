package models

import play.api.db.slick.Config.driver.simple._

class Whiskies(tag: Tag) extends Table[(Long, String, Int, Long)](tag, "WHISKEY") {
  def id = column[Long]("WHISKEY_ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def age = column[Int]("AGE")
  def distillery_id = column[Long]("DISTILLERY_ID")
  def * = (id, name, age, distillery_id)
}

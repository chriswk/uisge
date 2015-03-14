package models

import play.api.db.slick.Config.driver.simple._

case class Whiskey(id: Long, name: String, age: Int, distillery_id: Long)
class Whiskies(tag: Tag) extends Table[Whiskey](tag, "WHISKEY") {
  def id = column[Long]("WHISKEY_ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def age = column[Int]("AGE")
  def distillery_id = column[Long]("DISTILLERY_ID")
  def * = (id, name, age, distillery_id) <> (Whiskey.tupled, Whiskey.unapply)
  val distilleries = TableQuery[Distilleries]
  def distillery = foreignKey("DISTILLERY_FK", distillery_id, distilleries)(_.id)
}

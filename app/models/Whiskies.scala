package models

import play.api.db.slick.Config.driver.simple._

case class Whiskey(id: Long, name: String, vintage: Option[Int], bottled: Option[Int],
                   abv: Option[BigDecimal], cask_nr: Option[String],
                   size: Option[Int], distillery_id: Long)
class Whiskies(tag: Tag) extends Table[Whiskey](tag, "WHISKEY") {
  def id = column[Long]("WHISKEY_ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def vintage = column[Option[Int]]("VINTAGE")
  def bottled = column[Option[Int]]("BOTTLED")
  def abv = column[Option[BigDecimal]]("ABV")
  def cask_nr = column[Option[String]]("CASK_NR")
  def size = column[Option[Int]]("SIZE")
  def distillery_id = column[Option[Long]]("DISTILLERY_ID")
  def * = (id, name, vintage, bottled, abv, cask_nr, size, distillery_id) <> (Whiskey.tupled, Whiskey.unapply)
  val distilleries = TableQuery[Distilleries]
  def distillery = foreignKey("DISTILLERY_FK", distillery_id, distilleries)(_.id)
}

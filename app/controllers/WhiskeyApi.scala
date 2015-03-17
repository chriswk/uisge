package controllers


import javax.ws.rs.PathParam

import com.wordnik.swagger.annotations._
import model.WhiskeyDB
import models.Whiskey
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

@Api(value = "/whiskies", description = "Operations on whiskies")
object WhiskeyApi extends Controller {
  implicit val whiskeyFormat = Json.format[Whiskey]
  val whiskeyForm = Form(
    mapping (
      "id" -> default(longNumber, -1L),
      "name" -> nonEmptyText,
      "age" -> number(min = 3),
      "distillery_id" -> longNumber
    )(Whiskey.apply)(Whiskey.unapply)
  )

  @ApiOperation(
    nickname = "listWhiskies",
    value = "List all whiskies",
    httpMethod = "GET",
    response = classOf[Whiskey],
    responseContainer = "List"
  )
  def list = Action {
    val data = Json.toJson(WhiskeyDB.list)
    Ok(data)
  }
  
  @ApiOperation(
    nickname = "getWhiskey",
    value = "Get whiskey with id",
    response = classOf[Whiskey],
    httpMethod = "GET"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message="Whiskey not found")
  ))
  def getWhiskeyById(@ApiParam(value = "ID of the whiskey to fetch") @PathParam("id") id: Long) = Action {
    WhiskeyDB.get(id) match {
      case Some(whiskey) => Ok(Json.toJson(whiskey))
      case None => NotFound
    }
  }
  
  @ApiOperation(
    nickname="addWhiskey",
    value="Add a new Whiskey",
    response = classOf[Void],
    httpMethod = "POST"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid input")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(value = "Whiskey object to be added", required=true, dataType = "Whiskey", paramType = "body"))
  )
  def addWhiskey() = Action(parse.json) {
    implicit request =>
      whiskeyForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(Json.toJson(formWithErrors))
        },
        whiskey => {
          WhiskeyDB.save(whiskey)
          Created
        }
      )
  }
  
  @ApiOperation(
    nickname = "updateWhiskey",
    value = "Update an existing Whiskey",
    response = classOf[Void],
    httpMethod = "PUT"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Whiskey not found"),
    new ApiResponse(code = 405, message = "Validation exception")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(value = "Whiskey to be updated in the store", required = true, dataType = "Whiskey", paramType = "body")
  ))
  def updateWhiskey() = Action(parse.json) {
    implicit request =>
      whiskeyForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(Json.toJson(formWithErrors))
        },
        whiskey => {
          WhiskeyDB.update(whiskey)
          Accepted
        }
      )
  }

  @ApiOperation(nickname = "deleteWhiskey",
    value="Remove an existing Whiskey",
    response = classOf[Void],
    httpMethod = "DELETE")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Whiskey not found")
  ))
  def deleteWhiskey(@ApiParam(value = "ID of the whiskey to delete") @PathParam("id") id: Long) = Action {
    WhiskeyDB.delete(id)
    Ok
  }
  
}

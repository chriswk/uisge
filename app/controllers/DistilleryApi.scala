package controllers

import javax.ws.rs.PathParam

import com.wordnik.swagger.annotations._
import model.DistilleryDB
import model.DistilleryDB._
import models.Distillery
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}

@Api(value = "/distilleries", description = "Operations on distilleries")
object DistilleryApi extends Controller {

  val distilleryForm = Form(
    mapping(
      "id" -> default(longNumber, -1L),
      "name" -> nonEmptyText,
      "established" -> number(min = 1)
    )(Distillery.apply)(Distillery.unapply)
  )

  @ApiOperation(
    nickname = "listDistilleries",
    value = "List all Distilleries",
    httpMethod = "GET",
    response = classOf[Distillery],
    responseContainer = "List"
  )
  def list = Action {
    val data = Json.toJson(DistilleryDB.list)
    Ok(data)
  }

  @ApiOperation(
    nickname = "getDistillery",
    value = "Get Distillery with id",
    response = classOf[Distillery],
    httpMethod = "GET"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message="Distillery not found")
  ))
  def getDistilleryById(@ApiParam(value = "ID of the Distillery to fetch") @PathParam("id") id: Long) = Action {
    DistilleryDB.get(id) match {
      case Some(distillery) => Ok(Json.toJson(distillery))
      case None => NotFound
    }
  }

  @ApiOperation(
    nickname="addDistillery",
    value="Add a new Distillery",
    response = classOf[Void],
    httpMethod = "POST"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid input")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(value = "Distillery object to be added", required=true, dataType = "Distillery", paramType = "body"))
  )
  def addDistillery() = Action {
    implicit request =>
      distilleryForm.bindFromRequest.fold(
        formWithErrors => BadRequest(formWithErrors.errorsAsJson),
        distillery => {
          DistilleryDB.save(distillery)
          Created
        }
      )
  }

  @ApiOperation(
    nickname = "updateDistillery",
    value = "Update an existing Distillery",
    response = classOf[Void],
    httpMethod = "PUT"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid Data"),
    new ApiResponse(code = 404, message = "Distillery not found")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(value = "Distillery to be updated in the store", required = true, dataType = "Distillery", paramType = "body")
  ))
  def updateDistillery() = Action(parse.json) {
    implicit request =>
      distilleryForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(formWithErrors.errorsAsJson)
        },
        distillery => {
          DistilleryDB.get(distillery.id) match {
            case Some(_) => {
              DistilleryDB.update(distillery)
              Accepted
            }
            case None => NotFound
          }
        }
      )
  }
  @ApiOperation(nickname = "deleteDistillery",
  value="Remove an existing Distillery",
  response = classOf[Void],
  httpMethod = "DELETE")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Distillery not found")
  ))
  def deleteDistillery(@ApiParam(value = "ID of the distillery to delete") @PathParam("id") id: Long) = Action {
    DistilleryDB.delete(id)
    Ok
  }

}

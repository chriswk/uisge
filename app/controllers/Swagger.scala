package controllers

import play.api.mvc.{Action, Controller}

object Swagger extends Controller {
  def swagger = Action {
    request => Ok(views.html.swagger())
  }
}

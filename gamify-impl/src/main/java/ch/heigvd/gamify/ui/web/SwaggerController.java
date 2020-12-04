package ch.heigvd.gamify.ui.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class SwaggerController {

  @RequestMapping(value = "/")
  public String index() {
    return "redirect:swagger-ui/";
  }
}

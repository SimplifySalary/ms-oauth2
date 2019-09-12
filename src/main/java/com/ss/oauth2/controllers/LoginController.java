package com.ss.oauth2.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.bouncycastle.util.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@SessionAttributes("authorizationRequest")
public class LoginController {

  @RequestMapping({"/login", "/"})
  public ModelAndView loginPage() {
    return new ModelAndView ("login");
  }

  @RequestMapping("/oauth/confirm_access")
  public ModelAndView confirmAccess(Map<String, Object> model, HttpServletRequest request) {
    AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");

    Map<String, Object> customModel = new HashMap<>();
    customModel.put("client_id", authorizationRequest.getClientId());
    customModel.put("_csrf", model.containsKey("_csrf") ? model.get("_csrf") : request.getAttribute("_csrf"));
    customModel.put("request_path", Optional
            .ofNullable(ServletUriComponentsBuilder.fromContextPath(request).build().getPath())
            .orElse("")
            .concat("/oauth/authorize"));
    customModel.put("scopes", model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
    return new ModelAndView("approval", customModel);
  }

  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return new ModelAndView ("redirect:/login?logout");
  }
}

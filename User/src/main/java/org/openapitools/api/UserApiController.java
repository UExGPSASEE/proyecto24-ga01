package org.openapitools.api;

import org.openapitools.model.CancelSuscripcionPlan200Response;
import org.openapitools.model.DeleteList200Response;
import org.openapitools.model.DeletePaymentMethod200Response;
import org.openapitools.model.DeleteProfile200Response;
import org.openapitools.model.DeleteUser200Response;
import org.openapitools.model.FilmList;
import org.openapitools.model.LogoutUser200Response;
import org.openapitools.model.ModelList;
import org.openapitools.model.PaymentMethod;
import org.openapitools.model.Profile;
import org.openapitools.model.SeriesList;
import org.openapitools.model.SuscripcionPlan;
import org.openapitools.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T21:17:46.398497900+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeUsuario.base-path:/v1/user}")
public class UserApiController implements UserApi {

    private final NativeWebRequest request;

    @Autowired
    public UserApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}

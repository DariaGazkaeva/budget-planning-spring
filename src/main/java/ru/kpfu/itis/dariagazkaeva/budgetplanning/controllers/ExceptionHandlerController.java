package ru.kpfu.itis.dariagazkaeva.budgetplanning.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.*;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;

@ControllerAdvice
@RequestMapping("/error")
public class ExceptionHandlerController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({DbException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequest(BadRequest e) {
        logger.error("Exception caught: " + e.toString() + " !!!");
        return "errors/badRequest";
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("")
    public String notFound(NotFoundException e) {
        logger.error("Exception caught: " + e.toString() + " !!!");
        return "errors/notFound";
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(InternalError e) {
        logger.error("Exception caught: " + e.toString() + " !!!");
        return "errors/internalServerError";
    }
}

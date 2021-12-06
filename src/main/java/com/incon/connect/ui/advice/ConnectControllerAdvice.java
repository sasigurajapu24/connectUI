//package com.incon.connect.ui.advice;
//
//import java.util.Optional;
//
//import org.springframework.hateoas.VndErrors;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.incon.connect.ui.exceptions.ConnectCustomException;
//
//@ControllerAdvice(basePackages = "com.incon.connect.ui")
//@RequestMapping(produces = "application/vnd.error")
//public class ConnectControllerAdvice {
//
//	@ExceptionHandler(ConnectCustomException.class)
//	public ResponseEntity<VndErrors> notFoundException(final ConnectCustomException e) {
//		return error(e, HttpStatus.NOT_FOUND, e.getMessage());
//	}
//
//	private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus,
//			final String logRef) {
//		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
//		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
//	}
//}

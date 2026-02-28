package com.cc.vendas.infraestrutura.erro;

import com.cc.vendas.dominio.excecao.RegraNegocioException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice(basePackages = "com.cc.vendas.adaptadores.entrada.web")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenerica(
            Exception ex,
            HttpServletRequest request) {

        String path = request.getRequestURI();

        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
            throw new RuntimeException(ex);
        }

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno",
                "Erro inesperado. Contate o suporte.",
                request
        );
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(
            HttpStatus status,
            String title,
            String detail,
            HttpServletRequest request) {

        ApiErrorResponse erro = new ApiErrorResponse(
                OffsetDateTime.now().toString(),
                status.value(),
                title,
                detail,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(erro);
    }
}

package cc.wordview.api.controller.resentity;

import org.springframework.http.ResponseEntity;

public class ResponseTemplate {
        public static ResponseEntity<?> notAdmin() {
                return Response.forbidden("can't fly in the sky: This hand does not reach ");
        }
}

package com.burhanmutlu.ws.logins;

import com.burhanmutlu.ws.logins.dto.req.LoginsRequest;
import com.burhanmutlu.ws.shared.GenericResponse;
import com.burhanmutlu.ws.logins.dto.resp.LoginsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginsController {

    private final LoginsService loginsService;

    @GetMapping("/logins/user/{userId}")
    public ResponseEntity<List<LoginsResponse>> getAllLoginsByUserId (
                                                 @PathVariable  Long userId,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                 @RequestParam(defaultValue = "asc") String sortOrder) {

        List<LoginsResponse> loginsResponseList =
                loginsService.getAllLoginsByUserId(userId, page, size, sortBy, sortOrder);
        return ResponseEntity.ok(loginsResponseList);
    }

    @GetMapping("/logins/{id}")
    public ResponseEntity<LoginsResponse> getLoginsById(@PathVariable Long id) {
        LoginsResponse loginsResponse = loginsService.getLoginsById(id);
        return ResponseEntity.ok(loginsResponse);
    }

    @PostMapping("/logins/user/{userId}")
    public ResponseEntity<LoginsResponse> addLoginsByUserId(@PathVariable Long userId,
                                                            @RequestBody LoginsRequest loginsRequest) {
        LoginsResponse loginsResponse = loginsService.addLoginsByUserId(userId, loginsRequest);
        return ResponseEntity.ok(loginsResponse);
    }

    @PutMapping("/logins/{id}")
    public ResponseEntity<LoginsResponse> updateLoginsById(@PathVariable Long id,
                                                           @RequestBody LoginsRequest loginsRequest) {
        LoginsResponse loginsResponse = loginsService.updateLogins(id, loginsRequest);
        return ResponseEntity.ok(loginsResponse);
    }

    @DeleteMapping("/logins/{id}")
    public ResponseEntity<?> deleteLoginsById(@PathVariable Long id) {
        loginsService.deleteLogins(id);
        return ResponseEntity.ok(new GenericResponse(true, "Logins is deleted"));
    }

}

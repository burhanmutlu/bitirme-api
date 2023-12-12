package com.burhanmutlu.ws.creditCard;

import com.burhanmutlu.ws.creditCard.dto.req.CreditCardRequest;
import com.burhanmutlu.ws.creditCard.dto.resp.CreditCardResponse;
import com.burhanmutlu.ws.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping("/credit-cards/user/{id}")
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCardsByUserId(@PathVariable Long id) {
        List<CreditCardResponse> creditCardResponseList =
                creditCardService.getAllCreditCardsByUserId(id);
        return ResponseEntity.ok(creditCardResponseList);
    }

    @GetMapping("credit-cards/{id}")
    public ResponseEntity<CreditCardResponse> getCreditCardById(@PathVariable Long id) {
        CreditCardResponse creditCardResponse = creditCardService.getCreditCardById(id);
        return ResponseEntity.ok(creditCardResponse);
    }

    @PostMapping("/credit-cards/user/{id}")
    public ResponseEntity<CreditCardResponse> addCreditCardsByUserId
            (@PathVariable Long id, @RequestBody CreditCardRequest creditCardRequest) {
        CreditCardResponse creditCardResponse = creditCardService.addCreditCardByUserId(id, creditCardRequest);
        return ResponseEntity.ok(creditCardResponse);
    }

    @PutMapping("/credit-cards/{id}")
    public ResponseEntity<CreditCardResponse> updateCreditCardById
            (@PathVariable Long id, @RequestBody CreditCardRequest creditCardRequest) {
        CreditCardResponse creditCardResponse = creditCardService.updateCreditCard(id, creditCardRequest);
        return ResponseEntity.ok(creditCardResponse);
    }

    @DeleteMapping("/credit-cards/{id}")
    public ResponseEntity<?> deleteCreditCardById(@PathVariable Long id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.ok(new GenericResponse(true, "Credit card is deleted"));
    }

}

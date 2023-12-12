package com.burhanmutlu.ws.creditCard;

import com.burhanmutlu.ws.creditCard.dto.req.CreditCardRequest;
import com.burhanmutlu.ws.creditCard.dto.resp.CreditCardResponse;
import com.burhanmutlu.ws.creditCard.exception.CreditCardNotFoundException;
import com.burhanmutlu.ws.user.User;
import com.burhanmutlu.ws.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger log = LogManager.getLogger(CreditCardServiceImpl.class);

    private final UserService userService;

    private final CreditCardRepository creditCardRepository;

    private final CreditCardMapper creditCardMapper;

    @Override
    public List<CreditCardResponse> getAllCreditCardsByUserId(Long id) {
        User user = userService.getUserById(id);
        List<CreditCardResponse> creditCardResponseList = new ArrayList<>();

        creditCardRepository.findAllByUserId(id).forEach(creditCard -> {
            creditCardResponseList.add(creditCardMapper.toCreditCardResponse(creditCard));
        });

        return creditCardResponseList;
    }

    @Override
    public CreditCardResponse getCreditCardById(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(
                ()->{ throw new CreditCardNotFoundException("credit card not found"); });

        return creditCardMapper.toCreditCardResponse(creditCard);
    }

    @Override
    public CreditCardResponse addCreditCardByUserId(Long userId, CreditCardRequest creditCardRequest) {
        User user = userService.getUserById(userId);

        CreditCard creditCard = creditCardMapper.toCreditCard(creditCardRequest);
        creditCard.setUserId(user);

        creditCard = creditCardRepository.save(creditCard);

        return creditCardMapper.toCreditCardResponse(creditCard);
    }

    @Override
    public CreditCardResponse updateCreditCard(Long id, CreditCardRequest creditCardRequest) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(
                ()->{ throw new CreditCardNotFoundException("credit card not found"); });

        User user = userService.getUserById(creditCard.getUserId().getId());

        creditCard = creditCardMapper.toCreditCard(creditCardRequest);
        creditCard.setId(id);

        creditCard = creditCardRepository.save(creditCard);

        return creditCardMapper.toCreditCardResponse(creditCard);
    }

    @Override
    public void deleteCreditCard(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(
                ()->{ throw new CreditCardNotFoundException("credit card not found"); });

        creditCardRepository.deleteById(id);
    }
}

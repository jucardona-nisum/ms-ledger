package com.nisum.hack.ms.ledger.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class OperationServiceImpl implements OperationService {
    @Override
    public int sumValues(int... values) {

        if(Objects.isNull(values)) {
            throw new ArithmeticException("The operation cannot possible require non-null values.");
        }

        return Arrays.stream(values).sum();
    }
}

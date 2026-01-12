package com.nedware.analisecredito.service.strategy;


import com.nedware.analisecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}
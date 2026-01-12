package com.nedware.proposta_app.listener;


import com.nedware.proposta_app.entity.Proposta;
import com.nedware.proposta_app.mapper.PropostaMapper;
import com.nedware.proposta_app.repository.PropostaRepository;
import com.nedware.proposta_app.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PropostaConcluidaListener {

    private PropostaRepository propostaRepository;

    private WebSocketService webSocketService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        atualizarProposta(proposta);
        webSocketService.notificar(PropostaMapper.INSTANCE.convertEntityToDto(proposta));
    }

    private void atualizarProposta(Proposta proposta) {
        propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
    }
}
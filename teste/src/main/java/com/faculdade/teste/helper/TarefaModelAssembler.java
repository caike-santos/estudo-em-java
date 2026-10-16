package com.faculdade.teste.helper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.faculdade.teste.model.StatusTarefa;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.faculdade.teste.controller.TarefaController;
import com.faculdade.teste.dto.TarefaResponseDto;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<TarefaResponseDto, EntityModel<TarefaResponseDto>> {
    @Override
    public EntityModel<TarefaResponseDto> toModel(TarefaResponseDto dto) {
        EntityModel<TarefaResponseDto> model = EntityModel.of(dto,
                linkTo(methodOn(TarefaController.class).buscarTarefa(dto.id(), null)).withSelfRel(),

                linkTo(methodOn(TarefaController.class).listarTarefas(null)).withRel("todas_tarefas"),

                linkTo(methodOn(TarefaController.class).atualizarTarefa(null, null, dto.id())).withRel("atualizar"),
                
                linkTo(methodOn(TarefaController.class).atualizarTarefaParcialmente(null, null, dto.id()))
                        .withRel("atualizar_parcial"),
                
                linkTo(methodOn(TarefaController.class).deletarTarefa(dto.id(), null)).withRel("deletar"));

                if(dto.status() == StatusTarefa.PENDENTE){
                    model.add(linkTo(methodOn(TarefaController.class).iniciarTarefa(null, dto.id())).withRel("iniciar"));
                }

                if(dto.status() == StatusTarefa.PENDENTE || dto.status() == StatusTarefa.EM_ANDAMENTO){
                    model.add(linkTo(methodOn(TarefaController.class).concluirTarefa(null, dto.id())).withRel("concluir"));
                    model.add(linkTo(methodOn(TarefaController.class).cancelarTarefa(null, dto.id())).withRel("cancelar"));

                    model.add(linkTo(methodOn(TarefaController.class).cadastrarSubtarefas(null, dto.id(), null)).withRel("adicionar_subtarefa"));
                }
                return model;
    }

    @Override
    public CollectionModel<EntityModel<TarefaResponseDto>> toCollectionModel(Iterable<? extends TarefaResponseDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(TarefaController.class).listarTarefas(null)).withSelfRel());
    }
}

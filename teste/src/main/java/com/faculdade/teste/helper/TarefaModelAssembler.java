package com.faculdade.teste.helper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        return EntityModel.of(dto,
                linkTo(methodOn(TarefaController.class).buscarTarefa(dto.id(), null)).withSelfRel(),

                linkTo(methodOn(TarefaController.class).listarTarefas(null)).withRel("todas_tarefas"),

                linkTo(methodOn(TarefaController.class).atualizarTarefa(null, null, dto.id())).withRel("atualizar"),
                
                linkTo(methodOn(TarefaController.class).atualizarTarefaParcialmente(null, null, dto.id()))
                        .withRel("atualizar_parcial"),
                
                linkTo(methodOn(TarefaController.class).deletarTarefa(dto.id(), null)).withRel("deletar"));
    }

    @Override
    public CollectionModel<EntityModel<TarefaResponseDto>> toCollectionModel(Iterable<? extends TarefaResponseDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(TarefaController.class).listarTarefas(null)).withSelfRel());
    }
}

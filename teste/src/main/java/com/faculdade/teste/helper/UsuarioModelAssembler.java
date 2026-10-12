package com.faculdade.teste.helper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.faculdade.teste.controller.TarefaController;
import com.faculdade.teste.controller.UsuarioController;
import com.faculdade.teste.dto.UsuarioResponseDto;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<UsuarioResponseDto, EntityModel<UsuarioResponseDto>> {
    @Override
    public EntityModel<UsuarioResponseDto> toModel(UsuarioResponseDto dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(UsuarioController.class).buscarUsuario(dto.id(), null)).withSelfRel(),

                linkTo(methodOn(UsuarioController.class).atualizarUsuario(dto.id(), null, null)).withRel("atualizar"),
                
                linkTo(methodOn(UsuarioController.class).deletarUsuario(dto.id(), null)).withRel("deletar"),

                linkTo(methodOn(TarefaController.class).listarTarefas(null)).withRel("minhas_tarefas"));

    }

}

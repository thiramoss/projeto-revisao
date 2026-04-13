package dev.ramos.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {
    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensgem nessa rota";
    }

    // Adicionar ninja CREATE
    @PostMapping("/criar")
    public String criarNinja(){
        return "Mostrar  Ninja";
    }
    // Mostrar todos os ninjas
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosOsNinjas(){
        return ninjaService.listarNinjas();
    }
    // Mostrar Ninja por ID Read
    @GetMapping("/listarId")
    public String mostrarNinjaId(){
        return "Mostrar Ninja por ID";
    }
    // Alterar dados dos ninjas
    @PutMapping("/alterarID")
    public String alterarNinja(){
        return "Atualizando ninja";
    }
    // Deletar Ninja
    @DeleteMapping("/deletarID")
    public String excluirNinja(){
        return "Ninja deletado";
    }
}

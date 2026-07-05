package dev.ramos.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID) " + novoNinja.getId());
    }

    // Mostrar todos os ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> mostrarTodosOsNinjas() {
        List<NinjaDTO> ninjas =  ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);

    }

    // Mostrar Ninja por ID Read
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
       NinjaDTO ninja =  ninjaService.listarNinjasPorId(id);

       if (ninja != null) {
           return ResponseEntity.ok(ninja);
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Ninja com o id: " + id + " não existe nos nossos registros");
       }
    }

    // Alterar dados dos ninjas
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if(ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " não encontrado");
        }
    }

    // Deletar Ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id) {
        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com ID " + id + "deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o ID " + id + " não foi encontrado");
        }
    }
}

package ForoHubChallengeAlura.Services;

import ForoHubChallengeAlura.Dto.ActualizarTopicoDTO;
import ForoHubChallengeAlura.Dto.TopicoDTO;
import ForoHubChallengeAlura.Entities.Topico;
import ForoHubChallengeAlura.ManejoErrores.ValidacionDeIntegridad;
import ForoHubChallengeAlura.Repository.ITopico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicoService {

    @Autowired
    private ITopico topicoRespository;

    public List<Topico> verTopicos() {
        List<Topico> topicos = topicoRespository.findAll();
        return topicos;
    }

    public Topico elegirTopico(Long id) {
        Topico topicos = topicoRespository.getReferenceById(id);
        return topicos;
    }

    public Topico modificarElTopico(ActualizarTopicoDTO actualizar) {
        if (!topicoRespository.findById(actualizar.id()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado");
        }
        Topico topicos = topicoRespository.getReferenceById(actualizar.id());
        topicos.actualizarTopico(actualizar);
        return topicos;
    }

    public Topico registrarTopico(TopicoDTO topicoDto) {
        var existe = topicoRespository.existsByTituloAndMensaje(topicoDto.titulo(), topicoDto.mensaje());
        if (existe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tópico duplicado");
        }
        Topico topico = topicoRespository.save(new Topico(topicoDto));
        return topico;
    }

    public void eliminarTopico(Long id) {
        if(!topicoRespository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("El paciente con el id " + id + " no existe");
        }
        topicoRespository.deleteById(id);
    }
}

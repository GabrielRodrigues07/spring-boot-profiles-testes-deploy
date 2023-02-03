package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

    @Mock
    private CursoRepository repository;

    @InjectMocks
    private Curso curso;

    @Before("")
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
        curso.setNome("HTML 5");
        curso.setCategoria("Programação");

        String nomeCurso = "HTML 5";
        Mockito.when(repository.findByNome(nomeCurso)).thenReturn(curso);

        Curso curso = repository.findByNome(nomeCurso);

        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }
    @Test
    void naoDeveriaCarregarUmCursoCujoNomeNaoExista() {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);
        Assertions.assertNull(curso);
    }
}
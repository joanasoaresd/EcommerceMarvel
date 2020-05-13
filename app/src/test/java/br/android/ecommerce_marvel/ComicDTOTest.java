package br.android.ecommerce_marvel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.android.ecommerce_marvel.model.ComicsDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComicDTOTest {

    private ComicsDTO comicsDTO;

    @BeforeEach
    public void setUp() {
        comicsDTO = new ComicsDTO();
    }

    @Test
    @DisplayName("o resultado esperado é raro")
    public void testShowRare() {
        assertEquals(" Raro", comicsDTO.showRare(true));
        assertEquals(" Raro", comicsDTO.showRare(!false));

    }
}


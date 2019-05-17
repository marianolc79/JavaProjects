package jeerest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LambdaTest {

    private List<String> stringList;
    
    @BeforeEach
    void setUp() throws Exception {
        stringList = Arrays.asList("60", "48", "10", "20", "15");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    @DisplayName("Transformar los elementos a Integer")
    void testMapFilter() {
        List<Integer> list2 = stringList.stream().map(x -> Integer.parseInt(x)).filter(x -> x > 30).collect(Collectors.toList());
        
    }

    @Test
    @DisplayName("Obtener el máximo")
    void testMax() {
        Optional<Integer> max = stringList.stream().map(x -> Integer.parseInt(x)).max(Comparator.comparing(x -> x));
        
    }

    @Test
    @DisplayName("Filtrar elementos mayores de 30")
    void testParametrized() {
        List<Integer> list2 = stringList.stream().map(x -> Integer.parseInt(x)).filter(x -> x > 30).collect(Collectors.toList());
    }

}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {

    @Test
    public void nullNameTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void dontNameTest(String name){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> new Horse(name,1,1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -20, -1000})
    public void dontSpeedTest(int i){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> new Horse("name",i,1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -20, -1000})
    public void dontDistanceTest(int i){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name",1,i));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest(){
        Horse horse = new Horse("kol",1,10);
        assertEquals("kol", horse.getName());
    }
    @Test
    public void getSpeedTest(){
        Horse horse = new Horse("kol",1,10);
        assertEquals(1, horse.getSpeed());
    }
    @Test
    public void getDistanceTest(){
        Horse horse = new Horse("kol",1,10);
        assertEquals(10, horse.getDistance());
    }

    @Test
    public void getDistanceTest2(){
        Horse horse = new Horse("kol",1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void  moveTest (){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("kol", 1, 10);
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}

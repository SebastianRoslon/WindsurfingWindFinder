//package pl.roslon.WindsurfingWindFinder;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import pl.roslon.WindsurfingWindFinder.model.PointDto;
//import pl.roslon.WindsurfingWindFinder.service.WindService;
//import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;
//
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ControllerTest {
//
//
//        @Mock
//        private WeatherClient weatherClient;
//
//        @Mock
//        PointDto pointDto;
//
//        @InjectMocks
//        private WindService windService;
//
//        @Test
//        public void addNewPointToListTest() {
//            // Given
//            String cityName = "Warsaw";
//            PointDto pointDto = new PointDto(cityName, 52.2298, 21.0118);
//
//            when(weatherClient.buildPoint(cityName)).thenReturn(pointDto);
//
//            // When
//            String result = windService.addPointToDb(pointDto);
//
//            // Then
//            verify(weatherClient, times(1)).buildPoint(cityName);
//            verify(windService, times(1)).addPointToDb(pointDto);
//            assertEquals("New point added", result);
//        }
//    }
//
//
//
//
//}

package pl.roslon.WindsurfingWindFinder.service;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.roslon.WindsurfingWindFinder.model.PointDto;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

import java.util.List;


@Service
//@AllArgsConstructor
public class WindService {

    private final PointRepository pointRepository;

    public WindService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }



    public void addPointToDb(PointDto pointDto){
        pointRepository.save(pointDto);
    }

    public List<PointDto> printSortedPointList(){
       return pointRepository.findAllByOrderByAvgWindSpeedDesc();
    }

    public List<PointDto> printPointList(){
        return (List<PointDto>) pointRepository.findAll();
    }

//
//    List<PointDto> pointDtoList = new ArrayList<>();
//    public void addNewPointToList(String cityName){
//        pointDtoList.add(weatherClient.buildPoint(cityName));
//    }



}

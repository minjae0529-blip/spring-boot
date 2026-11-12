package com.korai.study03.domain.restaurant.service;

import com.korai.study03.domain.restaurant.dto.RestaurantCreateRequest;
import com.korai.study03.domain.restaurant.dto.RestaurantResponse;
import com.korai.study03.domain.restaurant.entity.Restaurant;
import com.korai.study03.domain.restaurant.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    // 싱글톤 서비스 객체로 스프링에 등록
public class RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    public RestaurantResponse create(RestaurantCreateRequest request){
        // 1. DTO -> Entity 변환
        Restaurant restaurant = request.toRestaurant();
        System.out.println("insert 전: " + restaurant);

        // 2. DB INSERT (auto_increment 된 ID가 restaurant에 채워짐)
        restaurantMapper.insert(restaurant);
        System.out.println("insert 후: " + restaurant);

        // 3. Entity -> Response DTO 변환 후 리턴
        return restaurant.toRestaurantResponse();
    }
}

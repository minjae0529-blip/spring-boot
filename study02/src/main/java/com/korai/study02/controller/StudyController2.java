package com.korai.study02.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Study API 2", description = "List 및 Map 응답 데이터 형식 학습용 API")
@RequestMapping("/api/study2")
@RestController
public class StudyController2 {

    @Operation(
            summary = "기본 List(문자열 리스트) 응답 테스트",
            description = "`List<String>` 데이터를 반환하면 JSON 배열(`[...]`) 형태로 응답됩니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List 응답 성공")
            }
    )
    @GetMapping("/list")
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("포도");
        return list;
    }

    @Operation(
            summary = "기본 Map(키-값 구조) 응답 테스트",
            description = "`Map<String, Object>` 데이터를 반환하면 JSON 객체(`{...}`) 형태로 응답됩니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Map 응답 성공")
            }
    )
    @GetMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "홍길동");
        map.put("age", 25);
        map.put("isStudent", true);
        return map;
    }

    @Operation(
            summary = "List 안에 Map이 포함된 복합 응답 테스트",
            description = "`List<Map<String, Object>>` 데이터를 반환하면 JSON 객체 배열(`[{...}, {...}]`) 형태로 응답됩니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List<Map> 응답 성공")
            }
    )
    @GetMapping("/list-map")
    public List<Map<String, Object>> getListMap() {
        List<Map<String, Object>> userList = new ArrayList<>();

        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("name", "김철수");

        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("name", "이영희");

        userList.add(user1);
        userList.add(user2);

        return userList;
    }

}

package com.korai.study01.study02.controller1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Study03 API", description = "HTTP 메서드(GET, POST, PUT, DELETE) 기본 요청 테스트 API")
@RestController
public class StudyController1 {

    @Operation(
            summary = "GET 요청 테스트",
            description = "기본 GET 방식의 요청을 처리하고 문자열을 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "GET 요청 성공")
            }
    )
    @GetMapping("/api/study03/get01")
    public String get01() {
        return "get요청 11";
    }

    @Operation(
            summary = "POST 요청 테스트",
            description = "기본 POST 방식의 요청을 처리하고 문자열을 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "POST 요청 성공")
            }
    )
    @PostMapping("/api/study03/post01")
    public String post01() {
        return "Post요청 11";
    }

    @Operation(
            summary = "DELETE 요청 테스트",
            description = "기본 DELETE 방식의 요청을 처리하고 문자열을 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "DELETE 요청 성공")
            }
    )
    @DeleteMapping("/api/study03/delete01")
    public String delete01() {
        return "Delete요청";
    }

    @Operation(
            summary = "PUT 요청 테스트",
            description = "기본 PUT 방식의 요청을 처리하고 문자열을 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "PUT 요청 성공")
            }
    )
    @PutMapping("/api/study03/put01")
    public String put01() {
        return "Put리턴!";
    }
}

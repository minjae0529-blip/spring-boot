package com.korai.study01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ===================================================================
 *  StudyController01
 * ===================================================================
 *  @Controller
 *   → 이 클래스가 "요청을 받는 컨트롤러"라고 스프링에게 알려줌
 *   → 스프링이 서버 시작 시 자동으로 객체(Bean)로 만들어 관리함
 *
 *  ★ @Controller 의 기본 동작
 *   → 메서드의 return 값을 "데이터"가 아니라 "View(화면) 경로"로 해석함
 *   → 즉, return "/index.html"  ==  "/index.html 화면으로 이동해라" (forward)
 *
 *  ※ 비교
 *   - @Controller + @ResponseBody  → return 값이 데이터로 출력됨
 *   - @RestController              → 위 둘을 합친 것 (모든 메서드가 데이터 응답)
 * ===================================================================
 */
@Controller
public class StudyController01 {

    /**
     * @Value("${user.dir}")
     *  → ${...} 안의 설정값/시스템 값을 읽어서 아래 변수에 자동으로 넣어줌
     *  → user.dir = 자바 프로그램이 "실행된 폴더" (보통 프로젝트 최상위 폴더)
     *  → 예) /Users/kangminjae/Documents/gov/java/study01
     */
    @Value("${user.dir}")
    private String projectDir;


    /**
     * [GET] /api/get01
     *
     *  @GetMapping("/api/get01")
     *   → 브라우저가 GET 방식으로 /api/get01 을 요청하면 이 메서드 실행
     *   → 서블릿의 doGet() 역할
     *   → 서블릿은 "클래스 1개 = 요청 1개", 스프링은 "메서드 1개 = 요청 1개"
     *
     *  return "/index.html"
     *   → @ResponseBody가 없으므로 "데이터"가 아니라 "경로"로 해석됨
     *   → src/main/resources/static/index.html 파일을 찾아서 화면에 보여줌
     *   → 앞의 "/" 는 "루트(최상위) 경로부터 찾아라"라는 뜻
     */
    @GetMapping("/api/get01")
    public String get01() {
        return "/index.html";
    }


    /**
     * [GET] /api/get02
     *
     *  업로드 폴더의 PDF 파일 경로를 만들어서 return 하는 메서드
     *
     *  1) projectDir + "/src/main/resources/upload"
     *     → 프로젝트 폴더 + 업로드 폴더 = 실제 PC 상의 폴더 경로 (파일 시스템 경로)
     *
     *  2) System.out.println(...)
     *     → 만들어진 경로가 맞는지 콘솔에 찍어서 확인용
     *
     *  3) return "/" + uploadPath + "/파일명.pdf"
     *     → 마찬가지로 "경로(View)"로 해석되어 해당 위치로 forward 시도
     */
    @GetMapping("/api/get02")
    public String get02() {
        System.out.println(projectDir + "/src/main/resources/upload");   // 경로 확인용 출력
        String uploadPath = projectDir + "/src/main/resources/upload";   // 업로드 폴더 경로
        return "/" + uploadPath + "/컴활2급_필기_일타강사_핵심요약_CBT기출마스터북.pdf";
    }


    @ResponseBody
    @GetMapping("/api/get03")
    public String get03(){
        return "데이터 응답!!";
    }

    @PostMapping("/api/study02/post01")
    public String post01(){
        return "Post매핑";
    }
}
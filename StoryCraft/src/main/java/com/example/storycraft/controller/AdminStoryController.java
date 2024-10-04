package com.example.storycraft.controller;

import com.example.storycraft.model.Story;
import com.example.storycraft.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminStoryController {

    @Autowired
    private StoryService storyService;

    // 스토리 관리 페이지
    @GetMapping("/stories")
    public String manageStories(
            @RequestParam(value = "orderBy", defaultValue = "ST_NUM") String orderBy,
            @RequestParam(value = "orderDirection", defaultValue = "ASC") String orderDirection,
            @RequestParam(value = "minReports", required = false) Integer minReports,
            @RequestParam(value = "maxReports", required = false) Integer maxReports,
            Model model) {

        List<Story> stories;

        if (minReports != null && maxReports != null) {
            stories = storyService.getStoriesByReportCount(minReports, maxReports);
        } else {
            stories = storyService.getAllStoriesForAdmin(orderBy, orderDirection);
        }

        // 장르 코드 매핑
        Map<String, String> genreMap = new HashMap<>();
        genreMap.put("CG-01", "판타지");
        genreMap.put("CG-02", "스릴러");
        genreMap.put("CG-03", "코미디");
        genreMap.put("CG-04", "SF");
        genreMap.put("CG-05", "미스터리");
        genreMap.put("CG-06", "로맨스");
        genreMap.put("CG-07", "호러");
        genreMap.put("CG-08", "무협");
        genreMap.put("CG-09", "드라마");
        genreMap.put("CG-10", "서부");
        genreMap.put("CG-11", "역사");

        model.addAttribute("stories", stories);
        model.addAttribute("genreMap", genreMap);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDirection", orderDirection);

        return "adminStoryList"; // 뷰 이름
    }

    // 스토리 삭제 처리
    @PostMapping("/stories/delete")
    @ResponseBody
    public Map<String, Object> deleteStory(@RequestParam("stNum") int stNum) {
        Map<String, Object> response = new HashMap<>();
        boolean isDeleted = storyService.deleteStoryByAdmin(stNum);
        response.put("success", isDeleted);
        return response;
    }

    // 필요에 따라 추가적인 관리자 기능을 구현할 수 있습니다.
}

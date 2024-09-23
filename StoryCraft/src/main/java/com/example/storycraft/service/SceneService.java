package com.example.storycraft.service;

import com.example.storycraft.dao.SceneDao;
import com.example.storycraft.model.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SceneService {

    @Autowired
    private SceneDao sceneDao;

    // 씬 저장 메서드
    public void saveScene(Scene scene) {
        // Scene 객체에 대한 추가 검증 및 처리 로직을 여기에 작성할 수 있습니다.
        sceneDao.saveScene(scene);
        System.out.println("씬이 저장되었습니다: " + scene.getScText());
    }

    // 모든 씬 조회 메서드
    public List<Scene> findAllScenes() {
        // Scene 리스트에 대한 추가 처리 로직을 여기에 작성할 수 있습니다.
        return sceneDao.findAllScenes();
    }

    // 특정 씬 조회 메서드 (Optional로 감싸서 처리)
    public Optional<Scene> findSceneById(Long scNum) {
        return Optional.ofNullable(sceneDao.findSceneById(scNum));
    }

    // 씬 업데이트 메서드
    public void updateScene(Scene updatedScene) {
        Optional<Scene> existingScene = findSceneById(updatedScene.getScNum());
        if (existingScene.isPresent()) {
            Scene scene = existingScene.get();
            scene.setScText(updatedScene.getScText());
            scene.setScChoices(updatedScene.getScChoices());
            scene.setPlayerNum(updatedScene.getPlayerNum());
            scene.setMoney(updatedScene.getMoney());
            scene.setHp(updatedScene.getHp());
            scene.setEndCode(updatedScene.getEndCode());
            scene.setSoundCode(updatedScene.getSoundCode());
            sceneDao.updateScene(scene);
            System.out.println("씬이 업데이트되었습니다: " + scene.getScText());
        } else {
            throw new RuntimeException("씬을 찾을 수 없습니다: ID " + updatedScene.getScNum());
        }
    }

    // 씬 삭제 메서드
    public void deleteScene(Long scNum) {
        Optional<Scene> scene = findSceneById(scNum);
        if (scene.isPresent()) {
            sceneDao.deleteScene(scNum);
            System.out.println("씬이 삭제되었습니다: ID " + scNum);
        } else {
            throw new RuntimeException("씬을 찾을 수 없습니다: ID " + scNum);
        }
    }
}

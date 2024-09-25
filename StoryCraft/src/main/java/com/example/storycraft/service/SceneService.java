package com.example.storycraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.storycraft.dao.SceneDao;
import com.example.storycraft.model.Scene;

@Service
public class SceneService {

    @Autowired
    private SceneDao sceneDao;

    // 씬 저장
    public void saveScene(Scene scene) {
        sceneDao.saveScene(scene);
    }

    // 씬 조회
    public Scene findSceneById(Long scNum) {
        return sceneDao.findSceneById(scNum);
    }

}

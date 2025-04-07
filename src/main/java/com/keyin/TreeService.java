package com.keyin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class TreeService {
    @Autowired
    TreeRepository treeRepository;

    @Autowired
    TreeStructureRepository treeStructureRepository;

    public TreeNode createTree(List<Integer> numbers) {
        Collections.sort(numbers);
        TreeNode root = buildBalancedTree(numbers, 0, numbers.size() - 1);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
        Map<String, TreeNode> rootMap = new HashMap<>();
        rootMap.put("root", root);
        String treeJson = gson.toJson(rootMap);
        String userInputsJson = gson.toJson(numbers);
    
        saveTreeJson(treeJson, userInputsJson);
    
        return root;
    }
    
    private TreeNode buildBalancedTree(List<Integer> nums, int start, int end) {
        if (start > end) return null;
    
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums.get(mid));
    
        node.setLeft(buildBalancedTree(nums, start, mid - 1));
        node.setRight(buildBalancedTree(nums, mid + 1, end));
    
        return node;
    }

    private void saveTreeJson(String treeJson, String userInputsJson) {
        TreeStructure treeStructure = new TreeStructure(treeJson, userInputsJson);
        treeStructureRepository.save(treeStructure);
    }

    public List<TreeStructure> getPreviousTreeJson() {
        return treeStructureRepository.findAll();
    }
}

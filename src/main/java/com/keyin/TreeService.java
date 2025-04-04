package com.keyin;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeService {
    @Autowired
    TreeRepository treeRepository;

    @Autowired
    TreeStructureRepository treeStructureRepository;

    public List<TreeRecord> findAllTrees() {
        return (List<TreeRecord>) treeRepository.findAll();
    }

    public Optional<TreeRecord> findTreeById(long id) {
        return treeRepository.findById(id);
    }

    public TreeNode createTree(List<Integer> numbers) {
        TreeNode root = null;
        for (int number : numbers) {
            root = insertRec(root, number);
        }
        TreeNodeRepository.save(root);
        String[] jsons = convertTreeToJson(root, numbers);
        saveTreeJson(jsons[0], jsons[1])
        return root;
    }

    private TreeNode insertRec(TreeNode root, int value){
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.getValue()) {
            root.setLeft(insertRec(root.getLeft(), value));
        } else if (value > root.getValue()) {
    }
        return root;
    }

    private void saveTreeJson(String treeJson, String userInputsJson) {
        TreeStructure treeStructure = new TreeStructure(treeJson, userInputsJson);
        treeStructureRepository.save(treeStructure);
    }

    public List<TreeStructure> getPreviousTreeJson() {
        return treeStructureRepository.findAll();
    }
}

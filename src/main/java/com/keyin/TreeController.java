package com.keyin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TreeController {
    @Autowired
    TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }
    
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("input") String input, Model model) {
        try {
            List<Integer> numbers = Arrays.stream(input.split(", ")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
            TreeNode root = treeService.createTree(numbers);

            model.addAttribute("treeJson", root);
            model.addAttribute("userInputs", input);

            return "process-numbers";
        }
        catch (Exception e) {
            model.addAttribute("error", "Invalid input format. Please enter comma-separated numbers.");
            return "enter-numbers";
        }
    }

    @GetMapping("/preview-trees")
    public String previewTrees(Model model) {
        List<TreeStructure> allTrees = treeService.getPreviousTreeJson();
        model.addAttribute("trees", allTrees);
        return "preview-trees";
    }
}

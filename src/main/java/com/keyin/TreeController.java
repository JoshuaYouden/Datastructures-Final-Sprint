package com.keyin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class TreeController {
    @Autowired
    TreeService treeService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }
    
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("input") String input, Model model) {
        try {
            List<Integer> numbers = Arrays.stream(input.split(", ")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
            TreeNode root = treeService.createTree(numbers);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String treeJson = gson.toJson(Map.of("root", root));

            model.addAttribute("treeJson", treeJson);
            model.addAttribute("userInputs", input);

            return "process-numbers";
        }
        catch (Exception e) {
            model.addAttribute("error", "Invalid input format. Please enter comma-separated numbers.");
            return "enter-numbers";
        }
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<TreeStructure> allTrees = treeService.getPreviousTreeJson();
        model.addAttribute("trees", allTrees);
        return "previous-trees";
    }
}

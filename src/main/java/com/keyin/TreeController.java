package com.keyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
public class TreeController {
    @Autowired
    TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {}
    
    @PostMapping("/process-numbers")
    public String processNumbers() {}

    @PostMapping("/preview-trees")
    public String previewTrees() {}
}

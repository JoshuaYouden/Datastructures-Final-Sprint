package com.keyin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TreeServiceTest {

    @Autowired
    private TreeService treeService;

    @Test
    public void testCreateTreeRootValue() {
        List<Integer> numbers = Arrays.asList(10, 20, 5);
        TreeNode root = treeService.createTree(numbers);

        assertNotNull(root);
        assertEquals(10, root.getValue());
    }

    @Test
    public void testTreeLeftAndRightChildren() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        TreeNode root = treeService.createTree(numbers);

        assertNotNull(root.getLeft());
        assertNotNull(root.getRight());
    }

    @Test
    public void testTreeIsStoredInDatabase() {
        List<Integer> numbers = Arrays.asList(7, 3, 9);
        treeService.createTree(numbers);

        List<TreeStructure> savedTrees = treeService.getPreviousTreeJson();
        assertTrue(!savedTrees.isEmpty());

        TreeStructure latest = savedTrees.get(savedTrees.size() - 1);
        assertTrue(latest.getTreeJson().contains("7"));
    }
}

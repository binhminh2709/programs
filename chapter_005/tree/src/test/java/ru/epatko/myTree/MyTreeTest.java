package ru.epatko.myTree;

import org.junit.*;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         21.02.17.
 */
public class MyTreeTest {

    MyTree<Integer> mt;
    MyTree.Leaf<Integer> root = new MyTree.Leaf<>(0);

    @Before
    public void initialize() {
        mt = new MyTree<>();
        mt.addChild(root, 1);
        mt.addChild(root, 2);
        mt.addChild(root, 3);
        mt.addChild(root.children.get(1), 4);
        mt.addChild(root.children.get(2), 5);
        mt.addChild(root.children.get(2), 6);
        mt.addChild(root.children.get(2).children.get(1), 7);
    }

    @Test
    public void whenAddObjectsToMyTreeThenCanGetTrue() {
        assertThat(mt.addChild(root.children.get(1), 4), is(true));
        assertThat(mt.addChild(root.children.get(2), 5), is(true));
        assertThat(mt.addChild(root.children.get(2), 6), is(true));
        assertThat(mt.addChild(root.children.get(2).children.get(0), 7), is(true));
    }

    @Test
    public void  whenAddObjectsToMyTreeThenCanGetListOfThem() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(7);
        assertThat(mt.getChildren(), is(list));
    }

    @Test
    public void  whenAddObjectToMyTreeThenCanFindAllTheLeafsContainingIt() {
        mt.addChild(root.children.get(1).children.get(0), 3);
        mt.addChild(root.children.get(1).children.get(0), 3);
        mt.addChild(root.children.get(2).children.get(1), 3);

        List<MyTree.Leaf<Integer>> list = new ArrayList<>();

        list.add(root.children.get(1).children.get(0).children.get(0));
        list.add(root.children.get(1).children.get(0).children.get(1));
        list.add(root.children.get(2));
        list.add(root.children.get(2).children.get(1).children.get(1));

        assertThat(mt.findElement(3), is(list));
    }

    @Test
    public void whenCheckUnbalancedTreeBalanceTgenGetFalse() {
        assertThat(mt.checkBalance(), is(false));
    }

    @Test
    public void whenCheckBalancedTreeBalanceTgenGetTrue() {
        MyTree<Integer> balancedTree = new MyTree<>();
        MyTree.Leaf<Integer> newRoot = new MyTree.Leaf<>(0);

        balancedTree.addChild(newRoot, 1);
        balancedTree.addChild(newRoot, 2);

        balancedTree.addChild(newRoot.children.get(0), 3);
        balancedTree.addChild(newRoot.children.get(0), 4);

        balancedTree.addChild(newRoot.children.get(1), 5);
        balancedTree.addChild(newRoot.children.get(1), 6);

        balancedTree.addChild(newRoot.children.get(0).children.get(0), 7);
        balancedTree.addChild(newRoot.children.get(0).children.get(0), 8);

        balancedTree.addChild(newRoot.children.get(1).children.get(1), 9);
        balancedTree.addChild(newRoot.children.get(1).children.get(1), 10);

        assertThat(balancedTree.checkBalance(), is(true));
    }

}
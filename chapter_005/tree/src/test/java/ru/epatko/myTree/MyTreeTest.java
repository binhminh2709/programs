package ru.epatko.myTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        mt.addChild(root.children.get(2).children.get(0), 7);
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
        list.add(7);
        list.add(6);
        assertThat(mt.getChildren(), is(list));
    }
}
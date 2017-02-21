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
public class MyBsTreeTest {

    MyBsTree<Integer> bst;

    @Before
    public void initialize() {
        bst = new MyBsTree<Integer>();
        assertThat(bst.addChild(8), is(true));
        assertThat(bst.addChild(5), is(true));
        assertThat(bst.addChild(7), is(true));
        assertThat(bst.addChild(1), is(true));
        assertThat(bst.addChild(15), is(true));
        assertThat(bst.addChild(11), is(true));
        assertThat(bst.addChild(16), is(true));
    }

    @Test
    public void whenCreateBalanceTreeThenChekBalanceReturnTrue() {
        assertThat(bst.checkBalance(), is(true));
    }

    @Test
    public void whenCreateNotBalanceTreeThenChekBalanceReturnFalse() {
        bst.addChild(10);
        assertThat(bst.checkBalance(), is(false));
    }

    @Test
    public void whenAddObjectsToTreeThenCanGetThem() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(11);
        list.add(15);
        list.add(16);

        assertThat(bst.getChildren(), is(list));
    }

    @Test
    public void whenAddObjectToTreeThenCanFindIT() {
        assertThat(bst.findElement(16).get(0).value, is(16));
    }

    @Test
    public void whenAddObjectToTreeThenCanFindITBinary() {
        assertThat(bst.binarySearch(16).get(0).value, is(16));
    }

}
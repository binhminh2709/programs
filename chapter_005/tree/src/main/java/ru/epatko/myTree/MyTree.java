package ru.epatko.myTree;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         20.02.17.
 */
public class MyTree<E> {

    private Leaf<E> root;

    public boolean addChild(Leaf<E> parent, E value) {
        boolean result = false;
        if (parent.left != null && parent.right != null) {
            result = false;
        } else if (parent.left == null) {
            parent.left = new Leaf<>(value, parent, null, null);
        } else {
            parent.right = new Leaf<>(value, parent, null, null);
        }
        return result;
    }

    /**
     * Get list of values.
     * @return list of values
     */
    public List<E> getChildren() {
        List<E> list = new ArrayList<E>();
        Leaf<E> temp = root;
        return getValuesList(list, temp);
    }

    /**
     * Running by tree and filling list of values.
     * @param list list
     * @param temp temporary leaf
     * @return list of values
     */
    public List<E> getValuesList(List list, Leaf<E> temp) {
        if (temp != null) {
            getValuesList(list, temp.left);
            list.add(temp.value);
            getValuesList(list, temp.right);
        }
        return list;
    }
}

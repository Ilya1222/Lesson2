package demo;


import list.MyList;
import list.MyListImp;
import mylistIterator.ListIterator;
import mylistIterator.ListIterable;
import java.util.Iterator;

/**
        * The Demo class shows the all necessary methods for operating the list
        * that has type MyListImpl.
        *
        * @version     1.0 16 September 2018
        * @author      Ilay
        */

public class Demo {
    /**
     * Shows the work of the all methods that are realized in MyListImpl class
     * and the package interfaces.
     *
     * @param args inner parameters that was deployed with the program.
     */
    public static void main(String[] args) {
        System.out.println("==== Part1");
        MyListImp list = new MyListImp();
        list.add("A");
        list.add("A2");
        System.out.println(list);
        // [A, A2]
        list.clear();
        System.out.println(list);
        //[]
        list.add("A");
        list.add("A2");
        list.add("A3");
        list.remove(2);
        System.out.println(list);
        // [A A3]
        for (Object el : list.toArray()) {
            System.out.print(el);
        }
        System.out.println();
        // [AA3]
        System.out.println(list.size());
        // 2
        System.out.println(list.contains("B"));
        // false
        System.out.println(list.contains("A3"));
        // true
        list.add("B");
        System.out.println("list1: "+list);
        MyList anotherList = new MyListImp();
        anotherList.add("A");
        anotherList.add("A3");
        System.out.println("list2: "+anotherList);
        System.out.println("list1 contains list2 ->"+list.containsAll(anotherList));
        // false
        anotherList.add("B" +
                "");
        System.out.println("list2: "+anotherList);
        System.out.println("list1 contains list2 ->"+list.containsAll(anotherList));
        // true



        System.out.println("==== Part2");
        list = new MyListImp();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // 1 2 3 4
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        it = list.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println(list);
      //   [1, 3, 4]
        System.out.println(it.next());
        // 3

        it.remove();
        System.out.println(list);
        // [1, 4]
        try {
            it.remove();
        } catch (IllegalStateException ex) {
            System.out.println(ex.getClass());
        }
        // class java.lang.IllegalStateException


        System.out.println("==== Part3");
        list = new MyListImp();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListIterator lit = ((ListIterable) list).listIterator();
        while (lit.hasNext()) {
            System.out.print(lit.next() + " ");
        }
        // 1 2 3 4
        System.out.println();

        while (lit.hasPrevious()) {
            System.out.print(lit.previous() + " ");
        }
        System.out.println();
        // 4 3 2 1
        list = new MyListImp
                ();
        lit = ((ListIterable) list).listIterator();
        System.out.println(lit.hasNext());
        // false
        System.out.println(lit.hasPrevious());
        // false
        list.add("Element");
        System.out.println(lit.next());
        // Elemenet
        System.out.println(lit.hasNext());
        // false
        System.out.println(lit.hasPrevious());
        // true


    }

}

package list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The MyListImp class implements interface MyList.
 *  @version     1.0 18 September 2018
 *  @author      Ilay
 */
public class MyListImp implements MyList  {
    private final int DEFAULT_SIZE = 6;
    private final int CONSTANT = 3;
    private Object[] array = new Object[DEFAULT_SIZE];
    private Object[] arrayFromString;
    private int size = 0;
    private boolean flag = false;




    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;

    }

    private void arrayToString(Object [] o , int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(o, 0, newArray, 0, size);
        arrayFromString = newArray;

    }

    @Override
    public String toString() {
        return Arrays.toString(arrayFromString);
    }

    @Override
    public void add(Object e) {
        if(size == array.length - 1)
            resize(array.length * 2);
        array[size++] = e;
          arrayToString(array,size);
    }

    @Override
    public void clear() {
       for(int i = 0; i<array.length; i++){
           array[i]=null;

       }
       size = 0;
        arrayToString(array,size);


    }




    @Override
    public void remove(int index) {
        flag=false;
        index-=1;
        for (int i = index ; i < size; i++)
            array[i] = array[i + 1];
        array[size] = null;
        size--;
        if (array.length > DEFAULT_SIZE && size < array.length / CONSTANT)
            resize(array.length / 2);
        arrayToString(array,size);
    }

    @Override
    public Object[] toArray() {

        Object [] new_array = arrayFromString;

       return new_array;
    }

    @Override
    public int size() {
       return size;
    }

    @Override
    public boolean contains(Object o) {
       for(int i=0;i<array.length;i++){
           if(array[i]==o){
               return true;
           }

       }

       return false;
    }

    @Override
    public boolean containsAll(MyList c) {
          array= arrayFromString;
        if(Arrays.deepEquals(array,c.toArray()))return true;
        return false;
    }

    @Override
    public Iterator <Object> iterator() {
        return new MyIteratorImp();
    }



    private class MyIteratorImp  implements Iterator {
           int indicator =0;
           int last=0;

        @Override
        public boolean hasNext() {
            return indicator!=size;
        }

        @Override
        public Object next() {
            flag =true;
            int next =indicator;
            Object [] newArray =  MyListImp.this.array;
            if(next>=size) throw new NoSuchElementException();
            if(next>=newArray.length)throw new NoSuchElementException();
            indicator=next+1;
            last=next;
            return  newArray[last];
        }



        @Override
        public void remove() {
            if (!flag)throw  new IllegalStateException();
            int goal =last+1;
             MyListImp.this.remove(goal);
             indicator=indicator-1;



        }


    }




}

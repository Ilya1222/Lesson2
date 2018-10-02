package list;

import mylistIterator.ListIterable;
import mylistIterator.ListIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by DELL on 25.09.2018.
 */
public class MyLinkListImp implements MyList, ListIterable {



    private int size = 0;
    private Node last ;
    private Node first;
    private Object[] arrayFromString;


    public MyLinkListImp(){
         first = new Node(null);
         last = new Node(null);
        first.next=last;
        last.previous=first;

    }

    public Node getLast() {
        return last;
    }

    public Node getFirst() {
        return first;
    }



    ////////////////////////////
    private class Node {
        private Object element;
        private Node next = null;
        private Node previous = null;
        public Node(Object element) {
            this.element = element;


        }

        public Node getPrevious() {
            return previous;
        }

        public Object getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    //////////////////////////////


    private void arrayToString(Object [] o , int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(o, 0, newArray, 0, size);
        arrayFromString = newArray;

    }

    public void addAtEnd(Object new_element) {
        if (new_element == null) {
            throw new NullPointerException("The first argument  is null.");
        }
        Node newNode = new Node(new_element);
        Node currentNode = last.previous;

            last.previous = newNode;
            newNode.previous=currentNode;
            newNode.next=last;
            currentNode.next=newNode;
        size++;
        toArray();
    }


    @Override
    public void add(Object e) {
        addAtEnd(e);
    }

    @Override
    public void clear() {
       first.next=last;
       last.previous=first;
       size=0;
       toArray();
    }

    @Override
    public void remove(int index) {
        Node node = first.next;
        for (int i = 0;node.getNext() != null&& i<=index;i++){

            if(i == index){

                Node prev = node.previous;
                Node next =node.next;
                prev.next=next;
                next.previous=prev;
                size--;
            }
           else node=node.getNext();
        }
        toArray();
    }

    @Override
    public Object[] toArray() {
        Object [] newArray = new Object[size];
        Node node = first.next;
        for (int i = 0;node.getNext() != null&& i<size;i++){

            newArray[i]= node.element;
            node=node.next;
        }
       // arrayFromString=newArray;
        arrayToString(newArray,size);
        return arrayFromString;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        Node node = first.next;
        for (int i = 0;node.getNext() != null&& i<=size;i++){
            if(o==node.element){
                return true;
            }
            else node=node.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        Object [] oldArray = this.arrayFromString;
        if(Arrays.deepEquals(oldArray,c.toArray()))return true;
        return false;
    }

    @Override
    public Iterator<Object> iterator() {
        return new MyIteratorImp();
    }

    @Override
    public ListIterator listIterator() {
        return new MyListIterator();
    }

    public String toString() {
        return Arrays.toString(arrayFromString);
    }
    private class MyIteratorImp  implements Iterator<Object> {

        Node indicator=first;
        Node lastMember;
        int length=0;
        int count =size;
        boolean flag =false;
        @Override
        public boolean hasNext() {
             return (length<size);
        }

        @Override
        public Object next() {
            if(indicator.next==last) throw new NoSuchElementException();
             lastMember = indicator.next;
            indicator = lastMember;
            length++;
            flag=true;
            return indicator.element;

        }

        @Override
        public void remove() {
            int count=length;
            if(!flag) throw new IllegalStateException();
            MyLinkListImp.this.remove(count-1);
            flag=false;
            length--;
        }
    }

    private class MyListIterator  extends MyIteratorImp  implements ListIterator{





        @Override
        public boolean hasPrevious() {
            return  (length!=0 );
        }

        @Override
        public Object previous() {

            if(indicator.previous==first) throw new NoSuchElementException();
            indicator=lastMember;
            lastMember = indicator.previous;
            length--;
            flag=true;
            return indicator.element;
        }

        @Override
        public void set(Object e) {

        }
    }


}

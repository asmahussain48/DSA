package DSAProjectPractice;

import java.util.ArrayList;

public class CarPriceBST {

    private static class Node {
        Car car;
        Node left, right;

        Node( Car car ) {
            this.car=car;
        }
    }

    private Node root;

    public void clear() {
        root=null;
    }

    public void insert( Car car ) {
        root=insertRec( root , car );
    }

    private Node insertRec( Node node , Car car ) {
        if ( node == null ) {
            return new Node( car );
        }
        if ( car.getPrice( ) < node.car.getPrice( ) ) {
            node.left=insertRec( node.left , car );
        } else {
            node.right=insertRec( node.right , car );
        }
        return node;
    }

    public void inOrder( ArrayList<Car> list ) {
        inOrderRec( root , list );
    }

    private void inOrderRec( Node node , ArrayList<Car> list ) {
        if ( node == null ) return;
        inOrderRec( node.left , list );
        list.add( node.car );
        inOrderRec( node.right , list );
    }

    public void getInPriceRange( long minPrice , long maxPrice , ArrayList<Car> list ) {
        getInPriceRangeRec( root , minPrice , maxPrice , list );
    }

    private void getInPriceRangeRec( Node node , long minPrice , long maxPrice , ArrayList<Car> list ) {
        if ( node == null ) return;

        if ( minPrice < node.car.getPrice( ) ) {
            getInPriceRangeRec( node.left , minPrice , maxPrice , list );
        }

        if ( node.car.getPrice( ) >= minPrice && node.car.getPrice( ) <= maxPrice ) {
            list.add( node.car );
        }

        if ( maxPrice > node.car.getPrice( ) ) {
            getInPriceRangeRec( node.right , minPrice , maxPrice , list );
        }
    }

    public Car getMinPriceCar() {
        if ( root == null ) return null;
        Node current=root;
        while (current.left != null) {
            current=current.left;
        }
        return current.car;
    }

    public Car getMaxPriceCar() {
        if ( root == null ) return null;
        Node current=root;
        while (current.right != null) {
            current=current.right;
        }
        return current.car;
    }
}

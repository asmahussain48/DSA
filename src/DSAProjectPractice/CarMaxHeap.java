package DSAProjectPractice;

public class CarMaxHeap {

    private Car[] data;
    private int size;

    public CarMaxHeap( int capacity ) {
        data=new Car[capacity];
        size=0;
    }

    public void clear() {
        for ( int i=0 ; i < size ; i++ ) {
            data[i]=null;
        }
        size=0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void insert( Car car ) {
        if ( isFull( ) ) {
            System.out.println( "Max-heap is full, cannot insert more cars." );
            return;
        }
        data[size]=car;
        heapifyUp( size );
        size++;
    }

    public Car peekMax() {
        if ( isEmpty( ) ) return null;
        return data[0];
    }

    public Car extractMax() {
        if ( isEmpty( ) ) return null;
        Car root=data[0];
        data[0]=data[size - 1];
        data[size - 1]=null;
        size--;
        heapifyDown( 0 );
        return root;
    }

    private void heapifyUp( int index ) {
        while (index > 0) {
            int parent=(index - 1) / 2;
            if ( data[index].getPrice( ) > data[parent].getPrice( ) ) {
                swap( index , parent );
                index=parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown( int index ) {
        while (true) {
            int left=2 * index + 1;
            int right=2 * index + 2;
            int largest=index;

            if ( left < size && data[left].getPrice( ) > data[largest].getPrice( ) ) {
                largest=left;
            }
            if ( right < size && data[right].getPrice( ) > data[largest].getPrice( ) ) {
                largest=right;
            }
            if ( largest != index ) {
                swap( index , largest );
                index=largest;
            } else {
                break;
            }
        }
    }

    private void swap( int i , int j ) {
        Car tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
}

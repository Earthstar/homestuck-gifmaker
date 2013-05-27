package gifmaker;

public class IndexedData<T> implements Comparable<IndexedData<T>>{
    
    public final T data;
    public final int index;
    
    public IndexedData(T data, int index){
        this.data = data;
        this.index = index;
    }
    
    @Override
    public int compareTo(IndexedData<T> other){
        return Integer.compare(this.index, other.index);
    }

}

public class Bubblesortdata {

    public int[] data;
    public int sorted = 10000;
    public int current = -1;

    public Bubblesortdata(int N,int periode) {
        data = new int [N];
        for (int i = 0; i < N; i++) {
            data[i] = (int)(Math.random()*periode)+1;
        }
    }

    public int N (){
        return data.length;
    }

    public int getindex(int index){
        if(index <0 || index >data.length){
            throw new IllegalArgumentException("the index is out of bound");
        }
        return data[index];
    }

    public void swap(int i,int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }




}

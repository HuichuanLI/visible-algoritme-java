public class InsertionSort {
    private int[] numbers;

    public  int orderedIndex = -1;
    public int currentIndex = -1;


    public InsertionSort(int N, int randomBound) {
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if(index < 0 || index > numbers.length){
            throw  new IllegalArgumentException("out of bound");
        }
        return numbers[index];
    }

    public  void swap(int i,int j){
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }


}

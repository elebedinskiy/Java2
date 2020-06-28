package ru.elebedinskiy.java2;

public class Main {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {

        float[] array1 = new float[SIZE];
        float[] array2 = new float[SIZE];

        procArray0to1(array1);
        procArray0to1(array2);

        procArray1thread(array1);
        procArray2thread(array2);

    }

    public static void procArray0to1 (float[] array){
        for (int i = 0; i < array.length; i++){
            array[i] = 1;
        }
    }


    public static void calcArray (float[] array){
        for (int i = 0; i < array.length; i++){
            //System.out.println("Thread name: " + Thread.currentThread().getName()); // для проверки
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Обработка завершена"); // для проверки
    }

    public static void procArray1thread (float[] array){
        long s = System.currentTimeMillis();
        calcArray(array);
        System.out.println("time 1: " + (System.currentTimeMillis() - s));
    }

    public static void procArray2thread (float[] array){

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        long s = System.currentTimeMillis();

        // разделим массив на два массива одинаковой длинны
        System.arraycopy(array, 0, arr1, 0, HALF);
        System.arraycopy(array, HALF, arr2, 0, HALF);

        // проведем вычисления в двух разных потоках
        Thread t1 = new Thread(() -> new Main().calcArray(arr1));
        t1.start();
        Thread t2 = new Thread(() -> new Main().calcArray(arr2));
        t2.start();

        // Обработаем ожидание завершения потоков
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // склеим массив обратно в один целый
        System.arraycopy(arr1, 0, array, 0, HALF);
        System.arraycopy(arr2, 0, array, HALF, HALF);

        System.out.println("time 2: " + (System.currentTimeMillis() - s));

    }

}

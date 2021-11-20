package Entities;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class Queue<T> {

    BlockingQueue<T> queue;

    public Queue(int size)
    {

        queue = new ArrayBlockingQueue<T>(size);
    }

    public boolean put(T item){
        boolean temp;
        try {
            queue.put(item);
            temp = true;
        }catch (InterruptedException e){
            temp = false;
        }
        return temp;
    }

    public T take(){
        /*
        try{
            curr = queue.take();
        }catch ((InterruptedException e)){
            curr = null;
        }
        return curr;
         */
        T curr;
        try {
            curr = queue.take();
        }catch (InterruptedException e){
            curr = null;
        }
        return curr;
    }

}

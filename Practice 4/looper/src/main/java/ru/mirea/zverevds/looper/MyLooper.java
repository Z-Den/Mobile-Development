package ru.mirea.zverevds.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;
    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }
    //TODO Количество лет соответствует времени задержки. Результат вычисления
    // осуществлять через Log.d.
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String job = msg.getData().getString("JOB_KEY");
                String strAge = msg.getData().getString("AGE_KEY");
                int age = Integer.parseInt(strAge);
                try {
                    Thread.sleep(1000 * age);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String data = "My age is " + age + " years old. " +
                        "I am " + job + " now.";
                Log.d("MyLooper get message: ", data);
                int count = data.length();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("The number of letters in the word %s is %d ", data, count));
                message.setData(bundle);
                // Send the message back to main thread message queue use main thread message Handler.
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}

package edu.neu.madcourse.framework;

public class Framework {
    private volatile static Framework myFramework;

    private Framework(){

    }

    public static Framework getMyFramework(){
        if(myFramework==null){
            synchronized (Framework.class){
                if(myFramework==null){
                    myFramework = new Framework();
                }
            }
        }
        return myFramework;
    }

}

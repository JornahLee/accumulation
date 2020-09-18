package com.jornah.work.camelroute.pojo;

/**
 * @author LiCong
 */
public class Function implements Comparable<Function>{
    private String name;
    private String fromState;
    private String toState;
    private String eventName;
    private String thisState;
    private String bpoCall;


    public Function(String name, String fromState, String toState, String thisState, String eventName,String bpoCall) {
        this.name = name;
        this.fromState = fromState;
        this.toState = toState;
        this.eventName = eventName;
        this.thisState = thisState;
        this.bpoCall = bpoCall;
    }

    public String getBpoCall() {
        return bpoCall;
    }

    public void setBpoCall(String bpoCall) {
        this.bpoCall = bpoCall;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getThisState() {
        return thisState;
    }

    public void setThisState(String thisState) {
        this.thisState = thisState;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", fromState='" + fromState + '\'' +
                ", toState='" + toState + '\'' +
                ", eventName='" + eventName + '\'' +
                ", thisState='" + thisState + '\'' +
                ", bpoCall='" + bpoCall + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Function){
            if(((Function) obj).getFromState().trim().equals(this.getFromState().trim())){
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getFromState().hashCode()+getToState().hashCode()+getName().hashCode();
    }

    @Override
    public int compareTo(Function fun) {
        int res=-1;
        if(fun==null){
            return -1;
        }
        String str1=fun.getName();
        String str2=this.getName();
        try{
            return str1.substring(str1.length()-3, str1.length()).compareTo(str2.substring(str2.length()-3, str2.length()));
        }catch (Exception e){
            e.printStackTrace();
            return res;
        }
        // String thisFromState=this.getFromState()+this.getFromState();
        // String funFromState=fun.getFromState()+fun.getThisState();
        //
        // if(thisFromState!=null&&!"".equals(thisFromState.trim())){
        //     if(funFromState!=null&&!"".equals(funFromState.trim())){
        //         return thisFromState.compareTo(funFromState);
        //     }
        // }

    }
}

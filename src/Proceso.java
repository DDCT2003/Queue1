public class Proceso {
    private String num;
    private String id;
    private int time;

    public Proceso(String num,String id, int time){
    this.num =num;
    this.id =id;
    this.time =time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return ""+num+" - "+id+" - "+time;
    }

}

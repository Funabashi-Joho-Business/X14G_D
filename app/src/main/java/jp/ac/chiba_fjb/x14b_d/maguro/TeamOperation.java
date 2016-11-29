package jp.ac.chiba_fjb.x14b_d.maguro;

public class TeamOperation{
    private OnCreateListener mListener;

    public interface OnCreateListener{
        public void onTeamCreated(boolean flag);
    }
    public interface OnListListener{
        public void onTeamList(RecvTeam datas);
    }

    private static String GAS_URL = "https://script.google.com/macros/s/AKfycbxIKA6B_PqTzYDzqJZLoYL3T3Dl4p8Nyz2i0lvjTAGKCyAywsHi/exec";

    public static class SendData{
        public String cmd;
        public String name;
        public String pass;
    }
    public static class RecvTeam{
        public Object[][] values;
    }
    public static class RecvData {
        public Boolean result;
    }

    public static void createTeam(final String name, final String pass, final OnCreateListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {

                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "CREATE";
                sendData.name = name;
                sendData.pass = pass;
                //GASに接続
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                    listener.onTeamCreated(recvData != null && recvData.result);

            }
        };
        thread.start();
    }
    public static void getTeam(final OnListListener listener){



        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "LIST";

                RecvTeam recvData = Json.send(GAS_URL,sendData,RecvTeam.class);
                if(listener != null)
                    listener.onTeamList(recvData);

            }
        };
        thread.start();
    }

}

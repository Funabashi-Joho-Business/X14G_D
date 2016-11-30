package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

public class TeamOperation{
    private OnCreateListener mListener;

    public interface OnCreateListener{
        public void onTeamCreated(boolean flag,int teamId,int userId);
    }
    public interface OnListListener{
        public void onTeamList(RecvTeam datas);
    }

    private static String GAS_URL = "https://script.google.com/macros/s/AKfycbxIKA6B_PqTzYDzqJZLoYL3T3Dl4p8Nyz2i0lvjTAGKCyAywsHi/exec";

    public static class SendData{
        public String cmd;
        public String teamName;
        public String teamPass;
        public String userName;
    }
    public static class RecvTeam{
        public Object[][] values;
    }
    public static class RecvData {
        public Boolean result;
        public int userId;
        public int teamId;
    }

    public static void createTeam(final String teamName, final String teamPass, final String userName,final OnCreateListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {

                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEAM_CREATE";
                sendData.teamName = teamName;
                sendData.teamPass = teamPass;
                sendData.userName = userName;
                //GASに接続
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                    if(recvData != null && recvData.result)
                        listener.onTeamCreated(true,recvData.teamId,recvData.userId);

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
                sendData.cmd = "TEAM_LIST";

                RecvTeam recvData = Json.send(GAS_URL,sendData,RecvTeam.class);
                if(listener != null)
                    listener.onTeamList(recvData);

            }
        };
        thread.start();
    }

}

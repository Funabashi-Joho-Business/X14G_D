package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

public class TeamOperation{
    private OnTeamListener mListener;

    public interface OnTeamListener{
        public void onTeam(RecvData recvData);
    }

    private static String GAS_URL = "https://script.google.com/macros/s/AKfycbxIKA6B_PqTzYDzqJZLoYL3T3Dl4p8Nyz2i0lvjTAGKCyAywsHi/exec";

    public static class SendData{
        public String cmd;
        public String teamId;
        public String teamName;
        public String teamPass;
        public String userName;
        public String userId;

    }
    public static class RecvData {
        public Boolean result;
        public int userId;
        public int teamId;
        public String teamName;
        public Object[][] values;
    }

    public static void createTeam(final String teamName, final String teamPass, final String userName,final OnTeamListener listener){
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
                    listener.onTeam(recvData);
            }
        };
        thread.start();
    }
    public static void getTeam(final OnTeamListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEAM_LIST";

                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                    listener.onTeam(recvData);

            }
        };
        thread.start();
    }
    public static void joinTeam(final String teamId, final String teamPass, final String userId, final String userName,final OnTeamListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEAM_JOIN";
                sendData.teamId = teamId;
                sendData.teamPass = teamPass;
                sendData.userId = userId;
                sendData.userName = userName;

                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                    listener.onTeam(recvData);

            }
        };
        thread.start();
    }
    public static void removeTeam(final String teamId, final String teamPass, final String userId,final OnTeamListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEAM_REMOVE";
                sendData.teamId = teamId;
                sendData.teamPass = teamPass;
                sendData.userId = userId;
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                   listener.onTeam(recvData);
            }
        };
        thread.start();
    }
}

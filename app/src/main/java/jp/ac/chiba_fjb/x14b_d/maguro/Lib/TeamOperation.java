package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

public class TeamOperation{
    private OnTeamListener mListener;

    public interface OnTeamListener{
        public void onTeam(RecvData recvData);
    }

    private static String GAS_URL = "https://script.google.com/macros/s/AKfycbw8yQimj_TyBl_2EhXDV02cyaAEQaOF9M0w-M3OVghO_CQ1Tqo/exec";

    public static class SendData{
        public String cmd;
        public int teamId;
        public String teamName;
        public String teamPass;
        public int userId;
        public String userName;
        public String userPass;


    }
    public static class TeamData{
        public String teamName;
        public int teamCount;
    }

    public static class RecvData {
        public Boolean result;
        public int userId;
        public String teamName;
        public String userPass;
        public TeamData[] values;
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
    public static void joinTeam(final String teamName, final String teamPass, final int userId, final String userName,final String userPass,final OnTeamListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEAM_JOIN";
                sendData.teamName = teamName;
                sendData.teamPass = teamPass;
                sendData.userId = userId;
                sendData.userName = userName;
                sendData.userPass = userPass;

                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                    listener.onTeam(recvData);

            }
        };
        thread.start();
    }
    public static void removeTeam(final int userId,final String userPass,final OnTeamListener listener){
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEAM_REMOVE";
                sendData.userId = userId;
                sendData.userPass = userPass;
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null)
                   listener.onTeam(recvData);
            }
        };
        thread.start();
    }
}

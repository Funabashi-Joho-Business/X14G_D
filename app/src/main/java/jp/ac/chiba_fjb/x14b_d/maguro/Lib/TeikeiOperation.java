package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

//   GAS保存アドレス       gutsdteam@gmail.com
//   GAS保存パスワード 　　x14gteamd

public class TeikeiOperation {
    private OnTeikeiListener mListener;

    public interface OnTeikeiListener{
        public void onTeikei(RecvData recvData);
    }

    private static String GAS_URL = "https://script.google.com/macros/s/AKfycbzix67Qe-YhA403i8YGSJ72uzZhyy8X03xQEmD_UtoDitRUhe4/exec";

    public static class UserData{
        public String sUserName;
        public int sUserId;
        public String sTeamName;
        public String sTeamPass;
        public String sTeikei;
    }

    public static class SendData{
        public String cmd;
        public UserData userData;
    }

    public static class TeikeiData{
        public String rTeikei1;
        public String rTeikei2;
        public String rTeikei3;
        public String rTeikei4;
        public String rTeikei5;
    }

    public static class RecvData {
        public Boolean result;
        public String rUserName;
        public String rChat;
        public TeikeiData[] values;
    }

    public static void getTeikei(final String teamName, final String teamPass, final TeikeiOperation.OnTeikeiListener listener){
        Thread thread = new Thread(){
                @Override
                public void run() {
                    TeikeiOperation.SendData sendData = new TeikeiOperation.SendData();
                    sendData.cmd = "TEIKEI_GET";
                    sendData.userData = new TeikeiOperation.UserData();
                    sendData.userData.sTeamName = teamName;
                    sendData.userData.sTeamPass = teamPass;
                    TeikeiOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeikeiOperation.RecvData.class);
                    if(listener != null)
                        listener.onTeikei(recvData);
                }
        };
        thread.start();
    }

    public static void setTeikei(final String teikei, final String userName, final int userId,final String teamName, final String teamPass, final TeikeiOperation.OnTeikeiListener listener) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEIKEI_SET";
                sendData.userData = new UserData();
                sendData.userData.sUserName = userName;
                sendData.userData.sUserId = userId;
                sendData.userData.sTeamName = teamName;
                sendData.userData.sTeamPass = teamPass;
                sendData.userData.sTeikei = teikei;
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null);
                    listener.onTeikei(recvData);
            }
        };
        thread.start();
    }

    public static void removeTeikei(final String userName,final int userId,final String teamName,final String teamPass){
        Thread thread = new Thread(){
                @Override
                public void run() {
                    //送信データの作成
                    SendData sendData = new SendData();
                    sendData.cmd = "TEIKEI_REMOVE";
                    sendData.userData = new UserData();
                    sendData.userData.sUserName = userName;
                    sendData.userData.sUserId = userId;
                    sendData.userData.sTeamName = teamName;
                    sendData.userData.sTeamPass = teamPass;
                    Json.send(GAS_URL,sendData,TeamOperation.RecvData.class);
                }
        };
        thread.start();
    }

}
package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

public class TeikeiOperation {
    private TeikeiOperation.OnTeikeiListener mListener;

    public interface OnTeikeiListener{
        public void onTeikei(RecvData recvData);
    }

    private static String GAS_URL = "";

    public static class UserData{
        public String mteikei1;
        public String mteikei2;
        public String mteikei3;
        public String mteikei4;
        public String mteikei5;

    }

    public static class SendData{
        public String cmd;
        public TeikeiOperation.UserData userData;
    }

    public static class TeikeiData{
        public String mteikei1;
        public String mteikei2;
        public String mteikei3;
        public String mteikei4;
        public String mteikei5;
    }

    public static class RecvData {
        public String mteikei1;
        public String mteikei2;
        public String mteikei3;
        public String mteikei4;
        public String mteikei5;
//        public Boolean result;
//        public int userId;
//        public String teamName;
//        public String userPass;
//        public TeamOperation.TeamData[] values;
//        public TeamOperation.UserData[] members;
    }

    public static void getTeam(final TeikeiOperation.OnTeikeiListener listener){
        Thread thread = new Thread(){
                @Override
                public void run() {
                    //送信データの作成
                    TeikeiOperation.SendData sendData = new TeikeiOperation.SendData();
                    sendData.cmd = "USER_TEIKEI1";
                    TeikeiOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeikeiOperation.RecvData.class);
                    if(listener != null)
                        listener.onTeikei(recvData);
                }
        };
            thread.start();
    }

    public static void getMember(final String teikei1, final String teikei2, final String teikei3, final String teikei4, final String teikei5,final TeikeiOperation.OnTeikeiListener listener){
        Thread thread = new Thread(){
                @Override
                public void run() {
                    TeikeiOperation.SendData sendData = new TeikeiOperation.SendData();
                    sendData.cmd = "TEAM_MEMBER";
                    sendData.userData = new TeikeiOperation.UserData();
                    sendData.userData.mteikei1 = teikei1;
                    sendData.userData.mteikei2 = teikei2;
                    sendData.userData.mteikei3 = teikei3;
                    sendData.userData.mteikei4 = teikei4;
                    sendData.userData.mteikei5 = teikei5;
                    TeikeiOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeikeiOperation.RecvData.class);
                    if(listener != null)
                        listener.onTeikei(recvData);
                }
        };
        thread.start();
    }

    public static void TeikeiCreate(final String teikei1, final String teikei2, final String teikei3, final String teikei4, final String teikei5, final TeikeiOperation.OnTeikeiListener listener){
        if(teikei1.length() == 0)
            return;
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                TeikeiOperation.SendData sendData = new TeikeiOperation.SendData();
                sendData.cmd = "TEIKEI_CREATE";
                sendData.userData = new TeikeiOperation.UserData();
                sendData.userData.mteikei1 = teikei1;
                sendData.userData.mteikei2 = teikei2;
                sendData.userData.mteikei3 = teikei3;
                sendData.userData.mteikei4 = teikei4;
                sendData.userData.mteikei5 = teikei5;
                TeikeiOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeikeiOperation.RecvData.class);
                if(listener != null);
                    listener.onTeikei(recvData);
            }
        };
        thread.start();
    }

    public static void removeTeam(final int userId,final String userPass,final TeamOperation.OnTeamListener listener){
        Thread thread = new Thread(){
                @Override
                public void run() {
                    //送信データの作成
                    TeamOperation.SendData sendData = new TeamOperation.SendData();
                    sendData.cmd = "TEAM_REMOVE";
                    sendData.userData = new TeamOperation.UserData();
                    sendData.userData.userId = userId;
                    sendData.userData.userPass = userPass;
                    TeamOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeamOperation.RecvData.class);
                    if(listener != null)
                        listener.onTeam(recvData);
                }
        };
        thread.start();
    }

}
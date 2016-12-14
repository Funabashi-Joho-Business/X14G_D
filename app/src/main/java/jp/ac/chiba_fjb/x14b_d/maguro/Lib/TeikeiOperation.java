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
        public int sUserId;
        public String sUserName;
        public String sUserPass;
        public String sTeikei1;
        public String sTeikei2;
        public String sTeikei3;
        public String sTeikei4;
        public String sTeikei5;
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
        public int userId;
        public String userName;
        public String userPass;
        public TeikeiData[] values;
    }

//    public static void getTeikei(final TeikeiOperation.OnTeikeiListener listener){
//        Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    //送信データの作成
//                    TeikeiOperation.SendData sendData = new TeikeiOperation.SendData();
//                    sendData.cmd = "USER_TEIKEI1";
//                    TeikeiOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeikeiOperation.RecvData.class);
//                    if(listener != null)
//                        listener.onTeikei(recvData);
//                }
//        };
//            thread.start();
//    }

//    public static void getMember(final String teikei1, final String teikei2, final String teikei3, final String teikei4, final String teikei5,final TeikeiOperation.OnTeikeiListener listener){
//        Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    TeikeiOperation.SendData sendData = new TeikeiOperation.SendData();
//                    sendData.cmd = "TEAM_MEMBER";
//                    sendData.userData = new TeikeiOperation.UserData();
//                    sendData.userData.mteikei1 = teikei1;
//                    sendData.userData.mteikei2 = teikei2;
//                    sendData.userData.mteikei3 = teikei3;
//                    sendData.userData.mteikei4 = teikei4;
//                    sendData.userData.mteikei5 = teikei5;
//                    TeikeiOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeikeiOperation.RecvData.class);
//                    if(listener != null)
//                        listener.onTeikei(recvData);
//                }
//        };
//        thread.start();
//    }

    public static void teikeiCreate(final String teikei1, final String teikei2, final String teikei3, final String teikei4, final String teikei5, final int userID, final String userName, final String userPass, final TeikeiOperation.OnTeikeiListener listener){
        if(teikei1 == null && teikei2 == null && teikei3 == null && teikei4 == null && teikei5 == null)
            return;
        Thread thread = new Thread(){
            @Override
            public void run() {
                //送信データの作成
                SendData sendData = new SendData();
                sendData.cmd = "TEIKEI_CREATE";
                sendData.userData = new UserData();
                sendData.userData.sUserId = userID;
                sendData.userData.sUserName = userName;
                sendData.userData.sUserPass = userPass;
                sendData.userData.sTeikei1 = teikei1;
                sendData.userData.sTeikei2 = teikei2;
                sendData.userData.sTeikei3 = teikei3;
                sendData.userData.sTeikei4 = teikei4;
                sendData.userData.sTeikei5 = teikei5;
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);
                if(listener != null);
                    listener.onTeikei(recvData);
            }
        };
        thread.start();
    }

//    public static void removeTeam(final int userId,final String userPass,final TeamOperation.OnTeamListener listener){
//        Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    //送信データの作成
//                    TeamOperation.SendData sendData = new TeamOperation.SendData();
//                    sendData.cmd = "TEAM_REMOVE";
//                    sendData.userData = new TeamOperation.UserData();
//                    sendData.userData.userId = userId;
//                    sendData.userData.userPass = userPass;
//                    TeamOperation.RecvData recvData = Json.send(GAS_URL,sendData,TeamOperation.RecvData.class);
//                    if(listener != null)
//                        listener.onTeam(recvData);
//                }
//        };
//        thread.start();
//    }

}
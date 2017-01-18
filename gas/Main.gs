var SHEET_FILE = ".android/jp.ac.chiba_fjb.x14b_d.maguro/Team2"

function getId(name){
  //プロパティサービスの取得
  var p = PropertiesService.getScriptProperties();
  var id = p.getProperty(name);           //プロパティの読み出し
  if(id == null)
    id = 1;                               //プロパティが無ければ初期値1
  else
    id = parseInt(id);                    //プロパティ(文字列)を整数に変換
  p.setProperty(name, id+1);              //+1した値を設定
  return id;                              //IDを戻す
}
//スプレッドシートから全データを読み出す
function getValues(){
  var sheet = openSheetFile(SHEET_FILE).getSheets()[0];
  var rows = sheet.getLastRow();
  var values;
  if(rows == 0)
    return [];
    
  values = sheet.getRange(1,1,rows,8).getValues();

  //不要なレコードを削除
  var now = new Date().getTime();
  var flag = false;

  for(var i=values.length;i>0;i--){
    var t = now-values[i-1][5].getTime();
    if(t > 5*60*1000){
      flag = true;
      sheet.deleteRow(i);
    }
  }
  if(flag){
    var work = [];
    for(var i=0;i<values.length;i++){
      var t = now-values[i][5].getTime();
      if(t <= 5*60*1000){
        work.push(values[i]);
      }
      values = work;
    }
  }

  return values;
}
function doPost(e){
  var data = null;
  //POSTから送られてきているデータをオブジェクトに変換する
  if(e && e.postData){
    values = JSON.parse(e.postData.getDataAsString());
    return ContentService.createTextOutput(JSON.stringify(action(values)));
  }
}
function action(values){
  var returnObject={};
  var lock = LockService.getPublicLock();
  //lock.waitLock(30000);
  if(values && values.cmd){
    var datas = getValues();
    switch(values.cmd){
      case "TEAM_LIST":
        returnObject = getTeamList(datas);
        break;
      case "TEAM_MEMBER":
        returnObject = getTeamMember(values,datas);
        break;
      case "TEAM_JOIN":
        returnObject = joinTeam(values,datas);
        break;
      case "TEAM_REMOVE":
        returnObject = removeTeam(values,datas);
        break;
    }
  }
  lock.releaseLock();  

  //結果を返す
  return returnObject;
}


function getTeamPass(datas,teamName){
  for(var i=0;i<datas.length;i++){
    if(datas[i][3] == teamName)
      return datas[i][4];
  }
  return null;
}
function getUserIndex(datas,userId){
  if(!userId)
    return 0;
  for(var i=0;i<datas.length;i++){
    if(datas[i][0] == userId)
      return i+1;
  }
  return 0;
}
function getUserPass(datas,index){
  return datas[index-1][2];
}

function MD5(value) {
  var array = Utilities.computeDigest(Utilities.DigestAlgorithm.MD5, new Date());
  var result = "";
  for (var i = 0; i < array.length; i++) {
    var v = array[i] < 0?256+array[i]:array[i];
    var c =  v.toString(16);
    result += c.length == 1?'0'+c:c;
  }
  return result;
}

function getTeamList(datas){
  var teams = {};
  for(var i=0;i<datas.length;i++){
    var teamName = datas[i][3];
    var count = teams[teamName];
    teams[teamName] = count==null?1:count+1;
  }
  var values=[];
  for(var index in teams){
    var count = teams[index];
    values.push({teamName:index,teamCount:count});
  }
  return {"values":values};
}
function getTeamMember(values,datas){
  var userData = values['userData'];
  var teamName = userData['teamName'];
  var teamPass = userData['teamPass'];

  //チームの存在確認とパスワードの取得
  var pass = getTeamPass(datas,teamName);
  //チームパスワードの確認
  if(pass != null && pass != teamPass)
    return {'result':false};

  var members = [];
  for(var i=0;i<datas.length;i++){
    if(datas[i][3] != teamName)
      continue;
    var member = {
      "userId":datas[i][0],
      "userName":datas[i][1],
      "locationX":datas[i][6],
      "locationY":datas[i][7]};
    members.push(member);
  }
  return {"result":true,"members":members};
}
function removeTeam(values,datas){
  var userData = values['userData'];
  var userId = userData['userId'];
  var userPass = userData['userPass'];
  var index = getUserIndex(datas,userId);
  if(!index)
    return {'result':false};
  if(getUserPass(datas,index) != userPass)
    return {'result':false};
  var sheet = openSheetFile(SHEET_FILE).getSheets()[0];
  sheet.deleteRow(index);
  return {'result':true};
}
function joinTeam(values,datas){
  var userData = values['userData'];

  var teamName = userData['teamName'];
  var teamPass = userData['teamPass'];
  var userId = userData['userId'];
  var userName = userData['userName'];
  var userPass = userData['userPass'];
  var locationX = userData['locationX'];
  var locationY = userData['locationY'];
  
  if(teamName==null || teamName == "")
    return {'result':false};
  
  //チームの存在確認とパスワードの取得
  var pass = getTeamPass(datas,teamName);
  //チームパスワードの確認
  if(pass != null && pass != teamPass)
    return {'result':false};
  
  //ユーザの照合
  var index = getUserIndex(datas,userId);
  if(index){
    if(userPass != getUserPass(datas,index))
      return {'result':false};
  }
  else
    userId = null;
  
  //ユーザの新規作成
  if(!userId){
    userId = getId("USER_ID");
    userPass = MD5(new Date());
  }
  var lineData=[[userId,userName,userPass,teamName,teamPass,new Date(),locationX,locationY]];  

  var sheet = openSheetFile(SHEET_FILE).getSheets()[0];
  if(!index)
    index = datas.length+1;

  sheet.getRange(index,1,1,8).setValues(lineData);
  
  //チームパスワードの照合
  var returnObject = {};
  returnObject.result = true;
  returnObject.userId = userId;
  returnObject.userPass = userPass;
  return returnObject;
}


function test(){
  var values = {};
  var userData = {};
  userData['teamName'] = "g";
  userData['teamPass'] = "abc";
  userData['userId'] = 60;
  userData['userName'] = "名前";
  userData['userPass'] = "08ea222d345708d157d9d25c3fd87e41";
  userData['locationX'] = 35;
  userData['locationY'] = 39;
  values.userData = userData;
  values.cmd = "TEAM_JOIN";

  Logger.log(action(values));

}